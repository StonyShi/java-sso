<#--
 * version 1.0.0
 * Created by Stony on 2016/7/28.
       \   ^__^
         \  (**)\__
            (__)\       )\/\
             U  ||------|
                ||     ||
-->
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title>Metronic | Data Tables - Managed Tables</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
<#include '../common/global_styles.ftl'>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="${media_css_url}/select2_metro.css" />
    <link rel="stylesheet" type="text/css" href="${media_css_url}/bootstrap-toggle-buttons.css" />
    <link rel="stylesheet" href="${media_css_url}/DT_bootstrap.css" />
    <link rel="stylesheet" href="${media_css_url}/messenger.css" />
    <link rel="stylesheet" href="${media_css_url}/messenger-theme-future.css" />
    <link href="${media_css_url}/profile.css" rel="stylesheet" type="text/css" />

    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="${media_image_url}/favicon.ico" />
    <style>
        .label-menu {
            background-color: #8ac095;
            background-image: none!important;
            text-shadow: none!important;
        }
        .label-button {
            background-color: #ae91c0;
            background-image: none!important;
            text-shadow: none!important;
        }
        ul.messenger.messenger-fixed.messenger-on-bottom.messenger-on-right {
            z-index: 199999;
            right: 20px;
            left: auto;
        }
    </style>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
<!-- BEGIN HEADER -->
<#include '../common/header_navbar.ftl'>
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container row-fluid">
    <!-- BEGIN SIDEBAR -->
