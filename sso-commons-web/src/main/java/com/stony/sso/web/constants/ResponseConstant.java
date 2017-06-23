package com.stony.sso.web.constants;

/**
 * @author Stony Created Date : 2016/4/20  17:45
 */
public abstract class ResponseConstant {

    /**
     * 成功
     **/
    public static final int CODE_SUCUESS = 100000;
    public static final String MSG_SUCUESS = "成功";
    /**
     * 身份验证无效
     **/
    public static final int CODE_TICKET_INVALID = 100001;
    public static final String MSG_TICKET_INVALID = "身份验证无效";

    /**
     * 签名无效
     **/
    public static final int CODE_SIGN_INVALID = 100002;
    public static final String MSG_SIGN_INVALID = "请求签名无效";


    /**
     * 原密码无效
     **/
    public static final int CODE_OLDPASSWD_INVALID = 100003;
    public static final String MSG_OLDPASSWD_INVALID = "原密码无效";


    /**
     * 服务器出现错误请联系管理员
     **/
    public static final int CODE_ERROR_INVALID = 100004;
    public static final String MSG_ERROR_INVALID = "服务器出现错误请联系管理员";


    /**
     * 无效的参数
     **/
    public static final int CODE_PARM_INVALID = 100005;
    public static final String MSG_PARM_INVALID = "无效的参数";

    public static final int CODE_LOGIN_INVALID = 100006;
    public static final String MSG_LOGIN_INVALID = "登陆错误";



    /**
     * 取消订单失败
     **/
    public static final int CODE_CANCLE_ORDER_INVALID = 100007;
    public static final String MSG_CANCLE_ORDER_INVALID = "取消订单失败";


    /**
     * 创建订单失败
     **/
    public static final int CODE_CREATE_ORDER_INVALID = 100008;
    public static final String MSG_CREATE_ORDER_INVALID = "创建订单失败";

    /**
     * 其他错误，未知错误
     */
    public static final int CODE_OTHER_INVALID = 200000;
    public static final String MSG_OTHER_INVALID = "其他错误";

    /***
     * 无效的OrderId
     */
    public static final int CODE_ORDERID_INVALID = 100009;
    public static final String MSG_ORDERID_INVALID = "订单ID无效";
 


    /***
     * 未获取到司机位置信息
     */
    public static final int CODE_DRIVER_LOCATION_INVALID = 100010;
    public static final String MSG_DRIVER_LOCATION_INVALID = "未获取到司机位置信息";


 


    public static final int CODE_ORDER_STATUS_INVALID = 100010;
    public static final String MSG_ORDER_STATUS_INVALID = "订单状态变更错误";

	 public static final int    CODE_PASSENGER_ORDER_INVALID = 100011;
    public static final String  MSG_PASSENGER_ORDER_INVALID = "用户支付预定金失败";


    /**
     * 异议订单失败
     **/
    public static final int CODE_EXCEPTION_ORDER_INVALID = 100012;
    public static final String MSG_EXCEPTION_ORDER_INVALID = "异议订单失败";

    /**
     * 参数校验异常
     */
    public static final int CODE_PARAMETER_INVALID = 100013;

    /**
     * 司机出发
     */
    public static final int CODE_SETOUT_INVALID = 100014;
    public static final String MSG_SETOUT_INVALID = "司机出发错误";

    /**
     * 司机达到
     */
    public static final int CODE_ARRIVED_INVALID = 100015;
    public static final String MSG_ARRIVED_INVALID = "司机达到错误";



    /**
     * 更新账户错误
     */
    public static final int CODE_UPDATE_ACCOUNT_INVALID = 100016;

    /**
     * 完成服务失败
     */
    public static final int CODE_FINISH_SERVICE_FAIL = 100017;
    public static final String MSG_FINISH_SERVICE_FAIL = "完成服务失败";
    
    /**
     * 微信版本号过低
     */
    public static final int CODE_WEIXIN_VERSION_LOW = 100018;
    public static final String MSG_WEIXIN_VERSION_LOW = "微信版本号太低,无法使用微信支付";
    
    /**
     * 系统异常支付失败
     */
    public static final int CODE_WEIXIN_PAY_FAIL = 100019;
    public static final String MSG_WEIXIN_PAY_FAIL = "系统异常,支付失败";

    /**
     * 身份信息验证无效
     */
    public static final int CODE_IDENTITY_INFO_INVALID = 100020;
    public static final String MSG_IDENTITY_INFO_INVALID = "身份信息验证无效";
    
    /**
     * 获取最近站点失败
     */
    public static final int CODE_GET_NEAR_SITE_FAIL = 100021;
    public static final String MSG_GET_NEAR_SITE_FAIL = "获取最近站点失败";
    

    /**
     * 手机号已注册
     */
    public static final int CODE_PHONE_REGISTER_FAIL = 100022;
    public static final String MSG_PHONE_REGISTER_FAIL = "该手机号已注册";

}
