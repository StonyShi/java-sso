package com.stony.sso.server.manger.controller;

import com.alibaba.fastjson.JSON;
import com.stony.sso.commons.DateUtils;
import com.stony.sso.commons.security.SecurityConstants;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.keys.OperationLogKeys;
import com.stony.sso.facade.service.*;
import com.stony.sso.commons.StringUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/1 </p>
 * <p>Time: 21:43 </p>
 * <p>Version: 1.0 </p>
 *
 * <h4>错误码说明</h4>
 * <blockquote>
 * <table border=0 cellspacing=3 cellpadding=4 summary="错误码.">
 *     <tr style="background-color: rgb(204, 204, 255);">
 *          <th align=left>错误码(error)
 *          <th align=left>错误编号(error_code)
 *          <th align=left>错误描述(error_description)
 *     <tr valign=top>
 *          <td><code>redirect_uri_mismatch</code>
 *          <td>21322
 *          <td>重定向地址不匹配
 *     <tr style="vertical-align: top; background-color: rgb(238, 238, 255);">
 *          <td><code>invalid_request</code>
 *          <td>21323
 *          <td>请求不合法
 *     <tr valign=top>
 *          <td><code>invalid_client</code>
 *          <td>21324
 *          <td>client_id或client_secret参数无效
 *     <tr style="vertical-align: top; background-color: rgb(238, 238, 255);">
 *          <td><code>invalid_grant</code>
 *          <td>21325
 *          <td>提供的Access Grant是无效的、过期的或已撤销的
 *     <tr valign=top>
 *          <td><code>unauthorized_client</code>
 *          <td>21326
 *          <td>客户端没有权限
 *     <tr style="vertical-align: top; background-color: rgb(238, 238, 255);">
 *          <td><code>expired_token</code>
 *          <td>21327
 *          <td>token过期
 *     <tr valign=top>
 *          <td><code>unsupported_grant_type</code>
 *          <td>21328
 *          <td>不支持的 GrantType
 *     <tr style="vertical-align: top; background-color: rgb(238, 238, 255);">
 *          <td><code>unsupported_response_type</code>
 *          <td>21329
 *          <td>不支持的 ResponseType
 *     <tr valign=top>
 *          <td><code>access_denied</code>
 *          <td>21330
 *          <td>用户或授权服务器拒绝授予数据访问权限
 *     <tr style="vertical-align: top; background-color: rgb(238, 238, 255);">
 *          <td><code>temporarily_unavailable</code>
 *          <td>21331
 *          <td>服务暂时无法访问
 *     <tr valign=top>
 *          <td><code>appkey permission denied</code>
 *          <td>21337
 *          <td>应用权限不足
 * </table>
 * </blockquote>
 */
@Controller
@RequestMapping("/oauth2")
public class OAuthController {

    private static final Logger logger = LoggerFactory.getLogger(OAuthController.class);

    @javax.annotation.Resource
    OAuthService oAuthService;

    @javax.annotation.Resource
    private AuthorizationService authorizationService;

    @javax.annotation.Resource
    private AppService appService;

    @javax.annotation.Resource
    private OperationLogService operationLogService;

    @javax.annotation.Resource
    private UserService userService;