<#include '../common/sidebar.ftl'>
    <!-- END SIDEBAR -->
    <!-- BEGIN PAGE -->
    <div class="page-content">
        <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
        <div id="portlet-config" class="modal hide">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"></button>
                <h3>portlet Settings</h3>
            </div>
            <div class="modal-body">
                <p>Here will be a configuration form</p>
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
                    <#include '../common/theme_color.ftl'/>
                </@shiro.hasPermission>
                    <!-- END BEGIN STYLE CUSTOMIZER -->

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        系统管理 <small>角色管理</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/index">Home</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">ZK管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">Host管理</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN EXAMPLE TABLE PORTLET-->
                    <div class="portlet box purple">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-list"></i>服务列表</div>
                            <div class="actions">
                                <a href="#" class="btn blue" id="addItem"><i class="icon-pencil"></i> 添加</a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <table class="table table-striped table-bordered table-hover" id="sample_1">
                                <thead>
                                <tr>
                                    <th style="width:8px;"><input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes" /></th>
                                    <th>ID</th>
                                    <th class="hidden-480">Host</th>
                                    <th class="hidden-480">端口</th>
                                    <th class="hidden-480">创建日期</th>
                                    <th >状态</th>
                                    <th>描述</th>
                                    <th>编辑</th>
                                    <th>删除</th>
                                    <th>详情</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list list as item>
                                <tr class="odd gradeX">
                                    <td>
                                        <input type="checkbox" class="checkboxes" value="1"/>
                                    </td>
                                    <td>${item.id!}</td>
                                    <td class="hidden-480">${(item.host)!""}</td>
                                    <td class="hidden-480">${(item.port)!""}</td>
                                    <td class="center hidden-480"><#if (item.insertDate)??>${(item.insertDate)?datetime}</#if></td>
                                    <#if (item.status)!"" == "imok">
                                        <td ><span class="label label-success">online</span></td>
                                    <#else >
                                        <td ><span class="label label-inverse">offline</span></td>
                                    </#if>
                                    <td class="hidden-480">${(item.description)!""}</td>
                                    <td>
                                        <a href="javascript:editView(${item.id!},'${(item.host)!}','${(item.port)!}','${(item.description)!}')"
                                           class="btn mini purple" id="editView_${item.id!}"><i class="icon-edit"></i> Edit</a>
                                    </td>
                                    <td>
                                        <a  href="javascript:deleteView(${item.id!})" class="btn mini black"
                                            id="deleteView_${item.id!}"><i class="icon-trash"></i> Delete</a>
                                    </td>
                                    <td><a  href="javascript:showView(${item.id!},'${(item.host)!}','${(item.port)!}')"
                                            id="showView_${item.id!}" class="btn mini blue-stripe" id="showView">View</a></td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- END EXAMPLE TABLE PORTLET-->
                </div>
            </div> <!-- end table row-fluid -->
            <!-- start sale-summary row-fluid -->
            <div class="row-fluid">
                <!-- start 服务详情 -->
                <div class="span4" id="zk_srvr">
                    <div class="portlet sale-summary">
                        <div class="portlet-title">
                            <div class="caption">192.168.0.9:2181 服务详情</div>
                            <div class="tools">
                                <a class="reload" href="javascript:;"></a>
                            </div>
                        </div>
                        <ul class="unstyled">
                            <li>
                                <span class="sale-info">版本 <i class="icon-img-up"></i></span>
                                <span class="sale-num">3.3.6-1366786</span>
                            </li>
                            <li>
                                <span class="sale-info">延时min/avg/max <i class="icon-img-down"></i></span>
                                <span class="sale-num">0/6/60418</span>
                            </li>
                            <li>
                                <span class="sale-info">收包数</span>
                                <span class="sale-num">10083028</span>
                            </li>
                            <li>
                                <span class="sale-info">发包数</span>
                                <span class="sale-num">10291302</span>
                            </li>
                            <li>
                                <span class="sale-info">堆积数</span>
                                <span class="sale-num">0</span>
                            </li>
                            <li>
                                <span class="sale-info">节点类型</span>
                                <span class="sale-num">standalone</span>
                            </li>
                            <li>
                                <span class="sale-info">节点数</span>
                                <span class="sale-num">584</span>
                            </li>
                            <li>
                                <span class="sale-info">更新时间</span>
                                <span class="sale-num">2016-08-02 19:17:20</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- END 服务详情 -->
                <!-- start 服务配置 -->
                <div class="span4" id="zk_envi">
                    <div class="portlet sale-summary">
                        <div class="portlet-title">
                            <div class="caption">192.168.0.9:2181 环境变量</div>
                            <div class="tools">
                                <a class="reload" href="javascript:;"></a>
                            </div>
                        </div>
                        <ul class="unstyled">
                            <li>
                                <span class="sale-info">版本 <i class="icon-img-up"></i></span>
                                <span class="sale-num">3.3.6-1366786</span>
                            </li>
                            <li>
                                <span class="sale-info">java版本<i class="icon-img-down"></i></span>
                                <span class="sale-num">1.7</span>
                            </li>
                            <li>
                                <span class="sale-info">系统</span>
                                <span class="sale-num">Linux</span>
                            </li>
                            <li>
                                <span class="sale-info">系统核心</span>
                                <span class="sale-num">adm64</span>
                            </li>
                            <li>
                                <span class="sale-info">系统版本</span>
                                <span class="sale-num">2.6.32-504.el6.x86_64</span>
                            </li>
                            <li>
                                <span class="sale-info">HostName</span>
                                <span class="sale-num">localhost</span>
                            </li>
                            <li>
                                <span class="sale-info">UserHome</span>
                                <span class="sale-num">/root</span>
                            </li>
                            <li>
                                <span class="sale-info">更新时间</span>
                                <span class="sale-num">2016-08-02 19:17:20</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- END 服务配置 -->
                <!-- start 服务连接数 -->
                <div class="span4" id="zk_conf">
                    <div class="portlet sale-summary">
                        <div class="portlet-title">
                            <div class="caption">192.168.0.9:2181 配置</div>
                            <div class="tools">
                                <a class="reload" href="javascript:;"></a>
                            </div>
                        </div>
                        <ul class="unstyled">
                            <li>
                                <span class="sale-info">初始化时间<i class="icon-img-up"></i></span>
                                <span class="sale-num">2012</span>
                            </li>
                            <li>
                                <span class="sale-info">数据目录<i class="icon-img-down"></i></span>
                                <span class="sale-num">/tmp/zookeeper/version-2</span>
                            </li>
                            <li>
                                <span class="sale-info">日志目录</span>
                                <span class="sale-num">/tmp/zookeeper/version-2</span>
                            </li>
                            <li>
                                <span class="sale-info">间隔时间</span>
                                <span class="sale-num">2000</span>
                            </li>
                            <li>
                                <span class="sale-info">最大连接数</span>
                                <span class="sale-num">10</span>
                            </li>
                            <li>
                                <span class="sale-info">最小session超时</span>
                                <span class="sale-num">4000</span>
                            </li>
                            <li>
                                <span class="sale-info">最大session超时</span>
                                <span class="sale-num">40000</span>
                            </li>
                            <li>
                                <span class="sale-info">更新时间</span>
                                <span class="sale-num">2016-08-02 19:17:20</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- END 服务连接数 -->
            </div>
            <!-- end sale-summary row-fluid -->
            <!-- start client connections row-fluid -->
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet-title">
                        <div class="caption" id="zk_cons_title">192.168.0.9:2181 连接</div>
                        <div class="tools">
                            <a class="reload" href="javascript:;"></a>
                        </div>
                    </div>
                    <div class="portlet-body" style="display: block;">
                        <table class="table table-striped table-bordered table-advance table-hover" id="zk_cons_table">
                            <thead>
                                <tr>
                                    <th><i class="icon-briefcase"></i>客户端</th>
                                    <th><i class="icon-beaker"></i>队列</th>
                                    <th><i class="icon-bolt"></i>收包数</th>
                                    <th><i class="icon-bolt"></i>发包数</th>
                                    <th><i class="icon-eye-open"></i>SessionId</th>
                                    <th><i class="icon-plane"></i>最后操作</th>
                                    <th><i class="icon-time"></i>连接时间</th>
                                    <th><i class="icon-time"></i>最大超时</th>
                                    <th><i class="icon-tint"></i>最后未确认ID</th>
                                    <th><i class="icon-tint"></i>最后状态变更ID</th>
                                    <th><i class="icon-time"></i>最后响应时间</th>
                                    <th><i class="icon-time"></i>最后最新延时</th>
                                    <th><i class="icon-time"></i>最小延时</th>
                                    <th><i class="icon-time"></i>最大延时</th>
                                    <th><i class="icon-time"></i>平均延时</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><a href="#">192.168.0.114:64697</a></td>
                                    <td class="hidden-phone">1</td>
                                    <td><span class="label label-success label-mini">321</span></td>
                                    <td><span class="label label-success label-mini">321</span></td>
                                    <td>0x154ff5de1081055</td>
                                    <td>PING</td>
                                    <td>1470134738890</td>
                                    <td>40000</td>
                                    <td>0x9</td>
                                    <td>0xbab08</td>
                                    <td>1470138892669</td>
                                    <td>1</td>
                                    <td>0</td>
                                    <td>12</td>
                                    <td>2579</td>
                                </tr>
                                <tr>
                                    <td><a href="#">192.168.0.7:47132</a></td>
                                    <td class="hidden-phone">1</td>
                                    <td><span class="label label-success label-mini">244</span></td>
                                    <td><span class="label label-success label-mini">233</span></td>
                                    <td>0x154ff5de1081055</td>
                                    <td>PING</td>
                                    <td>1470134738890</td>
                                    <td>40000</td>
                                    <td>0x9</td>
                                    <td>0xbab08</td>
                                    <td>1470138892669</td>
                                    <td>1</td>
                                    <td>0</td>
                                    <td>12</td>
                                    <td>2579</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!-- end client connections row-fluid -->
            <div class="clear" style="height:35px;"></div>
            <!-- END PAGE CONTENT-->
        </div>
        <!-- END PAGE CONTAINER-->
    </div>
    <!-- END PAGE -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<#include '../common/footer.ftl'/>
