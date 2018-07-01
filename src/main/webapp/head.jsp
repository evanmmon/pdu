<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/4
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="index.jsp"><span>强电管理系统</span></a>

            <!-- start: Header Menu -->
            <div class="nav-no-collapse header-nav">
                <ul class="nav pull-right">

                    <!-- start: User Dropdown -->
                    <li class="dropdown">
                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="halflings-icon white user"></i> ${sessionScope.user.nickname}
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a data-toggle="modal" data-target="#userInfo" onclick="userInfo()"><i class="halflings-icon user"></i> 个人信息</a></li>
                            <li><a data-toggle="modal" data-target="#editpassword"><i class="halflings-icon user"></i> 修改密码</a></li>
                            <li><a href="/out"><i class="halflings-icon off"></i> 退出登录</a></li>
                        </ul>
                    </li>
                    <!-- end: User Dropdown -->
                </ul>
            </div>
            <!-- end: Header Menu -->

        </div>
    </div>
</div>
<div class="modal hide fade" id="userInfo">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>个人信息</h3>
    </div>
    <div class="modal-body">
        <ul class="list-group">
            <li class="list-group-item"><span>用户名&nbsp：</span><span id="info_username"></span></li>
            <li class="list-group-item"><span>角色&nbsp：</span><span id="info_role"></span></li>
            <li class="list-group-item"><span>部门&nbsp：</span><span id="info_organization"></span></li>
            <li class="list-group-item"><span>邮箱&nbsp：</span><span id="info_email"></span></li>
            <li class="list-group-item"><span>手机号&nbsp：</span><span id="info_phone"></span></li>
            <li class="list-group-item"><span>备注&nbsp：</span><span id="info_remark"></span></li>
        </ul>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal">关闭</a>
    </div>
</div>
<div class="modal hide fade" id="editpassword">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>修改密码</h3>
    </div>
    <div class="modal-body">
        <%--<p>Here settings can be configured...</p>--%>
        <form id="passwordForm">
            <div class="control-group">
                <label class="control-label" for="focusedInput">新密码</label>
                <div class="controls">
                    <input class="input-xlarge focused" id="pw" type="password" name="password">
                    <span><font color="red" id="pw2"></font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="focusedInput">密码确认</label>
                <div class="controls">
                    <input class="input-xlarge focused" id="repw" type="password" name="sorting">
                    <span><font color="red" id="repw2"></font></span>
                </div>
            </div>
            <%--<input name="text" id="text">--%>
        </form>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal">关闭</a>
        <a href="#" class="btn btn-primary" onclick="updatePassword()">保存</a>
    </div>
</div>
<script type="text/javascript">
    function passwordCheck() {
        var pw = $("#pw").val();
        var repw = $("#repw").val();
        var zmnumReg=/^[0-9a-zA-Z]*$/;
        if(pw.length == 0){
            $("#pw2").html("*请输入密码");
            return false;
        }else if(!zmnumReg.test(pw)){
            $("#pw2").html("*只能输入是字母或者数字,请重新输入");
            return false;
        }else {
            $("#pw2").html("");
        }
        if(repw != pw){
            $("#repw2").html("*两次密码输入不一致，请重新输入");
            return false;
        }else{
            $("#repw2").html("");
        }
        return true;
    }
    function updatePassword() {
        var form = $("#passwordForm");
        form.attr("action", "<%=basePath %>updatePassword");
        form.attr("method", "post");
        if(passwordCheck()){
            form.submit();
            alert("密码已修改！请重新登录");
        }
    }
    function userInfo() {
        $.post("${path}/userInfo",function(data){
            $("#info_username").html(data.username);
            $("#info_role").html(data.role);
            $("#info_organization").html(data.organization);
            $("#info_email").html(data.email);
            $("#info_phone").html(data.phone);
            $("#info_remark").html(data.remark);
        });
    };
</script>
