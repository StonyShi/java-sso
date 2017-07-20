<#--
 * author: Stony  Date: 2016/4/28 Time: 14:13
-->
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title>Metronic | Admin Dashboard Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <#include './common/global_styles.ftl'>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="${media_css_url}/jquery.gritter.css" rel="stylesheet" type="text/css"/>
    <link href="${media_css_url}/daterangepicker.css" rel="stylesheet" type="text/css" />
    <link href="${media_css_url}/fullcalendar.css" rel="stylesheet" type="text/css"/>
    <link href="${media_css_url}/jqvmap.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="${media_css_url}/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="${media_image_url}/favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
<!-- BEGIN HEADER -->
<#include './common/header_navbar.ftl'>
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <!-- BEGIN SIDEBAR -->
    <#include './common/sidebar.ftl'>
    <!-- END SIDEBAR -->
    <!-- BEGIN PAGE -->
    <div class="page-content">
        <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
        <div id="portlet-config" class="modal hide">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"></button>
                <h3>Widget Settings</h3>
            </div>
            <div class="modal-body">
                Widget settings form goes here
            </div>
        </div>
        <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN STYLE CUSTOMIZER -->
                    <@shiro.hasPermission name="theme:color">
                        <#include './common/theme_color.ftl'/>
                    </@shiro.hasPermission>
                    <!-- END BEGIN STYLE CUSTOMIZER -->
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        Dashboard <small>-</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/index">Home</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">Dashboard</a></li>
                        <li class="pull-right no-text-shadow">
                            <div id="dashboard-report-range" class="dashboard-date-range tooltips no-tooltip-on-touch-device responsive" data-tablet="" data-desktop="tooltips" data-placement="top" data-original-title="Change dashboard date range">
                                <i class="icon-calendar"></i>
                                <span></span>
                                <i class="icon-angle-down"></i>
                            </div>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <div id="dashboard">
                <!-- BEGIN DASHBOARD STATS -->
                <div class="row-fluid">
                    <#assign index = 1>
                    <#assign colours = ["red","blue","green","yellow","purple","pink","orange","red","gray"]>
                    <#list list as item>
                        <div class="span3 responsive" data-tablet="span6  ${(index%2=0)?string('','fix-offset')}" data-desktop="span3">
                            <div class="dashboard-stat ${colours[index]}">
                                <div class="visual">
                                    <i class="${(item.icon)!"icon-asterisk"}"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                        ${item.name}
                                    </div>
                                    <div class="desc">
                                        ${(item.desc)!""}
                                    </div>
                                </div>
                                <a class="more" href="${(item.address)!"#"}">
                                    View more <i class="m-icon-swapright m-icon-white"></i>
                                </a>
                            </div>
                        </div>
                        <#assign index = index+1>
                        <#if index == 9>
                            <#assign index = 1>
                        </#if>
                    </#list>

                </div>
                <!-- END DASHBOARD STATS -->
                <div class="clearfix"></div>
                <div class="row-fluid">
                    <div class="span6">
                        <!-- BEGIN PORTLET-->
                        <div class="portlet solid bordered light-grey">
                            <div class="portlet-title">
                                <div class="caption"><i class="icon-bar-chart"></i>Site Visits</div>
                                <div class="tools">
                                    <div class="btn-group pull-right" data-toggle="buttons-radio">
                                        <a href="" class="btn mini">Users</a>
                                        <a href="" class="btn mini active">Feedbacks</a>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div id="site_statistics_loading">
                                    <img src="${media_image_url}/loading.gif" alt="loading" />
                                </div>
                                <div id="site_statistics_content" class="hide">
                                    <div id="site_statistics" class="chart"></div>
                                </div>
                            </div>
                        </div>
                        <!-- END PORTLET-->
                    </div>
                    <div class="span6">
                        <!-- BEGIN PORTLET-->
                        <div class="portlet solid light-grey bordered">
                            <div class="portlet-title">
                                <div class="caption"><i class="icon-bullhorn"></i>Activities</div>
                                <div class="tools">
                                    <div class="btn-group pull-right" data-toggle="buttons-radio">
                                        <a href="" class="btn blue mini active">Users</a>
                                        <a href="" class="btn blue mini">Orders</a>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div id="site_activities_loading">
                                    <img src="${media_image_url}/loading.gif" alt="loading" />
                                </div>
                                <div id="site_activities_content" class="hide">
                                    <div id="site_activities" style="height:100px;"></div>
                                </div>
                            </div>
                        </div>
                        <!-- END PORTLET-->
                        <!-- BEGIN PORTLET-->
                        <div class="portlet solid bordered light-grey">
                            <div class="portlet-title">
                                <div class="caption"><i class="icon-signal"></i>Server Load</div>
                                <div class="tools">
                                    <div class="btn-group pull-right" data-toggle="buttons-radio">
                                        <a href="" class="btn red mini active">
                                            <span class="hidden-phone">Database</span>
                                            <span class="visible-phone">DB</span></a>
                                        <a href="" class="btn red mini">Web</a>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div id="load_statistics_loading">
                                    <img src="${media_image_url}/loading.gif" alt="loading" />
                                </div>
                                <div id="load_statistics_content" class="hide">
                                    <div id="load_statistics" style="height:108px;"></div>
                                </div>
                            </div>
                        </div>
                        <!-- END PORTLET-->
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="row-fluid">
                    <div class="span6">
                        <div class="portlet box purple">
                            <div class="portlet-title">
                                <div class="caption"><i class="icon-calendar"></i>General Stats</div>
                                <div class="actions">
                                    <a href="javascript:;" class="btn yellow easy-pie-chart-reload"><i class="icon-repeat"></i> Reload</a>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div class="row-fluid">
                                    <div class="span4">
                                        <div class="easy-pie-chart">
                                            <div class="number transactions"  data-percent="55"><span>+55</span>%</div>
                                            <a class="title" href="#">Transactions <i class="m-icon-swapright"></i></a>
                                        </div>
                                    </div>
                                    <div class="margin-bottom-10 visible-phone"></div>
                                    <div class="span4">
                                        <div class="easy-pie-chart">
                                            <div class="number visits"  data-percent="85"><span>+85</span>%</div>
                                            <a class="title" href="#">New Visits <i class="m-icon-swapright"></i></a>
                                        </div>
                                    </div>
                                    <div class="margin-bottom-10 visible-phone"></div>
                                    <div class="span4">
                                        <div class="easy-pie-chart">
                                            <div class="number bounce"  data-percent="46"><span>-46</span>%</div>
                                            <a class="title" href="#">Bounce <i class="m-icon-swapright"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="span6">
                        <div class="portlet box blue">
                            <div class="portlet-title">
                                <div class="caption"><i class="icon-calendar"></i>Server Stats</div>
                                <div class="tools">
                                    <a href="" class="collapse"></a>
                                    <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                    <a href="" class="reload"></a>
                                    <a href="" class="remove"></a>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div class="row-fluid">
                                    <div class="span4">
                                        <div class="sparkline-chart">
                                            <div class="number" id="sparkline_bar"></div>
                                            <a class="title" href="#">Network <i class="m-icon-swapright"></i></a>
                                        </div>
                                    </div>
                                    <div class="margin-bottom-10 visible-phone"></div>
                                    <div class="span4">
                                        <div class="sparkline-chart">
                                            <div class="number" id="sparkline_bar2"></div>
                                            <a class="title" href="#">CPU Load <i class="m-icon-swapright"></i></a>
                                        </div>
                                    </div>
                                    <div class="margin-bottom-10 visible-phone"></div>
                                    <div class="span4">
                                        <div class="sparkline-chart">
                                            <div class="number" id="sparkline_line"></div>
                                            <a class="title" href="#">Load Rate <i class="m-icon-swapright"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>

                <div class="row-fluid">
                    <div class="span6">
                        <!-- BEGIN PORTLET-->
                        <div class="portlet box blue calendar">
                            <div class="portlet-title">
                                <div class="caption"><i class="icon-calendar"></i>Calendar</div>
                            </div>
                            <div class="portlet-body light-grey">
                                <div id="calendar">
                                </div>
                            </div>
                        </div>
                        <!-- END PORTLET-->
                    </div>
                    <div class="span6">
                        <!-- BEGIN PORTLET-->
                        <div class="portlet">
                            <div class="portlet-title line">
                                <div class="caption"><i class="icon-comments"></i>Chats</div>
                                <div class="tools">
                                    <a href="" class="collapse"></a>
                                    <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                    <a href="" class="reload"></a>
                                    <a href="" class="remove"></a>
                                </div>
                            </div>
                            <div class="portlet-body" id="chats">
                                <div class="scroller" data-height="435px" data-always-visible="1" data-rail-visible1="1">
                                    <ul class="chats">
                                        <li class="in">
                                            <img class="avatar" alt="" src="${media_image_url}/avatar1.jpg" />
                                            <div class="message">
                                                <span class="arrow"></span>
                                                <a href="#" class="name">Bob Nilson</a>
                                                <span class="datetime">at Jul 25, 2012 11:09</span>
													<span class="body">
													Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
													</span>
                                            </div>
                                        </li>
                                        <li class="out">
                                            <img class="avatar" alt="" src="${media_image_url}/avatar2.jpg" />
                                            <div class="message">
                                                <span class="arrow"></span>
                                                <a href="#" class="name">Lisa Wong</a>
                                                <span class="datetime">at Jul 25, 2012 11:09</span>
													<span class="body">
													Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
													</span>
                                            </div>
                                        </li>
                                        <li class="in">
                                            <img class="avatar" alt="" src="${media_image_url}/avatar1.jpg" />
                                            <div class="message">
                                                <span class="arrow"></span>
                                                <a href="#" class="name">Bob Nilson</a>
                                                <span class="datetime">at Jul 25, 2012 11:09</span>
													<span class="body">
													Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
													</span>
                                            </div>
                                        </li>
                                        <li class="out">
                                            <img class="avatar" alt="" src="${media_image_url}/avatar3.jpg" />
                                            <div class="message">
                                                <span class="arrow"></span>
                                                <a href="#" class="name">Richard Doe</a>
                                                <span class="datetime">at Jul 25, 2012 11:09</span>
													<span class="body">
													Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
													</span>
                                            </div>
                                        </li>
                                        <li class="in">
                                            <img class="avatar" alt="" src="${media_image_url}/avatar3.jpg" />
                                            <div class="message">
                                                <span class="arrow"></span>
                                                <a href="#" class="name">Richard Doe</a>
                                                <span class="datetime">at Jul 25, 2012 11:09</span>
													<span class="body">
													Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
													</span>
                                            </div>
                                        </li>
                                        <li class="out">
                                            <img class="avatar" alt="" src="${media_image_url}/avatar1.jpg" />
                                            <div class="message">
                                                <span class="arrow"></span>
                                                <a href="#" class="name">Bob Nilson</a>
                                                <span class="datetime">at Jul 25, 2012 11:09</span>
													<span class="body">
													Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
													</span>
                                            </div>
                                        </li>
                                        <li class="in">
                                            <img class="avatar" alt="" src="${media_image_url}/avatar3.jpg" />
                                            <div class="message">
                                                <span class="arrow"></span>
                                                <a href="#" class="name">Richard Doe</a>
                                                <span class="datetime">at Jul 25, 2012 11:09</span>
													<span class="body">
													Lorem ipsum dolor sit amet, consectetuer adipiscing elit,
													sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
													</span>
                                            </div>
                                        </li>
                                        <li class="out">
                                            <img class="avatar" alt="" src="${media_image_url}/avatar1.jpg" />
                                            <div class="message">
                                                <span class="arrow"></span>
                                                <a href="#" class="name">Bob Nilson</a>
                                                <span class="datetime">at Jul 25, 2012 11:09</span>
													<span class="body">
													Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. sed diam nonummy nibh euismod tincidunt ut laoreet.
													</span>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                <div class="chat-form">
                                    <div class="input-cont">
                                        <input class="m-wrap" type="text" placeholder="Type a message here..." />
                                    </div>
                                    <div class="btn-cont">
                                        <span class="arrow"></span>
                                        <a href="" class="btn blue icn-only"><i class="icon-ok icon-white"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END PORTLET-->
                    </div>
                </div>
            </div>
        </div>
        <!-- END PAGE CONTAINER-->
    </div>
    <!-- END PAGE -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<#include './common/footer.ftl'/>
<!-- END FOOTER -->
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
<script src="${media_js_url}/jquery.vmap.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery.vmap.russia.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery.vmap.world.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery.vmap.europe.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery.vmap.germany.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery.vmap.usa.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery.vmap.sampledata.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery.flot.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery.pulsate.min.js" type="text/javascript"></script>
<script src="${media_js_url}/date.js" type="text/javascript"></script>
<script src="${media_js_url}/daterangepicker.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery.gritter.js" type="text/javascript"></script>
<script src="${media_js_url}/fullcalendar.min.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery.easy-pie-chart.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="/static/js/app.js" type="text/javascript"></script>
<script src="/static/js/index.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function() {
        App.init(); // initlayout and core plugins
        Index.init();
        Index.initJQVMAP(); // init index page's custom scripts
        Index.initCalendar(); // init index page's custom scripts
        Index.initCharts(); // init index page's custom scripts
        Index.initChat();
        Index.initMiniCharts();
        Index.initDashboardDaterange();
        Index.initIntro();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>