<!-- END FOOTER -->
<!-- modals Begin -->
<div class="modal fade hide" id="addModal" tabindex="-1" role="dialog"
     aria-labelledby="addModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="addModalTitle">新增Host</h4>
            </div>
            <div class="modal-body">
                <div class="row-fluid">
                    <div class="span12">
                        <!-- BEGIN SAMPLE FORM PORTLET-->
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="/zk/host/update" class="form-horizontal" method="post" id="addForm">
                                <div class="control-group">
                                    <label class="control-label">Host</label>
                                    <div class="controls">
                                        <input type="hidden" name="id">
                                        <input type="text" class="span6 m-wrap" placeholder="zkIP" name="host"/>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">端口</label>
                                    <div class="controls">
                                        <input type="text" class="span6 m-wrap" placeholder="端口" name="port"/>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">描述</label>
                                    <div class="controls">
                                        <textarea rows="3" class="span6 m-wrap" placeholder="描述" name="description"></textarea>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="submitAddItem">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div> <!-- addModal -->
<!-- modals End -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<script src="${media_js_url}/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${media_js_url}/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${media_js_url}/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="${media_js_url}/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${media_js_url}/jquery.toggle.buttons.js"></script>

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
<script type="text/javascript" src="${media_js_url}/select2.min.js"></script>
<script type="text/javascript" src="${media_js_url}/jquery.dataTables.js"></script>
<script type="text/javascript" src="${media_js_url}/DT_bootstrap.js"></script>
<script type="text/javascript" src="${media_js_url}/moment.min.js"></script>
<script type="text/javascript" src="${media_js_url}/messenger.min.js"></script>
<script type="text/javascript" src="${media_js_url}/messenger-theme-future.js"></script>
<script type="text/javascript" src="${media_js_url}/artTemplate.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="/static/js/sidebar.js"></script>
<script src="/static/js/app.js" type="text/javascript"></script>
<script id="zk_srvr_html" type="text/html">
    <div class="portlet sale-summary">
        <div class="portlet-title">
            <div class="caption">{{host}}:{{port}} 服务详情</div>
            <div class="tools">
                <a class="reload" href="javascript:;"></a>
            </div>
        </div>
        <ul class="unstyled">
            <li>
                <span class="sale-info">版本 <i class="icon-img-up"></i></span>
                <span class="sale-num">{{zkVersion}}</span>
            </li>
            <li>
                <span class="sale-info">延时min/avg/max <i class="icon-img-down"></i></span>
                <span class="sale-num">{{latency}}</span>
            </li>
            <li>
                <span class="sale-info">收包数</span>
                <span class="sale-num">{{received}}</span>
            </li>
            <li>
                <span class="sale-info">发包数</span>
                <span class="sale-num">{{sent}}</span>
            </li>
            <li>
                <span class="sale-info">堆积数</span>
                <span class="sale-num">{{outstanding}}</span>
            </li>
            <li>
                <span class="sale-info">节点类型</span>
                <span class="sale-num">{{mode}}</span>
            </li>
            <li>
                <span class="sale-info">节点数</span>
                <span class="sale-num">{{nodeCount}}</span>
            </li>
            <li>
                <span class="sale-info">更新时间</span>
                <span class="sale-num">{{updateDate | date2str}}</span>
            </li>
        </ul>
    </div>
