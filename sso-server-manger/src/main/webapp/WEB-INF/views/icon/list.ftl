<#--
 * Created by Stony on 2016/6/3.
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
                        系统管理 <small>图标管理</small>
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
                        <li><a href="#">图标管理</a></li>
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
                            <div class="caption"><i class=" icon-apple"></i>图标列表</div>
                            <div class="actions">
                                <a href="#" class="btn blue" id="addItem"><i class="icon-pencil"></i> 添加</a>
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
                                    <th>图标</th>
                                    <th class="hidden-480">描述</th>
                                    <th class="hidden-480">地址</th>
                                    <th class="hidden-480">创建时间</th>
                                    <th >状态</th>
                                </tr>
                                </thead>
                                <tbody>
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
<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
     aria-labelledby="addModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="addModalTitle">新增图标</h4>
            </div>
            <div class="modal-body">
                <div class="row-fluid">
                    <div class="span12">
                        <!-- BEGIN SAMPLE FORM PORTLET-->
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="/app/create" class="form-horizontal" method="post" id="addForm">
                                <div class="control-group">
                                    <label class="control-label">名称</LABEL>
                                    <div class="controls">
                                        <input type="text" class="span6 m-wrap" placeholder="名称" name="name"/>
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
    function reloadDataTable(json){
        var table = $('#sample_1').dataTable();
        table.fnClearTable();
        table.fnAddData(json.aaData);
    }
    jQuery(document).ready(function() {
        Sidebar.init();
        App.init();
        $('#sample_1').dataTable({
            aoColumns : [
                {"mData": "id", "fnRender" : function (mdate,type,column) {
                    var rid = mdate.aData.id;
                    return '<input type="checkbox" class="checkboxes" value="0" date-id="'+rid+'"/>';
                }},
                {"mData": "name2", "fnRender" : function (mdate, type, col) {
                    return mdate.aData.name;
                },"sDefaultContent": "-"},
                {"mData": "name", "fnRender" : function (mdate, type, col) {
                    return '<i class="'+type+'"></i>';
                }},
                {"mData": "description", "sDefaultContent": "-"},
                {"mData": "url","sDefaultContent": "-"},
                {"mData": "insertDate","fnRender":function(mdate,type, col){
                    return moment(type).format("YYYY-MM-DD HH:mm:ss");
                },"sDefaultContent": "-"},
                {"mData": "available", "fnRender" : function (mdate, type, col) {
                    if(type == 1) {
                        return '<span class="label label-success">available</span>';
                    }else {
                        return '<span class="label label-inverse">unavailable</span>';
                    }
                }}
            ],
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
            },
            "bProcessing": false,
            "bServerSide": true,
            "sAjaxSource": "/icon/data",
            "fnServerData": function(sSource,aoData, fnCallback){
                //aoData:[{"name":"sEcho","value":1},{"name":"iColumns","value":7},{"name":"sColumns","value":""},{"name":"iDisplayStart","value":0},{"name":"iDisplayLength","value":15},{"name":"mDataProp_0","value":"id"},{"name":"mDataProp_1","value":"name"},{"name":"mDataProp_2","value":"name"},{"name":"mDataProp_3","value":"description"},{"name":"mDataProp_4","value":"url"},{"name":"mDataProp_5","value":"insertDate"},{"name":"mDataProp_6","value":"available"},{"name":"sSearch","value":""},{"name":"bRegex","value":false},{"name":"sSearch_0","value":""},{"name":"bRegex_0","value":false},{"name":"bSearchable_0","value":true},{"name":"sSearch_1","value":""},{"name":"bRegex_1","value":false},{"name":"bSearchable_1","value":true},{"name":"sSearch_2","value":""},{"name":"bRegex_2","value":false},{"name":"bSearchable_2","value":true},{"name":"sSearch_3","value":""},{"name":"bRegex_3","value":false},{"name":"bSearchable_3","value":true},{"name":"sSearch_4","value":""},{"name":"bRegex_4","value":false},{"name":"bSearchable_4","value":true},{"name":"sSearch_5","value":""},{"name":"bRegex_5","value":false},{"name":"bSearchable_5","value":true},{"name":"sSearch_6","value":""},{"name":"bRegex_6","value":false},{"name":"bSearchable_6","value":true},{"name":"iSortCol_0","value":0},{"name":"sSortDir_0","value":"asc"},{"name":"iSortingCols","value":1},{"name":"bSortable_0","value":true},{"name":"bSortable_1","value":true},{"name":"bSortable_2","value":true},{"name":"bSortable_3","value":true},{"name":"bSortable_4","value":true},{"name":"bSortable_5","value":true},{"name":"bSortable_6","value":true}]
                //console.log(JSON.stringify(aoData));
                //var data = {"aoData":JSON.stringify(aoData)};
                var sEcho = 1;
                var iDisplayStart = 0; // 起始索引
                var iDisplayLength = 15; // 每页显示的行数
                for(var key in aoData){
                    var vd = aoData[key];
                    if("iDisplayStart" == vd.name){
                        //console.log(vd.value);
                        iDisplayStart = vd.value;
                    }
                    if("iDisplayLength" == vd.name){
                        //console.log(vd.value);
                        iDisplayLength = vd.value;
                    }
                    if("sEcho" == vd.name){
                        //console.log(vd.value);
                        sEcho = vd.value;
                    }
                }
                var data = {
                    "pageNum": (iDisplayStart/iDisplayLength + 1),
                    "pageSize": iDisplayLength,
                    "echo": sEcho
                };
                $.ajax({
                    url: sSource,
                    type: "POST",
                    data: data,
                    dataType: "json",
                    async: false,
                    success: function(result){
                        fnCallback(result);
                    },
                    error:function(xhr){
                        Messenger().post({
                            message: "错误：" + xhr.status + " " + xhr.statusText,
                            type: 'error',
                            showCloseButton: true
                        });
                    }
                });
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
                    var lock;
                    var joined;
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
                    $('#sample_1').dataTable().fnAddData(
                            [frist, data.name, data.appKey, data.appSecret, data.insertDate, lock]
                    );
                    Messenger().post("添加应用成功!");
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