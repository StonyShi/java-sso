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
    <link rel="stylesheet" type="text/css" href="${media_css_url}/bootstrap-toggle-buttons.css" />
    <link rel="stylesheet" href="${media_css_url}/DT_bootstrap.css" />
    <link rel="stylesheet" href="${media_css_url}/messenger.css" />
    <link rel="stylesheet" href="${media_css_url}/messenger-theme-future.css" />
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
                        系统管理 <small>资源管理</small>
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
                        <li><a href="#">资源管理</a></li>
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
                            <div class="caption"><i class="icon-user"></i>资源列表</div>
                            <div class="actions">
                                <a href="#" class="btn blue" id="addItem"><i class="icon-pencil"></i> 添加</a>
                                <#--<div class="btn-group">-->
                                    <#--<a class="btn green" href="#" data-toggle="dropdown">-->
                                        <#--<i class="icon-cogs"></i> 工具-->
                                        <#--<i class="icon-angle-down"></i>-->
                                    <#--</a>-->
                                    <#--<ul class="dropdown-menu pull-right">-->
                                        <#--<li><a href="#"><i class="icon-pencil"></i> 编辑</a></li>-->
                                        <#--<li><a href="#"><i class="icon-trash"></i> 删除</a></li>-->
                                        <#--<li><a href="#"><i class="icon-ban-circle"></i> 禁用</a></li>-->
                                        <#--<li class="divider"></li>-->
                                        <#--<li><a href="#"><i class="i"></i> Make admin</a></li>-->
                                    <#--</ul>-->
                                <#--</div>-->
                            </div>
                        </div>
                        <div class="portlet-body">
                            <table class="table table-striped table-bordered table-hover" id="sample_1">
                                <thead>
                                <tr>
                                    <th style="width:8px;"><input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes" /></th>
                                    <th>ID</th>
                                    <th class="hidden-480">名称</th>
                                    <th class="hidden-480">类型</th>
                                    <th class="hidden-480">权限</th>
                                    <th class="hidden-480">路径</th>
                                    <th class="hidden-480">图标</th>
                                    <th >创建日期</th>
                                    <th >Status</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list list as item>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>${item.id!}</td>
                                    <td class="hidden-480">${(item.name)!""}</td>
                                    <#if item.type == "MENU">
                                        <td ><span class="label label-menu">MENU</span></td>
                                    <#else >
                                        <td ><span class="label label-button">BUTTON</span></td>
                                    </#if>
                                    <td class="hidden-480">${(item.permission)!""}</td>
                                    <td class="hidden-480">${(item.url)!""}</td>
                                    <td class="hidden-480"><i class="${(item.icon)!""}"></i></td>
                                    <td class="center hidden-480"><#if (item.insertDate)??>${(item.insertDate)?datetime}</#if></td>
                                    <#if item.available == 1>
                                        <td ><span class="label label-success">available</span></td>
                                    <#else >
                                        <td ><span class="label label-inverse">unavailable</span></td>
                                    </#if>
                                    <td>
                                        <a href="javascript:editView(${item.id!},'${(item.name)!}',
                                        '${(item.type)!}','${(item.permission)!}','${(item.url)!}','${(item.icon)!}',
                                        ${item.available!0},${(item.parentId)!})"
                                           class="btn mini purple" id="editView_${item.id!}"><i class="icon-edit"></i> Edit</a>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- END EXAMPLE TABLE PORTLET-->
                </div>
            </div>
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
     aria-labelledby="addModal" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="addModalTitle">新增资源</h4>
            </div>
            <div class="modal-body">
                <div class="row-fluid">
                    <div class="span12">
                        <!-- BEGIN SAMPLE FORM PORTLET-->
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="/resource/create" class="form-horizontal" method="post" id="addForm">
                                <div class="control-group">
                                    <label class="control-label">名称</LABEL>
                                    <div class="controls">
                                        <input type="hidden" name="id">
                                        <input type="text" class="span6 m-wrap" placeholder="名称" name="name"/>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">类型</label>
                                    <div class="controls">
                                        <select class="span6 select2" tabindex="1" name="type">
                                            <option value="MENU">MENU</option>
                                            <option value="BUTTON">BUTTON</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">URL</LABEL>
                                    <div class="controls">
                                        <input rows="3" class="span6 m-wrap" placeholder="URL地址" name="url" />
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">父类</LABEL>
                                    <div class="controls">
                                        <#--<input rows="3" class="span6 m-wrap" placeholder="父类" name="parentId" />-->
                                        <select class="span6 select2" tabindex="1" name="parentId">
                                            <option value="0"></option>
                                            <#list list as item>
                                                <option value="${item.id}">${item.name}</option>
                                            </#list>
                                        </select>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">权限</LABEL>
                                    <div class="controls">
                                        <input rows="3" class="span6 m-wrap" placeholder="权限（app:*）" name="permission" />
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">图标</LABEL>
                                    <div class="controls">
                                        <#--<input rows="3" class="span6 m-wrap" placeholder="图标" name="icon"/>-->
                                        <select class="span8 select2" tabindex="1" name="icon" id="iconSelect">
                                            <option value=""></option>
                                            <#list icons as item>
                                                <option value="${item.name}">${item.name}</option>
                                            </#list>
                                        </select>
                                        <span class="help-inline"></span>
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
                <button type="button" class="btn btn-primary" id="submitAddItem">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
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
<script type="text/javascript">
    $.fn.modal.Constructor.prototype.enforceFocus = function () {};