</script>
<script id="zk_envi_html" type="text/html">
    <div class="portlet sale-summary">
        <div class="portlet-title">
            <div class="caption">{{host}}:{{port}} 环境变量</div>
            <div class="tools">
                <a class="reload" href="javascript:;"></a>
            </div>
        </div>
        <ul class="unstyled">
            <li>
                <span class="sale-info">版本 <i class="icon-img-up"></i></span>
                <span class="sale-num">{{zkVersion}}</span>
            </li>
            <li>
                <span class="sale-info">java版本<i class="icon-img-down"></i></span>
                <span class="sale-num">{{javaVersion}}</span>
            </li>
            <li>
                <span class="sale-info">系统</span>
                <span class="sale-num">{{osName}}</span>
            </li>
            <li>
                <span class="sale-info">系统核心</span>
                <span class="sale-num">{{osArch}}</span>
            </li>
            <li>
                <span class="sale-info">系统版本</span>
                <span class="sale-num">{{osVersion}}</span>
            </li>
            <li>
                <span class="sale-info">HostName</span>
                <span class="sale-num">{{hostName}}</span>
            </li>
            <li>
                <span class="sale-info">UserHome</span>
                <span class="sale-num">{{userName}}</span>
            </li>
            <li>
                <span class="sale-info">更新时间</span>
                <span class="sale-num">{{updateDate | date2str}}</span>
            </li>
        </ul>
    </div>
