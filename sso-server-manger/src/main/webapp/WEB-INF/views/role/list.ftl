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
                            <a href="#">系统管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">角色管理</a></li>
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
                            <div class="caption"><i class="icon-user"></i>Table</div>
                            <div class="actions">
                                <a href="#" class="btn blue" id="addItem"><i class="icon-pencil"></i> 添加</a>
                                <#--<a href="#" class="btn green" id="editItem"><i class="icon-edit"></i> 编辑</a>-->
                                <#--<div class="btn-group">-->
                                    <#--<a class="btn green" href="#" data-toggle="dropdown">-->
                                        <#--<i class="icon-cogs"></i> 工具-->
                                        <#--<i class="icon-angle-down"></i>-->
                                    <#--</a>-->
                                    <#--<ul class="dropdown-menu pull-right">-->
                                        <#--<li><a href="#" id="editItem"><i class="icon-pencil"></i> 编辑</a></li>-->
                                        <#--&lt;#&ndash;<li><a href="#" id="delItem"><i class="icon-trash"></i> 删除</a></li>&ndash;&gt;-->
                                        <#--<li><a href="#" id="disableItem"><i class="icon-ban-circle"></i> 禁用</a></li>-->
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
                                    <th class="hidden-480">角色</th>
                                    <th class="hidden-480">描述</th>
                                    <th class="hidden-480">创建日期</th>
                                    <th >Status</th>
                                    <th>资源</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list list as item>
                                <tr class="odd gradeX">
                                    <td>
                                        <input type="checkbox" class="checkboxes" value="1"
                                               rid="${item.id!}" rname="${(item.role)!}" rdes="${(item.description)!}" rlive="${item.available!0}"/>
                                    </td>
                                    <td>${item.id!}</td>
                                    <td class="hidden-480">${(item.role)!""}</td>
                                    <td class="hidden-480">${(item.description)!""}</td>
                                    <td class="center hidden-480"><#if (item.insertDate)??>${(item.insertDate)?datetime}</#if></td>
                                    <#if item.available == 1>
                                        <td ><span class="label label-success">available</span></td>
                                    <#else >
                                        <td ><span class="label label-inverse">unavailable</span></td>
                                    </#if>
                                    <td><a  href="javascript:showView(${item.id!})" class="btn mini blue-stripe" id="showView">View</a></td>
                                    <td>
                                        <a href="javascript:editView(${item.id!},'${(item.role)!}','${(item.description)!}',${item.available!0})"
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
     aria-labelledby="addModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="addModalTitle">新增角色</h4>
            </div>
            <div class="modal-body">
                <div class="row-fluid">
                    <div class="span12">
                        <!-- BEGIN SAMPLE FORM PORTLET-->
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="/role/create" class="form-horizontal" method="post" id="addForm">
                                <div class="control-group">
                                    <label class="control-label">角色</LABEL>
                                    <div class="controls">
                                        <input type="hidden" name="id">
                                        <input type="text" class="span6 m-wrap" placeholder="角色" name="role"/>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">描述</LABEL>
                                    <div class="controls">
                                        <textarea rows="3" class="span6 m-wrap" placeholder="描述" name="description"></textarea>
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
</div> <!-- addModal -->
<div class="modal fade hide" id="tableModal" tabindex="-1" role="dialog"
     aria-labelledby="tableModal" aria-hidden="true" roleId="">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="tableModalTitle">角色资源</h4>
            </div>
            <div class="modal-body">
                <div class="row-fluid">
                    <div class="span12">
                        <!-- BEGIN SAMPLE FORM PORTLET-->
                        <div class="portlet-body">
                            <table class="table table-striped table-bordered table-hover" id="tableModalTable">
                                <thead>
                                <tr>
                                    <th style="width:8px;"><input type="checkbox" class="group-checkable" data-set="#tableModalTable .checkboxes" /></th>
                                    <th>ID</th>
                                    <th class="hidden-480">名称</th>
                                    <th class="hidden-480">类型</th>
                                    <th class="hidden-480">权限</th>
                                    <th class="hidden-480">路径</th>
                                    <th class="hidden-480">图标</th>
                                    <th class="hidden-480">创建日期</th>
                                    <th >Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="submitAddTable">提交</button>
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
    function editView(id,name,description,live){
        $("#addForm")[0].reset();
        $("#addForm input[name='id']").val(id);
        $("#addForm input[name='role']").val(name);
        $("#addForm textarea[name='description']").val(description);
        var available = $("#addForm input[name='available']").val();
        if(live != available){
            $("#addForm input[name='toggleAvailable']").siblings('label')
                    .trigger('mousedown')
                    .trigger('mouseup')
                    .trigger('click');
        }
        $("#addModalTitle").html("编辑角色");
        $("#addModal").modal("toggle");
    }
    function showView(id){
        if(id && id != ""){
            var table = $('#tableModalTable').dataTable();
            table.fnClearTable();
            var url = "/role/view/" + id;
            $.ajax({
                url : url,
                type: "POST",
                dataType: "json",
                success : function(data){
                    table.fnAddData(data);
                    $("#tableModal").attr("roleId", id);
                    $("#tableModal").modal("show").css({
                        width: 'auto',
                        'margin-left': function () {
                            return -($(this).width() / 2);
                        }
//                        ,'margin-top': function () {
//                            return ($(window).height() - $(this).height())/2.5 + $(document).scrollTop();
//                        }
                    });
                },
                error : function (xhr) {
                    $("#tableModal").attr("roleId", "");
                    $("#tableModal").modal("hide");
                    Messenger().post({
                        message: "错误：" + xhr.status + " " + xhr.statusText,
                        type: 'error',
                        showCloseButton: true
                    });
                }
            });
        }
    }
    function roleUpdate(url, ids, type){
        $.ajax({
            url : url,
            type: "POST",
            data: "ids=" + ids.join(","),
            dataType: "json",
            success : function(data){
                Messenger().post(type + "角色成功!");
            },
            error : function (xhr) {
                Messenger().post({
                    message: type +  "角色错误：" + xhr.status + " " + xhr.statusText,
                    type: 'error',
                    showCloseButton: true
                });
            }
        });
    }
    jQuery(document).ready(function() {
        Sidebar.init();
        App.init();
        $('#tableModalTable').dataTable({
            aoColumns : [
                {"mData": "hasRole", "fnRender" : function (mdate,type,column) {
                    var rid = mdate.aData.id;
                    if(type){
                        return '<input type="checkbox" checked="checked" class="checkboxes" value="1" rid="'+rid+'" />';
                    }
                    return '<input type="checkbox" class="checkboxes" value="0" rid="'+rid+'"/>';
                }},
                {"mData": "id"},
                {"mData": "name"},
                {"mData": "type", "fnRender" : function (mdate, type, col) {
                    if(type == "MENU"){
                        return '<span class="label label-menu">MENU</span>';
                    }
                    return '<span class="label label-button">BUTTON</span>';
                }},
                {"mData": "permission"},
                {"mData": "url"},
                {"mData": "icon", "fnRender" : function (mdate, type, col) {
                    return '<i class="'+type+'"></i>';
                }},
                {"mData": "insertDate"},
                {"mData": "available", "fnRender" : function (mdate, type, col) {
                    if(type == 1) {
                       return '<span class="label label-success">available</span>';
                    }else {
                        return '<span class="label label-inverse">unavailable</span>';
                    }
                }}
            ],
            "aLengthMenu": [
                [-1],
                ["All"] // change per page values here
            ],
            // set the initial value
            "iDisplayLength": -1,
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
            $("#addModalTitle").html("新增角色");
            $("#addModal").modal("toggle");
        });
        $("#editItem").click(function () {
            var checkboxs = $("#sample_1 td input[type=checkbox]:checked");
            if(checkboxs.length == 1){
                $("#addForm")[0].reset();
                $("#addForm input[name='id']").val($(checkboxs[0]).attr("rid"));
                $("#addForm input[name='role']").val($(checkboxs[0]).attr("rname"));
                $("#addForm textarea[name='description']").val($(checkboxs[0]).attr("rdes"));
                var alive = $(checkboxs[0]).attr("rlive");
                var available = $("#addForm input[name='available']").val();
                if(alive != available){
                    $("#addForm input[name='toggleAvailable']").siblings('label')
                            .trigger('mousedown')
                            .trigger('mouseup')
                            .trigger('click');
                }

                $("#addModalTitle").html("编辑角色");
                $("#addModal").modal("toggle");
            }else{
                Messenger().post({
                    message: "请选择一条数据。",
                    type: 'error',
                    showCloseButton: true
                });
            }
        });

        $("#disableItem").click(function () {
            var checkboxs = $("#sample_1 td input[type=checkbox]:checked");
            if(checkboxs.length <= 0){
                Messenger().post({
                    message: "请选择一条数据。",
                    type: 'error',
                    showCloseButton: true
                });
                return;
            }
            var ids = [];
            checkboxs.each(function (val, el) {
                ids.push($(el).attr("rid"));
            });
            var url = "/role/disable";
            roleUpdate(url, ids, "禁用");
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
                    var lock;
                    var joined;
                    var showView = '<a  href="javascript:showView('+data.id+')" class="btn mini blue-stripe" id="showView">View</a>';
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
                    var message = "添加角色成功!";
                    var srcId = $("#addForm input[name='id']").val();
                    var table = $('#sample_1').dataTable();
                    if(undefined != srcId || null != srcId){
                        var nRow = $("#editView_"+srcId).parents('tr')[0];
                        table.fnDeleteRow(nRow);
                        message = "修改角色成功!";
                    }
                    table.fnAddData(
                            [frist, data.id, data.role, data.description, joined, lock, showView, '']
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
        $("#submitAddTable").click(function () {
            var roleId = $("#tableModal").attr("roleId");
            var checkboxs = $("#tableModalTable td input[type=checkbox]:checked");
            var resourceIds = [];
            checkboxs.each(function (val, el) {
                resourceIds.push($(el).attr("rid"));
            })
            if(resourceIds.length <= 0){
                Messenger().post({
                    message: "请选择资源！",
                    type: 'error',
                    showCloseButton: false
                });
                return;
            }
            var url = "/role/update/resources/" + roleId ;
            $.ajax({
                url : url,
                type: "POST",
                data: "resourceIds=" + resourceIds.join(","),
                dataType: "json",
                success : function(data){
                    $("#tableModal").modal("hide");
                    $("#tableModal").attr("roleId", "");
                    Messenger().post("修改角色资源成功!");
                },
                error : function (xhr) {
                    $("#tableModal").modal("hide");
                    $("#tableModal").attr("roleId", "");
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