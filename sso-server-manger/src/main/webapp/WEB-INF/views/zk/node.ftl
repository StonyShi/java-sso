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
    <link rel="stylesheet" href="${media_css_url}/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${media_css_url}/bootstrap-toggle-buttons.css" />
    <link rel="stylesheet" type="text/css" href="${media_css_url}/bootstrap-tree.css" />
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="${media_image_url}/favicon.ico" />
    <style>
        .select2-drop-mask {
            position: absolute;
            left: 0;
            top: 0;
            z-index: 119991;
            background-color: #fff;
            opacity: 0;
            -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
            filter: "alpha(opacity=0)";
            filter: alpha(opacity=0);
        }
        .select2-drop {
            width: 100%;
            margin-top: -1px;
            position: absolute;
            z-index: 119992;
            top: 100%;
            background: #fff;
            color: #000;
            border: 1px solid #e5e5e5;
            border-top: 0;
        }

        .select2-search {
            display: inline-block;
            width: 100%;
            min-height: 26px;
            margin: 0;
            padding-left: 4px;
            padding-right: 4px;
            position: relative;
            z-index: 119992;
            white-space: nowrap;
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
                        系统管理 <small>组织管理</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/index">Home</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">系统管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">组织管理</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <!-- begin tree -->
                <div class="span6">
                    <div class="portlet box purple">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-list"></i>节点树</div>
                            <div class="actions">
                                <a href="javascript:;" id="tree_1_collapse" class="btn green">收起全部</a>
                                <a href="javascript:;" id="tree_1_expand" class="btn yellow">展开全部</a>

                            </div>
                        </div>
                        <div class="portlet-body fuelux">
                            <ul class="tree" id="tree_1">
                                <#list list as item>
                                    <li><a href="/zk/node/data/${(item.host)}/${(item.port)}" role="branch"
                                           class="tree-toggle closed" data-toggle="branch" data-value="${(item.host)}:${(item.port)}">${(item.host)}:${(item.port)}</a>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- end tree -->
                <div class="span6">
                    <!-- BEGIN EXAMPLE TABLE PORTLET-->
                    <div class="portlet box purple">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-group"></i>节点信息</div>
                            <div class="actions">
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="/zk/node/write/data" class="form-horizontal" method="post" id="updateNodeForm">
                                <div class="control-group">
                                    <label class="control-label">ZK服务</LABEL>
                                    <div class="controls">
                                        <input type="text" class="span8 m-wrap" placeholder="server" name="server" readonly="readonly"/>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">节点ID</LABEL>
                                    <div class="controls">
                                        <input type="text" class="span8 m-wrap" placeholder="ID" name="id" readonly="readonly"/>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">节点路径</LABEL>
                                    <div class="controls">
                                        <input type="text" class="span8 m-wrap" placeholder="路径" name="path" readonly="readonly"/>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">节点名称</LABEL>
                                    <div class="controls">
                                        <input type="text" class="span8 m-wrap" placeholder="名称" name="text" readonly="readonly"/>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">节点值</LABEL>
                                    <div class="controls">
                                        <input type="text" class="span8 m-wrap" placeholder="值" name="value"/>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <button type="submit" class="btn blue">提交修改</button>
                                    <#--<button type="button" class="btn">Cancel</button>-->
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- END EXAMPLE TABLE PORTLET-->
                </div> <!-- end table -->

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
    <script type="text/javascript">
        $.fn.modal.Constructor.prototype.enforceFocus = function () {};
    </script>
    <script type="text/javascript" src="${media_js_url}/jquery.dataTables.js"></script>
    <script type="text/javascript" src="${media_js_url}/DT_bootstrap.js"></script>
    <script type="text/javascript" src="${media_js_url}/moment.min.js"></script>
    <!-- END PAGE LEVEL PLUGINS -->
    <script src="${media_js_url}/bootstrap-tree.js"></script>
    <!-- BEGIN PAGE LEVEL SCRIPTS -->
    <script src="/static/js/sidebar.js"></script>
    <script src="/static/js/app.js" type="text/javascript"></script>
    <script>
        jQuery(document).ready(function() {
            Sidebar.init();
            App.init();
            // handle collapse/expand for tree_1
            $('#tree_1_collapse').click(function () {
                $('.tree-toggle', $('#tree_1 > li > ul')).addClass("closed");
                $('.branch', $('#tree_1 > li > ul')).removeClass("in");
            });

            $('#tree_1_expand').click(function () {
//                $('.tree-toggle', $('#tree_1 > li > ul')).removeClass("closed");

                $('.tree-toggle', $('#tree_1 > li > ul')).each(function () {
                    $(this).click(); //trigger tree node click
                });
                $('.branch', $('#tree_1 > li > ul')).addClass("in");
            });

            //This is a quick example of capturing the select event on tree leaves, not branches
            $("#tree_1").on("nodeselect.tree.data-api", "[data-role=leaf]", function (e) {
//                var output = "";
//
//                output += "Node nodeselect event fired:\n";
//                output += "Node Type: leaf\n";
//                output += "Value: " + ((e.node.value) ? e.node.value : e.node.el.text()) + "\n";
//                output += "Parentage: " + e.node.parentage.join("/");

//                console.log("1 = " + output);
//                console.dir(e)
//                console.dir(e.node)
//                console.dir(e.node.el)
                $("#updateNodeForm")[0].reset();
                $("#updateNodeForm input[name='server']").val(e.node.parentage[0]);
                $("#updateNodeForm input[name='id']").val(e.node.itemid);
                if(e.node.parentage.length > 0){
                    $("#updateNodeForm input[name='path']").val(e.node.itemid);  //e.node.parentage.slice(1).join("/") + "/" + e.node.el.text();
                }
                $("#updateNodeForm input[name='text']").val(e.node.el.text());
                $("#updateNodeForm input[name='value']").val(e.node.value);
            });

            //This is a quick example of capturing the select event on tree branches, not leaves
            $("#tree_1").on("nodeselect.tree.data-api", "[role=branch]", function (e) {
                var output = "Node nodeselect event fired:\n"; + "Node Type: branch\n" + "Value: " + ((e.node.value) ? e.node.value : e.node.el.text()) + "\n" + "Parentage: " + e.node.parentage.join("/") + "\n"

                //console.log("2 = " + output)
            });

            //Listening for the 'openbranch' event. Look for e.node, which is the actual node the user opens
            $("#tree_1").on("openbranch.tree", "[data-toggle=branch]", function (e) {

                //var output = "Node openbranch event fired:\n" + "Node Type: branch\n" + "Value: " + ((e.node.value) ? e.node.value : e.node.el.text()) + "\n" + "Parentage: " + e.node.parentage.join("/") + "\n"
                $('#tree_1 a[role="leaf"]').attr("data-role","leaf");
                $('#tree_1 a[role="leaf"]').attr("href","#");
//                console.log("3 = " + output);
//                console.dir(e)
//                console.dir(e.node)
//                console.dir(e.node.el)
                $("#updateNodeForm")[0].reset();
                $("#updateNodeForm input[name='server']").val(e.node.parentage[0]);
                $("#updateNodeForm input[name='id']").val(e.node.itemid);
                if(e.node.parentage.length > 0){
                    $("#updateNodeForm input[name='path']").val(e.node.itemid);
                }
                $("#updateNodeForm input[name='text']").val(e.node.el.text());
                $("#updateNodeForm input[name='value']").val(e.node.value);
            });


            //Listening for the 'closebranch' event. Look for e.node, which is the actual node the user closed

            $("#tree_1").on("closebranch.tree", "[data-toggle=branch]", function (e) {

                //var output = "Node closebranch event fired:\n" + "Node Type: branch\n" + "Value: " + ((e.node.value) ? e.node.value : e.node.el.text()) + "\n" + "Parentage: " + e.node.parentage.join("/") + "\n"

                //console.log("4 = " + output)
            });
        });
    </script>
</body>
<!-- END BODY -->
</html>