</script>
<script id="zk_conf_html" type="text/html">
    <div class="portlet sale-summary">
        <div class="portlet-title">
            <div class="caption">{{host}}:{{port}} 配置</div>
            <div class="tools">
                <a class="reload" href="javascript:;"></a>
            </div>
        </div>
        <ul class="unstyled">
            <li>
                <span class="sale-info">初始化时间<i class="icon-img-up"></i></span>
                <span class="sale-num">{{initLimit}}</span>
            </li>
            <li>
                <span class="sale-info">数据目录<i class="icon-img-down"></i></span>
                <span class="sale-num">{{dataDir}}</span>
            </li>
            <li>
                <span class="sale-info">日志目录</span>
                <span class="sale-num">{{dataLogDir}}</span>
            </li>
            <li>
                <span class="sale-info">间隔时间</span>
                <span class="sale-num">{{tickTime}}</span>
            </li>
            <li>
                <span class="sale-info">最大连接数</span>
                <span class="sale-num">{{maxClientCnxns}}</span>
            </li>
            <li>
                <span class="sale-info">最小session超时</span>
                <span class="sale-num">{{minSessionTimeout}}</span>
            </li>
            <li>
                <span class="sale-info">最大session超时</span>
                <span class="sale-num">{{maxSessionTimeout}}</span>
            </li>
            <li>
                <span class="sale-info">更新时间</span>
                <span class="sale-num">{{updateDate | date2str}}</span>
            </li>
        </ul>
    </div>
</script>
<#-- 客户端	队列	收包数	发包数	SessionId	最后操作	连接时间戳	最大超时	最后未确认ID	最后状态变更ID	最后响应时间戳	最后最新延时	最小延时	最大延时	平均延时-->
<script id="zk_cons_html" type="text/html">
    {{each list}}
    <tr>
        <td><a href="#">{{$value.clientIp}}:{{$value.clientPort}}</a></td>
        <td class="hidden-phone">{{$value.queued}}</td>
        <td><span class="label label-success label-mini">{{$value.received}}</span></td>
        <td><span class="label label-success label-mini">{{$value.sent}}</span></td>
        <td>{{$value.sid}}</td>
        <td>{{$value.lop}}</td>
        <td>{{$value.est | date2str}}</td>
        <td>{{$value.to}}</td>
        <td>{{$value.lcxid}}</td>
        <td>{{$value.lzxid}}</td>
        <td>{{$value.lresp | date2str}}</td>
        <td>{{$value.llat}}</td>
        <td>{{$value.minlat}}</td>
        <td>{{$value.maxlat}}</td>
        <td>{{$value.avglat}}</td>
     </tr>
    {{/each}}
