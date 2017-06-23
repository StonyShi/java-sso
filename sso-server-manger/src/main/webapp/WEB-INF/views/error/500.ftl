<#--
 * @author Stony  
 * Created Date : 2016/4/11 14:29
-->
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title>Metronic | Extra - 500 Page Option 2</title>
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
    <link href="${media_css_url}/error.css" rel="stylesheet" type="text/css"/>
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="${media_image_url}/favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-500-full-page">
<div class="row-fluid">
    <div class="span12 page-500">
        <div class=" number">
            500
        </div>
        <div class=" details">
            <h3>Opps, Something went wrong.</h3>
            <p>
                We are fixing it!<br />
                Please come back in a while.<br /><br />
            </p>
            <p>Error Msg : ${msg!""}</p>
        </div>
    </div>
</div>
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<script src="${media_js_url}/jquery-1.8.3.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${media_js_url}/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="${media_js_url}/bootstrap.min.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="${media_js_url}/excanvas.js"></script>
<script src="${media_js_url}/respond.js"></script>
<![endif]-->
<script src="${media_js_url}/breakpoints.js" type="text/javascript"></script>
<!-- IMPORTANT! jquery.slimscroll.min.js depends on jquery-ui-1.10.1.custom.min.js -->
<script src="${media_js_url}/jquery.uniform.min.js" type="text/javascript" ></script>
<!-- END CORE PLUGINS -->
<script src="${media_js_url}/app.js"></script>
<script>
    jQuery(document).ready(function() {
        App.init();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>