</script>
<script type="text/javascript" src="${media_js_url}/jquery.dataTables.js"></script>
<script type="text/javascript" src="${media_js_url}/DT_bootstrap.js"></script>
<script type="text/javascript" src="${media_js_url}/moment.min.js"></script>
<script type="text/javascript" src="${media_js_url}/messenger.min.js"></script>
<script type="text/javascript" src="${media_js_url}/messenger-theme-future.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="/static/js/sidebar.js"></script>
<script src="/static/js/app.js" type="text/javascript"></script>
<script>
    function isEmpty(name){
        if(undefined == name || "" == name || null == name){
            return true;
        }
        return false;
    }
    function isNotEmpty(name){
        return !isEmpty(name);
    }
    function editView(id,name,type,permission,url,icon,live,parentId){
        $("#addForm")[0].reset();
        $("#addForm input[name='id']").val(id);
        $("#addForm input[name='name']").val(name);
        $("#addForm input[name='permission']").val(permission);
        $("#addForm input[name='url']").val(url);
        $("#addForm select[name='type']").val(type);
        $("#addForm select[name='icon']").val(icon);
        $("#addForm select[name='icon']").select2("val",icon);
        $("#addForm select[name='parentId']").val(parentId);
        var available = $("#addForm input[name='available']").val();
        if(live != available){
            $("#addForm input[name='toggleAvailable']").siblings('label')
                    .trigger('mousedown')
                    .trigger('mouseup')
                    .trigger('click');
        }
        $("#addModalTitle").html("编辑资源");
        $("#addModal").modal("toggle");
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
        $("#addItem").click(function () {
            $("#addForm")[0].reset();
            $("#addModalTitle").html("新增资源");
            $("#addModal").modal("toggle");
        });
        $("#submitAddItem").click(function () {
            var name = $("#addForm input[name='name']").val();
            var permission = $("#addForm input[name='permission']").val();
            var type = $("#addForm select[name='type']").val();
            var parentId = $("#addForm select[name='parentId']").val();
            if(isEmpty(parentId)){
                $("#addForm select[name='parentId']").val(0);
            }
            if(isEmpty(name)){
                Messenger().post({
                    message: "资源名称不能为空",
                    type: 'error',
                    showCloseButton: false
                });
                return;
            }
            if(isEmpty(permission)){
                Messenger().post({
                    message: "资源权限不能为空",
                    type: 'error',
                    showCloseButton: false
                });
                return;
            }
            if(isEmpty(type)){
                Messenger().post({
                    message: "资源类型不能为空",
                    type: 'error',
                    showCloseButton: false
                });
                return;
            }
            var addForm = $("#addForm");
            var date = addForm.serialize();
            $.ajax({
                url : addForm.attr("action"),
                type: "POST",
                data: date,
                dataType: "json",
                success : function(data){
                    var id = data.id;
                    if(isEmpty(id)){
                        Messenger().post({
                            message: "资源添加失败.",
                            type: 'error',
                            showCloseButton: false
                        });
                        return;
                    }
                    $("#addModal").modal("hide");
                    var frist = '<input type="checkbox" class="checkboxes" value="1" />';
                    var lock;
                    var joined;
                    var icon = '<i class="'+data.icon+'"></i>';
                    var type;
                    if(data.type == "MENU") {
                        type = '<span class="label label-menu">MENU</span>';
                    }else {
                        type = '<span class="label label-button">BUTTON</span>';
                    }
                    if(data && data.insertDate && data.insertDate instanceof Date){
                        joined = moment(data.insertDate).format("YYYY-MM-DD HH:mm:ss");;
                    }else{
                        joined = moment().format("YYYY-MM-DD HH:mm:ss");
                    }
                    if(data.available == 1){
                        lock = "<span class=\"label label-success\">available</span>";
                    }else{
                        lock = "<span class=\"label label-inverse\">unavailable</span>";
                    }
                    var message = "添加资源成功!";
                    var srcId = $("#addForm input[name='id']").val();
                    var table = $('#sample_1').dataTable();
                    if(undefined != srcId || null != srcId){
                        var nRow = $("#editView_"+srcId).parents('tr')[0];
                        table.fnDeleteRow(nRow);
                        message = "修改资源成功!";
                    }
                    table.fnAddData(
                            [frist, data.id, data.name, type, data.permission, data.url, icon, joined, lock,'']
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