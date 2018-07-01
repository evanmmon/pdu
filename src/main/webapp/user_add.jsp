<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<%@include file="base.jsp" %>
<html lang="zh">
<head>

	<title>用户管理</title>
</head>
<body>
<!-- start: Header -->
<%--顶部--%>
<%@include file="head.jsp" %>
<%--顶部--%>
<div class="container-fluid-full">
	<div class="row-fluid">
		<jsp:include page="menu.jsp"></jsp:include>
		<div id="content" class="span10">
			<ul class="breadcrumb">

				<li>
					<i class="icon-home"></i>
					<a href="#">系统管理</a>
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="/userlist">用户管理</a>
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="#">用户新增</a></li>
			</ul>

			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon white edit"></i><span class="break"></span>用户新增</h2>
						<div class="box-icon">
							<a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
							<a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form  id="modify" class="form-horizontal" action="<%=basePath %>addUser" onsubmit="return check()" method="post">
							<fieldset>

								<div class="control-group">
									<label class="control-label" for="focusedInput">登录名</label>
									<div class="controls">
										<input class="input-xlarge focused" id="username" type="text" placeholder="必填（数字，字母的任意结合）" name="username" onblur="checkUsername()">
										<span><font color="red" id="username2"></font></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="focusedInput">用户名</label>
									<div class="controls">
										<input class="input-xlarge focused" id="nickname" type="text" placeholder="必填" name="nickname">
										<span><font color="red" id="nickname2"></font></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="focusedInput">密码</label>
									<div class="controls">
										<input class="input-xlarge focused" id="password" type="text" placeholder="必填（数字，字母的任意结合）"name="password">
										<span><font color="red" id="password2"></font></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="selectError3">角色</label>
									<div class="controls">
										<select id="selectError3" name="roleName">

											<option value="用户">用户</option>
											<option value="管理员">管理员</option>

										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="selectError3">部门</label>
									<div class="controls">
										<select id="selectError" name="parentid">
											<c:forEach  var="Organization" items="${OrganizationLists}"  >
												<option value="${Organization.id}">${Organization.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="focusedInput">邮箱</label>
									<div class="controls">
										<input class="input-xlarge focused" id="email" type="text" placeholder="邮箱"name="email">
										<span><font color="red" id="email2"></font></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="focusedInput">手机号</label>
									<div class="controls">
										<input class="input-xlarge focused" id="phone" type="text" placeholder="手机号"name="phone">
										<span><font color="red" id="phone2"></font></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="focusedInput">备注</label>
									<div class="controls">
										<input class="input-xlarge focused" id="focusedInput1" type="text" placeholder="备注"name="remark">
									</div>
								</div>

								<!--  <div class="control-group">
                                    <label class="control-label">用户角色</label>
                                    <div class="controls">
                                      <label class="checkbox inline">
                                        <input type="checkbox" id="inlineCheckbox1" value="option1"> 1
                                      </label>
                                      <label class="checkbox inline">
                                        <input type="checkbox" id="inlineCheckbox2" value="option2"> 2
                                      </label>
                                      <label class="checkbox inline">
                                        <input type="checkbox" id="inlineCheckbox3" value="option3">1
                                      </label>
                                    </div>
                                  </div>-->
								<%--<div class="control-group hidden-phone">--%>
								<%--<label class="control-label" for="textarea2">备注</label>--%>
								<%--<div class="controls">--%>
								<%--<textarea class="cleditor" id="textarea2" rows="3"name="remark"></textarea>--%>
								<%--</div>--%>
								<%--</div>--%>

								<div class="form-actions">
									<button type="submit" class="btn btn-primary" > 提交 </button>
									<button class="btn" onclick="javascript:history.back(-1);"> 返回 </button>
								</div>
							</fieldset>
						</form>
					</div>
				</div><!--/span-->

			</div><!--/row-->

		</div>
	</div>
</div>
<div class="modal hide fade" id="myModal">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">×</button>
		<h3>Settings</h3>
	</div>
	<div class="modal-body">
		<p>Here settings can be configured...</p>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn" data-dismiss="modal">Close</a>
		<a href="#" class="btn btn-primary">Save changes</a>
	</div>
</div>

<div class="common-modal modal fade" id="common-Modal1" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-content">
		<ul class="list-inline item-details">
			<li><a href="#">Admin templates</a></li>
			<li><a href="http://themescloud.org">Bootstrap themes</a></li>
		</ul>
	</div>
</div>
<%@include file="foot.jsp"%>
<script type="text/javascript">
    function checkUsername(){
        //用户名
        var username = $.trim($("#username").val());
        $.post("<%=basePath %>checkUsername", "username="+username, function (data) {
			if(null != data.username){
                $("#username2").html("*该用户已存在，请重新输入");
			}else {
                $("#username2").html("");
			}
        });
	};
	function check() {
        //用户名
        var username = $.trim($("#username").val());
        var zmnumReg=/^[0-9a-zA-Z]*$/;
        if(username.length == 0) {
            $("#username2").html("*请输入登录名");
            return false;
        }else if(!zmnumReg.test(username)){
                $("#username2").html("*只能输入是字母或者数字,请重新输入");
                return false;
        }else{
            $("#username2").html("");
        }
        //用户名
        var nickname = $.trim($("#nickname").val());
        if(nickname.length == 0){
            $("#nickname2").html("*请输入用户名");
            return false;
        }else{
            $("#nickname2").html("");
        }
        //密码
		var password = $.trim($("#password").val());
        if(password.length == 0){
            $("#password2").html("*请输入密码");
            return false;
		}else if(!zmnumReg.test(password)){
            $("#password2").html("*只能输入是字母或者数字,请重新输入");
            return false;
		}else {
            $("#password2").html("");
		}
		//邮箱
		var email = $("#email").val();
        var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if(email.length != 0){
            if(!myreg.test(email)){
                $("#email2").html("*请输入有效的邮箱！");
                return false;
            }else {
                $("#email2").html("");
            }
		}
        //手机
		var phone = $("#phone").val();
        var phonereg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        if(phone.length != 0){
            if(phone.length !=11){
                $("#phone2").html("*请输入有效的手机号码");
                return false;
            }else if(!phonereg.test(phone)){
                $("#phone2").html("*请输入有效的手机号码");
                return false;
            }else {
                $("#phone2").html("");
            }
		}
        return true;
    }
</script>
</body>
</html>
