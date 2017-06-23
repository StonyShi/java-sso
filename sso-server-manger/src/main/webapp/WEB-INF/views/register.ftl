<#--
 * Created by Stony on 2016/4/28.
-->
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title>Metronic | Login Page</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="${media_css_url}/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${media_css_url}/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="${media_css_url}/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${media_css_url}/style-metro.css" rel="stylesheet" type="text/css"/>
    <link href="${media_css_url}/style.css" rel="stylesheet" type="text/css"/>
    <link href="${media_css_url}/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="${media_css_url}/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${media_css_url}/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="${media_css_url}/login.css" rel="stylesheet" type="text/css"/>
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="${media_image_url}/favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN LOGO -->
<div class="logo">
    <img src="${media_image_url}/logo-big.png" alt="" />
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="form-vertical login-form" action="/login" method="post">
        <h3 class="form-title">系统登陆</h3>
        <div class="error">${error!""}</div>
        <div class="alert alert-error hide">
            <button class="close" data-dismiss="alert"></button>
            <span>输入用户名和密码.</span>
        </div>
        <div class="control-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-user"></i>
                    <input class="m-wrap placeholder-no-fix" type="text" placeholder="Username" name="username" value="admin"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-lock"></i>
                    <input class="m-wrap placeholder-no-fix" type="password" placeholder="Password" name="password" value="123456"/>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <label class="checkbox">
                <input type="checkbox" name="remember" value="1"/> 记住登陆
            </label>
            <button type="submit" class="btn green pull-right">
                Login <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
        <div class="forget-password">
            <h4>忘记密码？</h4>
            <p>
                点击 <a href="javascript:;" class="" id="forget-password">这里</a>
                重置你的密码.
            </p>
        </div>
        <div class="create-account">
            <p>
                还没有账户 ?&nbsp;
                <a href="javascript:;" id="register-btn" class="">创建一个账户</a>
            </p>
        </div>
    </form>
    <!-- END LOGIN FORM -->
    <!-- BEGIN FORGOT PASSWORD FORM -->
    <form class="form-vertical forget-form" action="/forget" method="post">
        <h3 class="">重置密码 ?</h3>
        <p>输入你的邮箱重置密码.</p>
        <div class="control-group">
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-envelope"></i>
                    <input class="m-wrap placeholder-no-fix" type="text" placeholder="Email" name="email" />
                </div>
            </div>
        </div>
        <div class="form-actions">
            <button type="button" id="back-btn" class="btn">
                <i class="m-icon-swapleft"></i> Back
            </button>
            <button type="submit" class="btn green pull-right">
                Submit <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END FORGOT PASSWORD FORM -->
    <!-- BEGIN REGISTRATION FORM -->
    <form class="form-vertical register-form" action="/register" method="post">
        <h3 class="">注册</h3>
        <p>填写注册信息:</p>
        <div class="control-group">
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-user"></i>
                    <input class="m-wrap placeholder-no-fix" type="text" placeholder="Username" name="username"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-lock"></i>
                    <input class="m-wrap placeholder-no-fix" type="password" id="register_password" placeholder="Password" name="password"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label visible-ie8 visible-ie9">Re-type Your Password</label>
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-ok"></i>
                    <input class="m-wrap placeholder-no-fix" type="password" placeholder="Re-type Your Password" name="rpassword"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">邮箱</label>
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-envelope"></i>
                    <input class="m-wrap placeholder-no-fix" type="text" placeholder="Email" name="email"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <label class="checkbox">
                    <input type="checkbox" name="tnc"/> I agree to the <a href="#">Terms of Service</a> and <a href="#">Privacy Policy</a>
                </label>
                <div id="register_tnc_error"></div>
            </div>
        </div>
        <div class="form-actions">
            <button id="register-back-btn" type="button" class="btn">
                <i class="m-icon-swapleft"></i>  Back
            </button>
            <button type="submit" id="register-submit-btn" class="btn green pull-right">
                Sign Up <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END REGISTRATION FORM -->
</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->
<div class="copyright">
    2016 &copy; 01zhuanche. Admin Dashboard Template.
</div>
<!-- END COPYRIGHT -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<script src="${media_js_url}/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${media_js_url}/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="${media_js_url}/bootstrap.min.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="${media_js_url}/excanvas.min.js"></script>
<script src="${media_js_url}/respond.min.js"></script>
<![endif]-->
<script src="${media_js_url}/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery.cookie.min.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery.uniform.min.js" type="text/javascript" ></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${media_js_url}/jquery.validate.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="/static/js/app.js" type="text/javascript"></script>
<script src="/static/js/login.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function() {
        Login.init();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>