</script>
<script>
    function isInteger(obj) {
        return typeof obj === 'number' && obj%1 === 0
    }
    function isEmpty(time){
        return (time == null || typeof time == 'undefined' || time == '');
    }
    // 字段 | date2str
    template.helper('date2str', function (time) {
        if(time == null || typeof time == 'undefined' || time == '') {
            return '-';
        }
        if(isInteger(time)) {
            return moment(time).format('YYYY-MM-DD hh:mm:ss');
        }
        return time;
    });
    function editView(id,host,port,description){
        $("#addForm")[0].reset();
        $("#addForm input[name='id']").val(id);
        $("#addForm input[name='host']").val(host);
        $("#addForm input[name='port']").val(port);
        $("#addForm textarea[name='description']").val(description);
        $("#addModalTitle").html("编辑Host");
        $("#addModal").modal("toggle");
    }
    function showView(id, host, port) {
        $.ajax({
            url : "/zk/node/srer",
            type: "POST",
            dataType: "json",
            data: {"id": id, "host": host, "port": port},
            success : function(data){
                var zk_srvr = $("#zk_srvr");
                var html = template('zk_srvr_html', data);
                zk_srvr.html(html);
            },
            error : function (xhr) {
                Messenger().post({
                    message: "更新服务错误：" + xhr.status + " " + xhr.statusText,
                    type: 'error',
                    showCloseButton: true
                });
            }
        });
        $.ajax({
            url : "/zk/node/envi",
            type: "POST",
            dataType: "json",
            data: {"id": id, "host": host, "port": port},
            success : function(data){
                var zk_srvr = $("#zk_envi");
                var html = template('zk_envi_html', data);
                zk_srvr.html(html);
            },
            error : function (xhr) {
                Messenger().post({
                    message: "更新服务错误：" + xhr.status + " " + xhr.statusText,
                    type: 'error',
                    showCloseButton: true
                });
            }
        });
        $.ajax({
            url : "/zk/node/conf",
            type: "POST",
            dataType: "json",
            data: {"id": id, "host": host, "port": port},
            success : function(data){
                var zk_srvr = $("#zk_conf");
                var html = template('zk_conf_html', data);
                zk_srvr.html(html);
            },
            error : function (xhr) {
                Messenger().post({
                    message: "更新服务错误：" + xhr.status + " " + xhr.statusText,
                    type: 'error',
                    showCloseButton: true
                });
            }
        });
        $.ajax({
            url : "/zk/node/cons",
            type: "POST",
            dataType: "json",
            data: {"id": id, "host": host, "port": port},
            success : function(data){
                $("#zk_cons_title").html(data.host + ":" + data.port + " 连接");
                var zk_srvr = $("#zk_cons_table tbody");
                var data = {
                    title: '标签',
                    list: data.cons
                };
                var html = template('zk_cons_html', data);
                zk_srvr.html(html);
            },
            error : function (xhr) {
                Messenger().post({
                    message: "更新服务错误：" + xhr.status + " " + xhr.statusText,
                    type: 'error',
                    showCloseButton: true
                });
            }
        });

    }
    function deleteView(id){
        $.ajax({
            url : "/zk/host/del/"+id,
            type: "POST",
            dataType: "json",
            success : function(data){
                var table = $('#sample_1').dataTable();
                var nRow = $("#editView_"+id).parents('tr')[0];
                table.fnDeleteRow(nRow);
                Messenger().post("删除Host成功!");
            },
            error : function (xhr) {
                Messenger().post({
                    message: "删除Host错误：" + xhr.status + " " + xhr.statusText,
                    type: 'error',
                    showCloseButton: true
                });
            }
        });
    }

    jQuery(document).ready(function() {
        Sidebar.init();
        App.init();

        $('#sample_1').dataTable({
            "aLengthMenu": [
                [5, 15, 20, 50, -1],
                [5, 15, 20, 50, "All"] // change per page values here
            ],
            // set the initial value
            "iDisplayLength": 15,
            "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
            "sPaginationType": "bootstrap",
            "oLanguage": {
                "sLengthMenu": "_MENU_ records per page",
                "oPaginate": {
                    "sPrevious": "Prev",
                    "sNext": "Next"
                }
            }
        });
        jQuery('#sample_1 .group-checkable').change(function () {
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            jQuery(set).each(function () {
                if (checked) {
                    $(this).attr("checked", true);
                } else {
                    $(this).attr("checked", false);
                }
            });
            jQuery.uniform.update(set);
        });
        $("#addItem").click(function () {
            $("#addForm")[0].reset();
            $("#addModalTitle").html("新增Host");
            $("#addModal").modal("toggle");
        });

        $("#submitAddItem").click(function () {
            var addForm = $("#addForm");
            var date = addForm.serialize();
            $.ajax({
                url : addForm.attr("action"),
                type: "POST",
                data: date,
                dataType: "json",
                success : function(data){
                    $("#addModal").modal("hide");
                    var frist = '<input type="checkbox" class="checkboxes" value="1" />';
                    var joined;
                    if(data && data.insertDate && data.insertDate instanceof Date){
                        joined = moment(data.insertDate).format("YYYY-MM-DD HH:mm:ss");;
                    }else{
                        joined = moment().format("YYYY-MM-DD HH:mm:ss");
                    }
                    var message = "添加HOST成功!";
                    var srcId = $("#addForm input[name='id']").val();
                    var table = $('#sample_1').dataTable();
                    if(undefined != srcId || null != srcId){
                        var nRow = $("#editView_"+srcId).parents('tr')[0];
                        table.fnDeleteRow(nRow);
                        message = "修改HOST成功!";
                    }
                    table.fnAddData(
                            [frist, data.id, data.host, data.port, '', data.description, joined, '']
                    );
                    Messenger().post(message);
                },
                error : function (xhr) {
                    $("#addModal").modal("hide");
                    Messenger().post({
                        message: "错误：" + xhr.status + " " + xhr.statusText,
                        type: 'error',
                        showCloseButton: true
                    });
                }
            });
        });
    });
</script>
</body>
<!-- END BODY -->
</html>