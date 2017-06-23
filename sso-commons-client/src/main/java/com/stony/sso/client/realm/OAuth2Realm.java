package com.stony.sso.client.realm;

import com.alibaba.fastjson.JSON;
import com.stony.sso.commons.StringUtils;
import com.stony.sso.facade.entity.PermissionEntity;
import com.stony.sso.facade.util.PermissionUtil;
import com.google.common.base.Ticker;
import com.google.common.cache.*;
import com.stony.sso.client.OAuth2AuthenticationException;
import com.stony.sso.client.OAuth2Token;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/1 </p>
 * <p>Time: 21:41 </p>
 * <p>Version: 1.0 </p>
 */
public class OAuth2Realm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(OAuth2Realm.class);
    /** appKey **/
    private String clientId;
    private String clientSecret;
    private String accessTokenUrl;
    private String userInfoUrl;
    private String permissionUrl;
    private String redirectUrl;
    private Long expiredSeconds;
    private Long lastMilliseconds;
    private LoadingCache<String, PermissionEntity> entityLoadingCache;
    private LoadingCache<String, String> tokenLoadingCache;

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
    //client.oauth.permission.url
    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;//表示此Realm只支持OAuth2Token类型
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("Enter ");
        String username = (String)principals.getPrimaryPrincipal();
        logger.info("appKey = {} ,username = {}", clientId, username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        PermissionEntity permissionEntity = getEntityCache(username);
        if(null == permissionEntity){
            throw new AuthenticationException("授权码过期，请重新登陆！");
        }
        authorizationInfo.setRoles(PermissionUtil.getRoleNameByRoles(permissionEntity.getRoles()));
        authorizationInfo.setStringPermissions(PermissionUtil.getPermissionsByResources(permissionEntity.getResources()));
        logger.debug("roles = {} ,permissions = {}",authorizationInfo.getRoles(), authorizationInfo.getStringPermissions());
//        authorizationInfo.setRoles(PermissionUtil.getRoleNameByRoles(authorizationService.findRoles(clientId, username)));
//        authorizationInfo.setStringPermissions(PermissionUtil.getPermissionsByResources(authorizationService.findResources(clientId, username)));
//        logger.info("roles = {} ,permissions = {}",authorizationInfo.getRoles(), authorizationInfo.getStringPermissions());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("Enter ");
        OAuth2Token oAuth2Token = (OAuth2Token) token;
        String code = oAuth2Token.getAuthCode();
        if(StringUtils.isEmpty(code)){
            throw new AuthenticationException("授权信息错误");
        }
        PermissionEntity permissionEntity = postPermission(code);
        putEntityCache(permissionEntity); //本地缓存
//        String username = extractUsername(code);
        return new SimpleAuthenticationInfo(permissionEntity.getUsername(), code, getName());
    }



    /**
     *  返回授权信息
     * @return username,roles,resources
     */
    private PermissionEntity postPermission(String code){
        try {
            String accessToken = extractAccessToken(code);
            logger.info("code = {}, accessToken = {}", code, accessToken);
            PermissionEntity entity = postPermissionEntityByToken(accessToken);
            putTokenCache(entity.getUsername(), accessToken);
            return entity;
        } catch (Exception e) {
            logger.error(code + " 获取资源错误", e);
            throw new OAuth2AuthenticationException(e);
        }
    }
    private PermissionEntity postPermissionEntityByToken(String token) throws OAuthSystemException, OAuthProblemException {
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        OAuthClientRequest userInfoRequest = new OAuthPermissionClientRequest(permissionUrl)
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setAccessToken(token)
                .buildQueryMessage();
        //userInfoRequest.setHeader("Content-Type","application/x-www-form-urlencoded");
        userInfoRequest.setHeader(OAuth.HeaderType.CONTENT_TYPE, OAuth.ContentType.URL_ENCODED);
        OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.POST, OAuthResourceResponse.class);
        String body = resourceResponse.getBody();
        return JSON.parseObject(body, PermissionEntity.class);
    }
    private String extractAccessToken(String code){
        logger.info("code = {}, accessTokenUrl = {}, clientId = {}, clientSecret = {}, redirectUrl = {}", code, accessTokenUrl, clientId, clientSecret, redirectUrl);
        try {
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            OAuthClientRequest accessTokenRequest = OAuthClientRequest
                    .tokenLocation(accessTokenUrl)
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setClientId(clientId)
                    .setClientSecret(clientSecret)
                    .setCode(code)
                    .setRedirectURI(redirectUrl)
                    .buildQueryMessage();
            OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
            expiredSeconds = oAuthResponse.getExpiresIn();
            lastMilliseconds = System.currentTimeMillis();
            return oAuthResponse.getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
            throw new OAuth2AuthenticationException(e);
        }
    }
    static class OAuthPermissionClientRequest extends OAuthBearerClientRequest{


        public OAuthPermissionClientRequest(String url) {
            super(url);

        }

        public OAuthPermissionClientRequest setClientId(String clientId) {
            this.parameters.put("client_id", clientId);
            return this;
        }
        public OAuthPermissionClientRequest setClientSecret(String secret) {
            this.parameters.put("client_secret", secret);
            return this;
        }
    }
    private boolean isExpired(){
        if(null == expiredSeconds) return true;
        if(null == lastMilliseconds) return true;
        long elide = System.currentTimeMillis() - lastMilliseconds;
        return (elide / 1000) > expiredSeconds;
    }
    private void checkEntityCache(){
        if(null == entityLoadingCache) initEntityCache();
    }
    private void initEntityCache(){
        this.entityLoadingCache = CacheBuilder.newBuilder()
                .concurrencyLevel(2)
                .initialCapacity(10)
                .maximumSize(50)
                .expireAfterAccess(24, TimeUnit.HOURS)
                .ticker(Ticker.systemTicker())
                .removalListener(new CacheRemovalListener())
                .build(new CacheLoader<String, PermissionEntity>() {
                    @Override
                    public PermissionEntity load(String key) throws Exception {
                        return postPermissionEntityByToken(getTokenCache(key));
                    }
                });
    }

    private void putEntityCache(PermissionEntity entity) {
        if(null == entity) {
            logger.warn("put null entity to cache ");
            return;
        }
        putEntityCache(entity.getUsername(), entity);
    }
    private void putEntityCache(String username, PermissionEntity entity) {
        try {
            checkEntityCache();
            this.entityLoadingCache.put(username, entity);
        } catch (Exception e) {
            logger.warn("put {} to entity cache error {}", username, e.getMessage());
        }
    }

    private PermissionEntity getEntityCache(String username) {
        try {
            checkEntityCache();
            return this.entityLoadingCache.get(username);
        } catch (Exception e) {
            logger.warn("get {} from entity cache error {}", username, e.getMessage());
            try {
                return postPermissionEntityByToken(getTokenCache(username));
            } catch (Exception ee) {
                logger.warn("post {} from entity by token error {}", username, e.getMessage());
                return null;
            }
        }
    }


    private void checkTokenCache(){
        if(null == tokenLoadingCache) initTokenCache();
    }
    private void initTokenCache(){
        this.tokenLoadingCache = CacheBuilder.newBuilder()
                .concurrencyLevel(2)
                .initialCapacity(30)
                .maximumSize(120)
                .expireAfterAccess(72, TimeUnit.HOURS)
                .ticker(Ticker.systemTicker())
                .removalListener(new CacheRemovalListener())
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return null;
                    }
                });
    }
    private void putTokenCache(String username, String code) {
        try {
            checkTokenCache();
            this.tokenLoadingCache.put(username, code);
        } catch (Exception e) {
            logger.warn("put {} to token cache error {}", username, e.getMessage());
        }
    }
    private String getTokenCache(String username){
        try {
            checkTokenCache();
            return this.tokenLoadingCache.get(username);
        } catch (Exception e) {
            logger.warn("get {} from token cache error {}", username, e.getMessage());
            return null;
        }
    }

    /**
     * 废弃请使用
     * @see #extractAccessToken(String)
     * @param code
     * @return
     */
    @Deprecated
    private String extractUsername(String code) {
        try {
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            String accessToken = extractAccessToken(code);
            OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(userInfoUrl)
                    .setAccessToken(accessToken).buildQueryMessage();
            OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
            return resourceResponse.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new OAuth2AuthenticationException(e);
        }
    }

    class CacheRemovalListener implements RemovalListener<String, Object> {
        @Override
        public void onRemoval(RemovalNotification notification) {
            logger.debug("Cache {} removal [{} = {}]", notification.getCause(), notification.getKey(), notification.getValue());
        }
    }
}