    /**
     * <h4>参数说明</h4>
     * <blockquote>
     * <table border=0 cellspacing=3 cellpadding=4 summary="获取code.">
     *     <tr style="background-color: rgb(204, 204, 255);">
     *          <th align=left>字段名
     *          <th align=left>含义
     *          <th align=left>类型
     *          <th align=left>必须
     *     <tr valign=top>
     *          <td><code>client_id</code>
     *          <td>应用客户
     *          <td>string
     *          <td>是
     *     <tr style="vertical-align: top; background-color: rgb(238, 238, 255);">
     *          <td><code>redirect_uri</code>
     *          <td>授权后重定向的回调链接地址
     *          <td>string
     *          <td>是
     *     <tr valign=top>
     *          <td><code>response_type</code>
     *          <td>返回类型，请填写code
     *          <td>string
     *          <td>是
     *     <tr style="vertical-align: top; background-color: rgb(238, 238, 255);">
     *          <td><code>scope</code>
     *          <td>应用授权作用域
     *          <td>string
     *          <td>否（可选）
     * </table>
     * </blockquote>
     *  授权控制
     * @param model
     * @param request /authorize?client_id=xxx&response_type=code&redirect_uri=http://xxx.com/path
     * @return http://xxx.com/path?code=52b1832f5dff68122f4f00ae   code参数（授权码）,客户端根据授权码去换取access token
     * @throws URISyntaxException
     * @throws OAuthSystemException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/authorize", method = {RequestMethod.POST, RequestMethod.GET})
    public Object authorize(Model model, HttpServletRequest request) throws URISyntaxException, OAuthSystemException {
        logger.info("Enter");
        try {
            //构建OAuth 授权请求
            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);

            //检查传入的客户端id是否正确
            if (!oAuthService.checkClientId(oauthRequest.getClientId())) {
                OAuthResponse response =
                        OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                                .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                                .setErrorDescription(SecurityConstants.INVALID_CLIENT_DESCRIPTION)
                                .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.BAD_REQUEST);
            }


            Subject subject = SecurityUtils.getSubject();
            //如果用户没有登录，跳转到登陆页面
            if(!subject.isAuthenticated()) {
                if(!login(subject, request)) {//登录失败时跳转到登陆页面getClientId appKey
                    App app = appService.findAppByAppKey(oauthRequest.getClientId());
                    logger.info("app = {}", app);
                    ModelAndView view = new ModelAndView();
                    view.setViewName("oauth2login");
                    view.addObject("client", app);
                    view.addObject(OAuth.OAUTH_CLIENT_ID, oauthRequest.getClientId());
                    view.addObject(OAuth.OAUTH_REDIRECT_URI, oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI));
                    view.addObject(OAuth.OAUTH_RESPONSE_TYPE, oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE));
                    logger.info("ModelMap = {}", view.getModelMap());
                    return view;
                }
            }

            String username = (String)subject.getPrincipal();
            User user =userService.findByUsername(username);
            operationLogService.insertOperation(new OperationLog(OperationLogKeys.LOGIN_OPERATION, user.getId()));

            //生成授权码
            String authorizationCode = null;
            //responseType目前仅支持CODE，另外还有TOKEN
            String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
            if (responseType.equals(OAuth.OAUTH_CODE)) {
                OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
                authorizationCode = oauthIssuerImpl.authorizationCode();
                oAuthService.addAuthCode(authorizationCode, username);
            }

            //进行OAuth响应构建
            OAuthASResponse.OAuthAuthorizationResponseBuilder builder =
                    OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);
            //设置授权码
            builder.setCode(authorizationCode);
            //得到到客户端重定向地址
            String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
            logger.info("redirectURI = ", redirectURI);
            //构建响应
            final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();

            //根据OAuthResponse返回ResponseEntity响应
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(response.getLocationUri()));
            return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
        } catch (OAuthProblemException e) {

            //出错处理
            String redirectUri = e.getRedirectUri();
            if (OAuthUtils.isEmpty(redirectUri)) {
                //告诉客户端没有传入redirectUri直接报错
                return new ResponseEntity("OAuth callback url needs to be provided by client!!!", HttpStatus.NOT_FOUND);
            }

            //返回错误消息（如?error=）
            final OAuthResponse response = OAuthASResponse
                    .errorResponse(HttpServletResponse.SC_FOUND)
                    .error(e)
                    .location(redirectUri)
                    .buildQueryMessage();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(response.getLocationUri()));
            return new ResponseEntity(headers, HttpStatus.NOT_FOUND);
        }
    }

    private boolean login(Subject subject, HttpServletRequest request) {
        if(OAuth.HttpMethod.POST.equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        String username = request.getParameter(OAuth.OAUTH_USERNAME);
        String password = request.getParameter(OAuth.OAUTH_PASSWORD);
        logger.debug("username = {}, password = {}", username, password);
        if(org.springframework.util.StringUtils.isEmpty(username) || org.springframework.util.StringUtils.isEmpty(password)) {
            return false;
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
            return true;
        } catch (Exception e) {
            request.setAttribute("error", "登录失败:" + e.getClass().getName());
            return false;
        }
    }

    /**
     * <h4>请求参数说明</h4>
     * <blockquote>
     * <table border=0 cellspacing=3 cellpadding=4 summary="code获取token.">
     *     <tr style="background-color: rgb(204, 204, 255);">
     *          <th align=left>字段名
     *          <th align=left>含义
     *          <th align=left>类型
     *          <th align=left>必须
     *     <tr valign=top>
     *          <td><code>client_id</code>
     *          <td>客户应用标识
     *          <td>string
     *          <td>是
     *     <tr style="vertical-align: top; background-color: rgb(238, 238, 255);">
     *          <td><code>client_secret</code>
     *          <td>客户应用安全码
     *          <td>string
     *          <td>是
     *     <tr valign=top>
     *          <td><code>grant_type</code>
     *          <td>授权类型，填写为authorization_code
     *          <td>string
     *          <td>是
     *     <tr style="vertical-align: top; background-color: rgb(238, 238, 255);">
     *          <td><code>code</code>
     *          <td>授权码，code
     *          <td>string
     *          <td>是
     * </table>
     * </blockquote>
     * 访问令牌控制<br/>
     * 该控制器会验证client_id、client_secret、auth code的正确性，如果错误会返回相应的错误；<br/>
     * 如果验证通过会生成并返回相应的访问令牌access token。<br/>
     * @param request
     *   /access_token?client_id=cc1234&client_secret=sc123232&grant_type=authorization_code&code=ddd&redirect_uri=http://xxx.com/path
     * @return
     * <h4>返回参数说明</h4>
     * <blockquote>
     * <table border=0 cellspacing=3 cellpadding=4 summary="私有协议头定义.">
     *     <tr style="background-color: rgb(204, 204, 255);">
     *          <th align=left>字段名
     *          <th align=left>含义
     *          <th align=left>类型
     *          <th align=left>必须
     *     <tr valign=top>
     *          <td><code>access_token</code>
     *          <td>授权接口调用凭证
     *          <td>string
     *          <td>是
     *     <tr style="vertical-align: top; background-color: rgb(238, 238, 255);">
     *          <td><code>expires_in</code>
     *          <td>access_token超时时间（秒）
     *          <td>long
     *          <td>是
     *     <tr valign=top>
     *          <td><code>refresh_token</code>
     *          <td>刷新access_token
     *          <td>string
     *          <td>是
     *     <tr style="vertical-align: top; background-color: rgb(238, 238, 255);">
     *          <td><code>scope</code>
     *          <td>授权的作用域，使用逗号（,）分隔
     *          <td>string
     *          <td>是
     * </table>
     * </blockquote>
     * @throws URISyntaxException
     * @throws OAuthSystemException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/access_token")
    public HttpEntity accessToken(HttpServletRequest request)throws URISyntaxException, OAuthSystemException {
        logger.info("Enter");
        try {
            //构建OAuth请求
            OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);

            //检查提交的客户端id是否正确 appKey
            if (!oAuthService.checkClientId(oauthRequest.getClientId())) {
                OAuthResponse response =
                        OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                                .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                                .setErrorDescription(SecurityConstants.INVALID_CLIENT_DESCRIPTION)
                                .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.BAD_REQUEST);
            }

            // 检查客户端安全KEY是否正确 appSecret
            if (!oAuthService.checkClientSecret(oauthRequest.getClientSecret())) {
                OAuthResponse response =
                        OAuthASResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                                .setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT)
                                .setErrorDescription(SecurityConstants.INVALID_CLIENT_DESCRIPTION)
                                .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.UNAUTHORIZED);
            }
            // code
            String authCode = oauthRequest.getParam(OAuth.OAUTH_CODE);
            // 检查验证类型，此处只检查AUTHORIZATION_CODE类型，其他的还有PASSWORD或REFRESH_TOKEN
            if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())) {
                if (!oAuthService.checkAuthCode(authCode)) {
                    OAuthResponse response = OAuthASResponse
                            .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                            .setError(OAuthError.TokenResponse.INVALID_GRANT)
                            .setErrorDescription("错误的授权码")
                            .buildJSONMessage();
                    return new ResponseEntity(response.getBody(), HttpStatus.BAD_REQUEST);
                }
            }

            //生成Access Token
            OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            final String accessToken = oauthIssuerImpl.accessToken();
            final String refreshToken = oauthIssuerImpl.refreshToken();
            final String userName = oAuthService.getUsernameByAuthCode(authCode);
            TokenInfo token = new TokenInfo();
            token.setUsername(userName);
            token.setExpireIn(oAuthService.getExpireIn());
            token.setToken(accessToken);
            token.setClientId(oauthRequest.getClientId());
            token.setClientSecret(oauthRequest.getClientSecret());

            TokenInfo token2 = token.clone();
            token2.setToken(refreshToken);
            token2.setExpireIn(oAuthService.getRefreshTokenExpireIn());
            //保存token到缓冲
            oAuthService.addAccessToken(accessToken, token);
            oAuthService.addRefreshToken(refreshToken, token2);

            //生成OAuth响应
            OAuthResponse response = OAuthASResponse
                    .tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(accessToken)
                    .setRefreshToken(refreshToken)
                    .setExpiresIn(String.valueOf(oAuthService.getExpireIn()))
                    .buildJSONMessage();

            //根据OAuthResponse生成ResponseEntity
            return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));

        } catch (OAuthProblemException e) {
            //构建错误响应
            OAuthResponse res = OAuthASResponse
                    .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .error(e)
                    .buildJSONMessage();
            return new ResponseEntity(res.getBody(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * <h4>请求参数说明</h4>
     * <blockquote>
     * <table border=0 cellspacing=3 cellpadding=4 summary="刷新token.">
     *     <tr style="background-color: rgb(204, 204, 255);">
     *          <th align=left>字段名
     *          <th align=left>含义
     *          <th align=left>类型
     *          <th align=left>必须
     *     <tr valign=top>
     *          <td><code>client_id</code>
     *          <td>客户应用标识
     *          <td>string
     *          <td>是
     *     <tr style="vertical-align: top; background-color: rgb(238, 238, 255);">
     *          <td><code>grant_type</code>
     *          <td>授权类型，填写为refresh_token
     *          <td>string
     *          <td>是
     *     <tr valign=top>
     *          <td><code>refresh_token</code>
     *          <td>refresh_token参数
     *          <td>string
     *          <td>是
     * </table>
     * </blockquote>
     * 授权token刷新<br/>
     * @param request
     * @return
     * 返回参数请查看
     * @see #accessToken(HttpServletRequest)
     * @throws URISyntaxException
     * @throws OAuthSystemException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/refresh_token")
    public HttpEntity refreshToken(HttpServletRequest request)throws URISyntaxException, OAuthSystemException {
        logger.info("Enter");
        try {
            //构建OAuth请求
            OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);

            final String refreshToken = oauthRequest.getRefreshToken();

            //检查提交的客户端id是否正确 appKey
            if (!oAuthService.checkClientId(oauthRequest.getClientId())) {
                OAuthResponse response =
                        OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                                .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                                .setErrorDescription(SecurityConstants.INVALID_CLIENT_DESCRIPTION)
                                .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.BAD_REQUEST);
            }


            final TokenInfo token = oAuthService.getToken(refreshToken);
            //验证 refresh_token
            if (token == null || token.getUsername() == null) {
                // 如果不存在/过期了，返回未验证错误，需重新验证
                return getUnauthorizedResponseEntity("this refresh_token invalid.");
            }


            //生成Access Token
            OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            final String accessToken = oauthIssuerImpl.accessToken();

            TokenInfo token2 = token.clone();
            token2.setToken(accessToken);
            token2.setExpireIn(oAuthService.getExpireIn());

            //保存token到缓冲
            oAuthService.addAccessToken(accessToken, token2);

            //生成OAuth响应
            OAuthResponse response = OAuthASResponse
                    .tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(accessToken)
                    .setRefreshToken(refreshToken)
                    .setExpiresIn(String.valueOf(oAuthService.getExpireIn()))
                    .buildJSONMessage();

            //根据OAuthResponse生成ResponseEntity
            return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));

        } catch (OAuthProblemException e) {
            //构建错误响应
            OAuthResponse res = OAuthASResponse
                    .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .error(e)
                    .buildJSONMessage();
            return new ResponseEntity(res.getBody(), HttpStatus.BAD_REQUEST);
        }
    }

    MediaType APPLICATION_JSON = MediaType.valueOf("application/json;charset=UTF-8");
    /**
     * 该控制器会验证access token的有效性；如果无效了将返回相应的错误，客户端再重新进行授权；<br/>
     * 如果有效，则返回当前登录用户的用户名。<br/>
     * @param request access_token=828beda9
     * @return
     * @throws OAuthSystemException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/permission/username")
    public HttpEntity userInfo(HttpServletRequest request) throws OAuthSystemException {
        logger.info("Enter");
        try {
            //构建OAuth资源请求
            OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.QUERY);
            //获取 access_token
            String accessToken = oauthRequest.getAccessToken();

            //验证 access_token
            if (!oAuthService.checkAccessToken(accessToken)) {
                // 如果不存在/过期了，返回未验证错误，需重新验证
                return getUnauthorizedResponseEntity("this access_token invalid.");
            }
            //返回用户名
            TokenInfo token = oAuthService.getToken(accessToken);

            return new ResponseEntity(token.getUsername(), HttpStatus.OK);
        } catch (OAuthProblemException e) {
            //检查是否设置了错误码
            String errorCode = e.getError();
            if (OAuthUtils.isEmpty(errorCode)) {
                return getUnauthorizedResponseEntity("code id not found");
            }
            return getErrorResponseEntity(e);
        }
    }

    /**
     *
     * @param request
     * @return
     * @throws OAuthSystemException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    public HttpEntity permission(HttpServletRequest request) throws OAuthSystemException {
        logger.info("Enter");
        try {
            //构建OAuth资源请求
            OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.BODY);
            //获取Access Token
            String accessToken = oauthRequest.getAccessToken();


            //返回 TokenInfo
            TokenInfo token = oAuthService.getToken(accessToken);

            //验证 Access Token
            if (token == null || token.getUsername() == null) {
                // 如果不存在/过期了，返回未验证错误，需重新验证
                return getUnauthorizedResponseEntity("this token invalid");
            }
            //验证 用户
            User user = userService.findByUsername(token.getUsername());
            if(user == null || StringUtils.isEmpty(user.getUsername())){
                return getUnauthorizedResponseEntity("user not found");
            }

            String username = user.getUsername();
            String clientId = request.getParameter(OAuth.OAUTH_CLIENT_ID);
            if(StringUtils.isEmpty(clientId)){
                return getUnauthorizedResponseEntity("client id not found");
            }
            //返回资源
            PermissionEntity entity = authorizationService.getPermissionEntity(clientId, username);
            entity.setUsername(username);
            entity.setResponseTime(DateUtils.dateTimeString());
            entity.setUser(user);

            HttpHeaders headers = new HttpHeaders();
            headers.add(OAuth.HeaderType.CONTENT_TYPE, "application/json;charset=UTF-8");
            return new ResponseEntity(JSON.toJSON(entity), headers, HttpStatus.OK);
        } catch (OAuthProblemException e) {
            //检查是否设置了错误码
            String errorCode = e.getError();
            if (OAuthUtils.isEmpty(errorCode)) {
                return getUnauthorizedResponseEntity("code id not found");
            }
            return getErrorResponseEntity(e);
        }
    }

    private ResponseEntity getUnauthorizedResponseEntity(String errDes) throws OAuthSystemException {
        OAuthResponse oauthResponse = OAuthRSResponse
                .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                .setRealm(SecurityConstants.RESOURCE_SERVER_NAME)
                .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
                .setErrorDescription(errDes)
                .buildHeaderMessage();

        HttpHeaders headers = new HttpHeaders();
        headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
        return new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
    }
    private ResponseEntity getErrorResponseEntity(OAuthProblemException e) throws OAuthSystemException {
        OAuthResponse oauthResponse = OAuthRSResponse
                .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                .setRealm(SecurityConstants.RESOURCE_SERVER_NAME)
                .setError(e.getError())
                .setErrorDescription(e.getDescription())
                .setErrorUri(e.getUri())
                .buildHeaderMessage();
        HttpHeaders headers = new HttpHeaders();
        headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
        return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
    }
}
