<#--
 * Created by Stony on 2016/5/6.
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
                <div class="span8">
                    <!-- BEGIN EXAMPLE TABLE PORTLET-->
                    <div class="portlet box purple">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-group"></i>组织列表</div>
                            <div class="actions">
                                <a href="#" class="btn blue" id="addOrganization"><i class="icon-pencil"></i> 添加</a>
                                <div class="btn-group">
                                    <a class="btn green" href="#" data-toggle="dropdown">
                                        <i class="icon-cogs"></i> 工具
                                        <i class="icon-angle-down"></i>
                                    </a>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="#"><i class="icon-pencil"></i> 编辑</a></li>
                                        <li><a href="#"><i class="icon-trash"></i> 删除</a></li>
                                        <li><a href="#"><i class="icon-ban-circle"></i> 禁用</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#"><i class="i"></i> Make admin</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <table class="table table-striped table-bordered table-hover" id="sample_1">
                                <thead>
                                <tr>
                                    <th style="width:8px;"><input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes" /></th>
                                    <th>名称</th>
                                    <th class="hidden-480">插入时间</th>
                                    <th class="hidden-480">更新时间</th>
                                    <th class="hidden-480">状态</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list list as item>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="chexkboxes" value="1"/></td>
                                    <td class="hidden-480">${(item.name)!""}</td>
                                    <td class="center hidden-480"><#if (item.insertDate)??>${(item.insertDate)?datetime}</#if></td>
                                    <td class="center hidden-480"><#if (item.updateDate)??>${(item.updateDate)?datetime}</#if></td>
                                    <#if item.available == 1>
                                        <td ><span class="label label-success">available</span></td>
                                    <#else >
                                        <td ><span class="label label-inverse">unavailable</span></td>
                                    </#if>
                                </tr>
                                </#list>
                            </table>
                        </div>
                    </div>
                    <!-- END EXAMPLE TABLE PORTLET-->
                </div> <!-- end table -->
                <!-- begin tree -->
                <div class="span4">
                    <div class="portlet box purple">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-list"></i>组织树</div>
                            <div class="actions">
                                <a href="javascript:;" id="tree_1_collapse" class="btn green">收起全部</a>
                                <a href="javascript:;" id="tree_1_expand" class="btn yellow">展开全部</a>
                            </div>
                        </div>
                        <div class="portlet-body fuelux">
                            <ul class="tree" id="tree_1">
                                <#if tree??>
                                    <@global.treeOrgan list=treeOrgan/>
                                </#if>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- end tree -->
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
<div class="modal fade hide" id="addOrganizationModal" tabindex="-1" role="dialog"
     aria-labelledby="addOrganizationModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="addOrganizationModalTitle">新增组织机构</h4>
            </div>
            <div class="modal-body">
                <div class="row-fluid">
                    <div class="span12">
                        <!-- BEGIN SAMPLE FORM PORTLET-->
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="/organization/create" class="form-horizontal" method="post" id="addOrganizationForm">
                                <div class="control-group">
                                    <label class="control-label">名称</LABEL>
                                    <div class="controls">
                                        <input type="text" class="span8 m-wrap" placeholder="名称" name="name"/>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">上级组织</label>
                                    <div class="controls">
                                        <select class="span8 select2" tabindex="1" name="parentId">
                                            <option value=""></option>
                                            <#list list as item>
                                                <option value="${(item.id)}">${(item.name)!""}</option>
                                            </#list>
                                        </select>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Logo</label>
                                    <div class="controls">
                                        <select class="span8 select2" tabindex="1" name="logo" id="iconSelect">
                                            <option value=""></option>
                                            <option value="icon-adn"> icon-adn</option>
                                            <option value="icon-bitbucket-sign"> icon-bitbucket-sign</option>
                                            <option value="icon-dribble"> icon-dribble</option>
                                            <option value="icon-flickr"> icon-flickr</option>
                                            <option value="icon-github-sign"> icon-github-sign</option>
                                            <option value="icon-html5"> icon-html5</option>
                                            <option value="icon-linux"> icon-linux</option>
                                            <option value="icon-renren"> icon-renren</option>
                                            <option value="icon-tumblr"> icon-tumblr</option>
                                            <option value="icon-vk"> icon-vk</option>
                                            <option value="icon-xing-sign"> icon-xing-sign</option>
                                            <option value="icon-android"> icon-android</option>
                                            <option value="icon-bitcoin"> icon-bitcoin</option>
                                            <option value="icon-dropbox"> icon-dropbox</option>
                                            <option value="icon-foursquare"> icon-foursquare</option>
                                            <option value="icon-gittip"> icon-gittip</option>
                                            <option value="icon-instagram"> icon-instagram</option>
                                            <option value="icon-maxcdn"> icon-maxcdn</option>
                                            <option value="icon-skype"> icon-skype</option>
                                            <option value="icon-tumblr-sign"> icon-tumblr-sign</option>
                                            <option value="icon-weibo"> icon-weibo</option>
                                            <option value="icon-youtube"> icon-youtube</option>
                                            <option value="icon-apple"> icon-apple</option>
                                            <option value="icon-facebook"> icon-facebook</option>
                                            <option value="icon-github"> icon-github</option>
                                            <option value="icon-google-plus"> icon-google-plus</option>
                                            <option value="icon-linkedin"> icon-linkedin</option>
                                            <option value="icon-pinterest"> icon-pinterest</option>
                                            <option value="icon-stackexchange"> icon-stackexchange</option>
                                            <option value="icon-twitter"> icon-twitter</option>
                                            <option value="icon-windows"> icon-windows</option>
                                            <option value="icon-youtube-play"> icon-youtube-play</option>
                                            <option value="icon-bitbucket"> icon-bitbucket</option>
                                            <option value="icon-css3"> icon-css3</option>
                                            <option value="icon-facebook-sign"> icon-facebook-sign</option>
                                            <option value="icon-github-alt"> icon-github-alt</option>
                                            <option value="icon-google-plus-sign"> icon-google-plus-sign</option>
                                            <option value="icon-linkedin-sign"> icon-linkedin-sign</option>
                                            <option value="icon-pinterest-sign"> icon-pinterest-sign</option>
                                            <option value="icon-trello"> icon-trello</option>
                                            <option value="icon-twitter-sign"> icon-twitter-sign</option>
                                            <option value="icon-xing"> icon-xing</option>
                                            <option value="icon-youtube-sign"> icon-youtube-sign</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">有效</label>
                                    <div class="controls">
                                        <div class="basic-toggle-button">
                                            <input type="checkbox" class="toggle" name="toggleAvailable" checked="checked"/>
                                            <input type="hidden" name="available" value="1" />
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="submitAddOrganization">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
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
        $('.basic-toggle-button').toggleButtons({
            onChange: function($el, status, e) {
                $("#log").text("status = " + status);
                if(status){
                    $('input[name=available]').val(1);
                }else{
                    $('input[name=available]').val(0);
                }
            },
            animated: true,
            transitionspeed: 0.10
        });

        $("#addOrganization").click(function(){
            $("#addOrganizationModal").modal("toggle");
        });
        $("#submitAddOrganization").click(function(){
            var addOrganizationForm = $("#addOrganizationForm");
            var date=addOrganizationForm.serialize();
            $.ajax({
                url:addOrganizationForm.attr("action"),
                type:"post",
                data: date,
                datatype:"json",
                success: function (data) {
                    $("#addOrganizationModal").modal("hide");
                    var frist='<input type="checkbox" class="chexkboxes" value="1"/>';
                    var lock;
                    var joined;
                    if(data && data.inserData && data.inserData instanceof Date){
                        joined=moment(data.inserData).format("YYYY-MM-DD HH:mm:ss");
                    }else{
                        joined=moment().format("YYYY-MM-DD HH:mm:ss");
                    }
                    if(data.available == 1){
                        lock = "<span class=\"label label-success\">available</span>";
                    }else{
                        lock = "<span class=\"label label-inverse\">unavailable</span>";
                    }
                    $('#sample_1').dataTable().fnAddData(
                            [frist,data.name,joined,"",lock]
                    );
                },
                error:function(xhr){
                    $("#addOrganizationModal").modal("hide");
                    alert("错误提示："+xhr.status+" "+xhr.statusText);
                }
            });
        });

        // handle collapse/expand for tree_1
        $('#tree_1_collapse').click(function () {
            $('.tree-toggle', $('#tree_1 > li > ul')).addClass("closed");
            $('.branch', $('#tree_1 > li > ul')).removeClass("in");
        });

        $('#tree_1_expand').click(function () {
            $('.tree-toggle', $('#tree_1 > li > ul')).removeClass("closed");
            $('.branch', $('#tree_1 > li > ul')).addClass("in");
        });

        function formatIcon(state) {
            if (!state.id) return state.text; // optgroup
            return '<i class="'+state.text+'"></i>'+state.text;
        }
        $("#iconSelect").select2({
            allowClear: true,
            formatResult: formatIcon,
            formatSelection: formatIcon,
            escapeMarkup: function (m) {
                return m;
            }
        });
    });
</script>
</body>
<!-- END BODY -->
</html>