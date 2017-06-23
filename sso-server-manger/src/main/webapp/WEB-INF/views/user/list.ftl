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
                        系统管理 <small>用户管理</small>
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
                        <li><a href="#">用户管理</a></li>
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
                            <div class="caption"><i class="icon-user"></i>用户列表</div>
                            <div class="actions">
                                <a href="#" class="btn blue" id="addUser"><i class="icon-pencil"></i> 添加</a>
                            <#--<div class="btn-group">-->
                            <#--<a class="btn green" href="#" data-toggle="dropdown">-->
                            <#--<i class="icon-cogs"></i> Tools-->
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
                            <!--当前登录人的归属后台-->
                            <input type="hidden" id="loginUserType" value="${userType!}">
                            <!--当前登录人的归属站点-->
                            <input type="hidden" id="gasStationType" value="${gasStationType!}">
                            <table class="table table-striped table-bordered table-hover" id="sample_1">
                                <thead>
                                <tr>
                                    <th style="width:8px;"><input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes" /></th>
                                    <th>名称</th>
                                    <th class="hidden-480">邮箱</th>
                                    <th class="hidden-480">手机号</th>
                                    <th class="hidden-480">站点</th>
                                    <th class="hidden-480">创建时间</th>
                                    <th >Status</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list users as user>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>${user.username}</td>
                                    <td class="hidden-480"><a href="${(user.email)!""}">${(user.email)!""}</a></td>
                                    <td class="hidden-480">${(user.phone)!""}</td>
                                    <td class="hidden-480">${(user.gasStationName)!""}</td>
                                    <td class="center hidden-480"><#if (user.insertDate)??>${(user.insertDate)?datetime}</#if></td>
                                    <#if user.locked == 1>
                                        <td ><span class="label label-inverse">Locked</span></td>
                                    <#else >
                                        <td ><span class="label label-success">UnLock</span></td>
                                    </#if>
                                    <td>
                                        <a href="javascript:editView(${user.id!},'${(user.username)!}','${(user.email)!}','${(user.phone)!}',${(user.gasStationId)!0},${user.locked!0},${user.userType!0})"
                                           class="btn mini purple" id="editView_${user.id!}"><i class="icon-edit"></i> Edit</a>
                                    </td>
                                </tr>
                                </#list>
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
<div class="modal fade hide" id="addUserModal" tabindex="-1" role="dialog"
     aria-labelledby="addUserModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="addUserModalTitle">新增用户</h4>
            </div>
            <div class="modal-body">
                <div class="row-fluid">
                    <div class="span12">
                        <!-- BEGIN SAMPLE FORM PORTLET-->
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="/user/create" class="form-horizontal" method="post" id="addUserForm">
                                <div class="control-group">
                                    <label class="control-label">姓名</LABEL>
                                    <div class="controls">
                                        <input type="hidden" name="id">
                                        <input type="text" class="span6 m-wrap" placeholder="姓名" name="username"/>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">手机号</label>
                                    <div class="controls">
                                        <input type="text" class="span6 m-wrap" placeholder="手机号" name="phone"/>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">邮箱</label>
                                    <div class="controls">
                                        <input type="text" class="span6 m-wrap" placeholder="邮箱地址" name="email"/>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">密码</label>
                                    <div class="controls">
                                        <input type="text" class="span6 m-wrap" placeholder="密码" name="sea"/>
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group" id="gasStationShow">
                                    <label class="control-label">站点</label>
                                    <div class="controls">
                                       <select name ="gasStationId" class="span6 m-wrap" >
                                           <option value="">请选择</option>
                                       <#list gasStation as gas>
                                           <option value="${gas.id}">${gas.name}</option>
                                       </#list>
                                       </select>

                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group" id="userTypeShow">
                                    <label class="control-label">归属后台</label>
                                    <div class="controls">
                                        <select class="span6 m-wrap"  tabindex="1" name="userType" id="userType">
                                        </select>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">锁定</label>
                                    <div class="controls">
                                        <div class="basic-toggle-button">
                                            <input type="checkbox" class="toggle" name="toggleLocked" />
                                            <input type="hidden" name="locked" value="0">
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
                <button type="button" class="btn btn-primary" id="submitAddUser">提交</button>
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
<script type="text/javascript" src="${media_js_url}/moment.min.js"></script>
<script type="text/javascript" src="${media_js_url}/jquery.dataTables.js"></script>
<script type="text/javascript" src="${media_js_url}/DT_bootstrap.js"></script>
<script type="text/javascript" src="${media_js_url}/messenger.min.js"></script>
<script type="text/javascript" src="${media_js_url}/messenger-theme-future.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="/static/js/sidebar.js"></script>
<script src="/static/js/app.js" type="text/javascript"></script>
<script>

    /*根据登录人显示归属*/
    function showUserType(){
        var result="";
        var type =$("#loginUserType").val();
        if( type==-1){
            result+="<option value='-1'>系统后台</option>";
            result+="<option value='1'>大后台</option>";
            result+="<option value='2'>站点后台</option>";
        }else{
            if(type ==1){
                result+="<option value='1'>大后台</option>";
                result+="<option value='2'>站点后台</option>";
            }else{
                result+="<option value='2'>站点后台</option>";
                $("#userTypeShow").hide();
                $("#gasStationShow").hide();
                $("#addUserForm select[name='userType']").val(type);
                $("#addUserForm select[name='gasStationId']").val($("#gasStationType").val());
            }
        }
        $("#userType").html(result);
    }
    function editView(id,name,email,phone,gasStationId,live,userType){
        $("#addUserForm")[0].reset();
        $("#addUserForm input[name='id']").val(id);
        $("#addUserForm input[name='username']").val(name);
        $("#addUserForm input[name='email']").val(email);
        $("#addUserForm input[name='phone']").val(phone);
        if(gasStationId!=0){
            $("#addUserForm select[name='gasStationId']").val(gasStationId);
        }
        var available = $("#addUserForm input[name='locked']").val();
        if(live != available){
            $("#addUserForm input[name='toggleLocked']").siblings('label')
                    .trigger('mousedown')
                    .trigger('mouseup')
                    .trigger('click');
        }
        showUserType();
        $("#addUserForm select[name='userType']").val(userType);
        $("#addUserModalTitle").html("编辑用户");
        $("#addUserModal").modal("toggle");
    }
    jQuery(document).ready(function() {
        Sidebar.init();
        App.init();
        jQuery('#sample_1').dataTable({
            "aLengthMenu": [
                [5, 15, 20, 50, -1],
                [5, 15, 20, 50, "All"] // change per page values here
            ],
            // set the initial value
            "iDisplayLength": 15,
            "sDom": "<'row-fluid't<'row-fluid'<'span6'li>p>>",
            "sPaginationType": "bootstrap",
            "oLanguage": {
                "sLengthMenu": "_MENU_ ",
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
                    $('input[name=locked]').val(1);
                }else{
                    $('input[name=locked]').val(0);
                }
            },
            transitionspeed: 0.10
        });

        $("#addUser").click(function () {
            $("#addUserForm")[0].reset();
            $("#addUserModalTitle").html("新增用户");
            $("#addUserModal").modal("toggle");
            showUserType();
        });

        $("#submitAddUser").click(function () {
            var addUserForm = $("#addUserForm");
            var date = addUserForm.serialize();
            $.ajax({
                url : addUserForm.attr("action"),
                type: "POST",
                data: date,
                dataType: "json",
                success : function(data){
                    $("#addUserModal").modal("hide");
                    var frist = '<input type="checkbox" class="checkboxes" value="1" />';
                    var lock;
                    var joined;
                    if(data && data.insertDate && data.insertDate instanceof Date){
                        joined = moment(data.insertDate).format("YYYY-MM-DD HH:mm:ss");;
                    }else{
                        joined = moment().format("YYYY-MM-DD HH:mm:ss");
                    }
                    if(data.locked == 1){
                        lock = "<span class=\"label label-inverse\">Locked</span>";
                    }else{
                        lock = "<span class=\"label label-success\">UnLock</span>";
                    }
                    var message = "添加用户成功!";
                    var srcId = $("#addUserForm input[name='id']").val();
                    var table = $('#sample_1').dataTable();
                    if(undefined != srcId || null != srcId){
                        var nRow = $("#editView_"+srcId).parents('tr')[0];
                        table.fnDeleteRow(nRow);
                        message = "修改用户成功!";
                    }
                    table.fnAddData(
                            [frist, data.username, data.email, data.phone,data.gasStationName, joined, lock,'']
                    );
                    Messenger().post(message);

                },
                error : function (xhr) {
                    $("#addUserModal").modal("hide");
                    Messenger().post({
                        message: "添加用户失败：" + xhr.status + " " + xhr.statusText,
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