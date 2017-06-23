package com.stony.sso.web.constants;


/**
 *
 * <h4>API请求头信息</h4>
 * <blockquote>
 * <table border=1 cellspacing=3 cellpadding=0 summary="request header">
 *     <tr bgcolor="#ccccff">
 *         <th align=left>请求参数
 *         <th align=left>说明
 *         <th align=left>必须
 *     <tr>
 *         <td>car-ticket</td>	<td>用户登录后分配的身份凭证</td>	<td>是</td>
 *     <tr>
 *         <td>car-userId</td>	<td>用户ID，登陆后传</td>	<td>是</td>
 *     <tr><td>car-sign</td>	<td>签名参考签名文档</td>	<td>是</td></tr>
 *     <tr><td>car-platform</td>	<td>平台ID</td>	<td>是</td></tr>
 *     <tr><td>car-channel</td>	<td>渠道ID</td>	<td>是</td></tr>
 *     <tr><td>car-request-time</td><td>请求时间(毫秒)<td> <td>是</td></tr>
 *     <tr><td>car-version</td>	<td>客户端版本</td>	<td>是</td></tr>
 *     <tr><td>car-imei</td>	<td>移动设备标识符</td>	<td>是</td></tr>
 *     <tr><td>car-key</td>	<td>客户端key</td>	<td>是</td></tr>
 * </table>
 * </blockquote>
 *
 * <p>
 * API请求头信息
 *
 * @author  Stony
 */
public abstract class HeaderConstant {

    public static final String PARAM_TICKET = "car-ticket";   //		是

    public static final String COOKIE_TICKET = "car_ticket";   //		是
    /** 用户登录后分配的身份凭证 **/
    public static final String HEADER_TICKET = "car-ticket";   //		是
    public static final String HEADER_TOKEN = "car-token";   //		是
    public static final String HEADER_USERID = "car-userId";  //	用户ID，登陆后传	是
    public static final String HEADER_SIGN = "car-sign";  //	签名，请求参数（FORM表单）按照key 正序后用&拼接字符串，字符串最后加上appKey=xxx，最后用MD5加密转大写，拼接示例：area=beijing&userId=1000&phone=19228990456&appKey=xxx 签名	是
    public static final String HEADER_PLATFORM = "car-platform";  //	平台ID	是
    /** 渠道ID	是 **/
    public static final String HEADER_CHANNEL = "car-channel";
    /** 请求时间（毫秒） **/
    public static final String HEADER_REQUEST_TIME = "car-request-time";  //		是
    /** 客户端版本 **/
    public static final String HEADER_VERSION = "car-version";  //		是
    /** 移动设备标识符	 **/
    public static final String HEADER_IMEI = "car-imei";  //	是
    public static final String HEADER_APPKEY = "car-key";  //	客户端key	是




    public static final String CONTEN_TYPE_JSON = "application/json;charset=utf-8";
    public static final String CONTEN_TYPE_CHARSET = "utf-8";

}
