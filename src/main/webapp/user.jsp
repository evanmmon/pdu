<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<%@include file="base.jsp" %>
<html lang="zh">
<head>
	<script type="text/javascript">
        function delUser(id) {
            bootbox.confirm("<div style='line-height: 1.5;'>确定要删除吗?</div>", function (result){
                if(result) {
                    window.location = '${path}/delUser?id='+id;
                }
            });
        }
	</script>

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
				<li><a href="#">用户管理</a></li>
			</ul>

			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon white user"></i><span class="break"></span>用户表</h2>
						<div class="box-icon">
							<shiro:hasPermission name="sys:product:add">
								<a  href="${path}/addUser1" ><i class="halflings-icon white plus"></i></a>
							</shiro:hasPermission>
							<a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
							<a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
							<thead>
							<tr>
								<th>用户ID</th>
								<th>登录名</th>
								<th>用户名</th>
								<th>角色</th>
								<th>部门</th>
								<th>邮箱</th>
								<th>手机号</th>
								<th>状态</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach var="Userlist" items="${Userlist}">
								<tr>
									<td class="center">${Userlist.id}</td>
									<td class="center">${Userlist.username}</td>
									<td class="center">${Userlist.nickname}</td>
									<td class="center">${Userlist.role.roleName}</td>
									<td class="center">${Userlist.organization.name}</td>
									<td class="center">${Userlist.email}</td>
									<td class="center">${Userlist.phone}</td>
									<td class="center">
										<c:if test="${Userlist.state eq '0'}">
                                                        <span class="label label-important">已删除</span>
										</c:if>
										<c:if test="${Userlist.state eq '1'}">
                                                        <span class="label label-success">正常</span>
										</c:if>
									</td>
									<td class="center">${Userlist.remark}</td>
									<td class="center">
										<shiro:hasPermission name="sys:product:update">
											<a class="btn btn-info" href="${path}/seleceOneUser?id=${Userlist.id}">
												<i class="halflings-icon white edit" title='编辑'></i>
											</a>
										</shiro:hasPermission>
										<%--<shiro:hasPermission name="sys:product:select">
											<a class="btn btn-success" href="#">
												<i class="halflings-icon white zoom-in" title='设备权限'></i>
											</a>
										</shiro:hasPermission>--%>
										<shiro:hasPermission name="sys:product:delete">
											<a type="button" onclick="delUser(${Userlist.id})"
											   class="btn btn-danger btn-sm">
												<i class="glyphicon glyphicon-trash"></i>
												</a>
										</shiro:hasPermission>
										</a>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
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
</body>
</html>
