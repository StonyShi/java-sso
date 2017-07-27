package com.stony.sso.client.realm;

import com.alibaba.fastjson.JSON;
import com.stony.sso.client.ClientInfoHold;
import com.stony.sso.commons.StringUtils;
import com.stony.sso.facade.context.PermissionContext;
import com.stony.sso.facade.service.PermissionService;
import com.stony.sso.facade.util.PermissionUtil;
import com.stony.sso.commons.security.exception.OAuth2AuthenticationException;
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

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/1 </p>
 * <p>Time: 21:41 </p>
 * <p>Version: 1.0 </p>
 */
public class OAuth2ClientRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(OAuth2ClientRealm.class);

    ClientInfoHold clientInfoHold;

    @javax.annotation.Resource
    PermissionService permissionService;
    /**
     * Realm 支持 Token 类型
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && (token instanceof OAuth2Token);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("Enter ");
        PermissionContext context = (PermissionContext) principals.getPrimaryPrincipal();
        logger.info("appKey = {} ,username = {}", clientInfoHold.getAppKey(), context.getUsername());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        if (null == context) {
            throw new AuthenticationException("授权码过期，请重新登陆！");
        }
        authorizationInfo.setRoles(PermissionUtil.getRoleNameByRoles(permissionService.getRoles(clientInfoHold.getAppKey(), context.getUsername())));
        authorizationInfo.setStringPermissions(PermissionUtil.getPermissionsByResources(permissionService.getResources(clientInfoHold.getAppKey(), context.getUsername())));
//        authorizationInfo.setRoles(PermissionUtil.getRoleNameByRoles(entity.getRoles()));
//        authorizationInfo.setStringPermissions(PermissionUtil.getPermissionsByResources(entity.getResources()));
        logger.debug("roles = {}, permissions = {}", authorizationInfo.getRoles(), authorizationInfo.getStringPermissions());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("Enter ");
        String code = (String) token.getPrincipal();
        if(StringUtils.isEmpty(code)){
            throw new AuthenticationException("授权code不能为空!");
        }
        PermissionContext context = postPermission(code);
//        String username = postUsername(code);
        return new SimpleAuthenticationInfo(context, code, getName());
    }



    /**
     *  返回授权资源信息
     * @return username,roles,resources
     */
    private PermissionContext postPermission(String code){
        try {
            OAuthAccessTokenResponse tokenInfo = postTokenInfo(code);
            logger.info("code = {}, accessToken = {}, refreshToken = {}", code, tokenInfo.getAccessToken(),tokenInfo.getRefreshToken());
            PermissionContext context = postPermissionByToken(tokenInfo.getAccessToken());
            context.setCode(code);
            context.setAccessToken(tokenInfo.getAccessToken());
            context.setRefreshToken(tokenInfo.getRefreshToken());
            context.setExpiresIn(tokenInfo.getExpiresIn());
            context.setClientId(clientInfoHold.getAppKey());
            return context;
        } catch (Exception e) {
            logger.error("[code=" + code + "], 获取授权信息错误", e);
            throw new OAuth2AuthenticationException(e);
        }
    }
    private PermissionContext postPermissionByToken(String token) throws OAuthSystemException, OAuthProblemException {
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        OAuthClientRequest userInfoRequest = new OAuthPermissionClientRequest(clientInfoHold.getPermissionUrl())
                .setClientId(clientInfoHold.getAppKey())
                .setClientSecret(clientInfoHold.getAppSecret())
                .setAccessToken(token)
                .buildBodyMessage();
        //userInfoRequest.setHeader("Content-Type","application/x-www-form-urlencoded");
        userInfoRequest.setHeader(OAuth.HeaderType.CONTENT_TYPE, OAuth.ContentType.URL_ENCODED);
        OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.POST, OAuthResourceResponse.class);
        String body = resourceResponse.getBody();
        return JSON.parseObject(body, PermissionContext.class);
    }
    private OAuthAccessTokenResponse postTokenInfo(String code){
        logger.info("code = {}, accessTokenUrl = {}, clientId = {}, clientSecret = {}, redirectUrl = {}",
                code, clientInfoHold.getAccessTokenUrl(), clientInfoHold.getAppKey(),
                clientInfoHold.getAppSecret(), clientInfoHold.getRedirectUrl());
        try {
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            OAuthClientRequest accessTokenRequest = OAuthClientRequest
                    .tokenLocation(clientInfoHold.getAccessTokenUrl())
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setClientId(clientInfoHold.getAppKey())
                    .setClientSecret(clientInfoHold.getAppSecret())
                    .setCode(code)
                    .setRedirectURI(clientInfoHold.getRedirectUrl())
                    .buildBodyMessage();
            OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
            if(oAuthResponse == null || oAuthResponse.getAccessToken() == null){
                logger.warn("授权码[{}]获取授权错误", code);
                throw new OAuth2AuthenticationException("获取token异常");
            }
            return oAuthResponse;
        } catch (Exception e) {
            logger.error(String.format("授权码[%s]获取授权错误",code) , e);
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

    /**
     * 废弃请使用
     * @see #postTokenInfo(String)
     * @param code
     * @return
     */
    @Deprecated
    private String postUsername(String code) {
        try {
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            OAuthAccessTokenResponse tokenInfo = postTokenInfo(code);
            OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(clientInfoHold.getPermissionUsernameUrl())
                    .setAccessToken(tokenInfo.getAccessToken()).buildBodyMessage();
            OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
            return resourceResponse.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new OAuth2AuthenticationException(e);
        }
    }

    public void setClientInfoHold(ClientInfoHold clientInfoHold) {
        this.clientInfoHold = clientInfoHold;
    }
}
