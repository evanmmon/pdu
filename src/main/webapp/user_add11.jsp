<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
					<a href="index.html">系统管理</a>
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="user.html">用户管理</a>
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="#">用户新增</a></li>
			</ul>

			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon white edit"></i><span class="break"></span>用户新增</h2>
						<div class="box-icon">
							<a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
							<a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
							<a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form  id="modify" class="form-horizontal" action="">
							<fieldset>
								<div class="control-group">
									<label class="control-label" for="focusedInput">用户名</label>
									<div class="controls">
										<input class="input-xlarge focused" id="username" type="text" placeholder="用户名" name="username">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="focusedInput">密码</label>
									<div class="controls">
										<input class="input-xlarge focused" id="focusedInput" type="text" placeholder="密码"name="password">
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
										<input class="input-xlarge focused" id="focusedInput3" type="text" placeholder="邮箱"name="email">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="focusedInput">手机号</label>
									<div class="controls">
										<input class="input-xlarge focused" id="focusedInput2" type="text" placeholder="手机号"name="phone">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="focusedInput">备注</label>
									<div class="controls">
										<input class="input-xlarge focused" id="focusedInput1" type="text" placeholder="备注"name="remark">
									</div>
								</div>
								<div class="form-actions">
									<button type="submit" class="btn btn-primary" id="1" onclick="addUser()"> 提交 </button>
									<button class="btn"> 返回 </button>
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
    function check() {
		val username = $("#username").val();
        if(null == username || "" == username){
            return false;
		}
        return true;
    }
	function addUser() {
        var form = document.forms[0];
        form.action = "<%=basePath %>addUser";
        form.method = "post";
        form.onsubmit = "return check()";
        form.submit();
    }
</script>
</body>
</html>
