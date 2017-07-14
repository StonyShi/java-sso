<!doctype html>
<html lang="en">
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
<body class="login">
<!-- BEGIN LOGO -->
<div class="logo">
    <img src="${media_image_url}/logo-big.png" alt="" />
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="form-vertical login-form" action="/login" method="post" id="loginForm">
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
                    <input class="m-wrap placeholder-no-fix" type="text" placeholder="Username" name="username" />
                    <input type="hidden" name="backUrl" value="${backUrl!""}">
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-lock"></i>
                    <input class="m-wrap placeholder-no-fix" type="password" placeholder="Password" name="password" />
                </div>
            </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn green pull-right" id="login_form_submit">
                登陆 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
        <div class="forget-password">
            <h4>忘记密码？</h4>
            <p>
                点击 <a href="javascript:;" class="" id="forget-password">这里</a>
                重置你的密码.
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
</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->
<div class="copyright">
    2017 &copy; Stony. Admin Dashboard Template.
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
<script type="text/javascript">
    var Login = function () {
        return {
            init: function () {
                $('.login-form').validate({
                    errorElement: 'label', //default input error message container
                    errorClass: 'help-inline', // default input error message class
                    focusInvalid: false, // do not focus the last invalid input
                    rules: {
                        username: {
                            minlength: 4,
                            required: true
                        },
                        password: {
                            minlength: 4,
                            required: true
                        },
                        remember: {
                            required: false
                        }
                    },
                    messages: {
                        username: {
                            required: "请输入用户名",
                            minlength: "用户名必需由两个字母组成"
                        },
                        password: {
                            required: "请输入密码",
                            minlength: "密码长度不能小于 4 个字母"
                        }
                    },
                    invalidHandler: function (event, validator) { //display error alert on VO submit
                        $('.alert-error', $('.login-form')).show();
                    },
                    highlight: function (element) { // hightlight error inputs
                        $(element)
                                .closest('.control-group').addClass('error'); // set error class to the control group
                    },
                    success: function (label) {
                        label.closest('.control-group').removeClass('error');
                        label.remove();
                    },
                    errorPlacement: function (error, element) {
                        error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
                    },
                    submitHandler: function (form) {
                        $("#login_form_submit").attr("disabled",true);
//                        $('.login-form').submit();
                        form.submit();
                    }
                });
                $('.login-form input').keypress(function (e) {
                    if (e.which == 13) {
                        if ($('.login-form').validate().form()) {
                            $("#login_form_submit").attr("disabled",true);
                            $('.login-form').submit();
                        }
                        return false;
                    }
                });
            }
        }
    }();
</script>
<script>
    jQuery(document).ready(function() {
        Login.init();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
</html>