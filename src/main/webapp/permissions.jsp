<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<%@include file="base.jsp" %>
<html lang="zh">
<head>

	<title>权限管理</title>
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
			<li><a href="#">权限管理</a></li>
			</ul>

			<div class="row-fluid sortable">
				<form action="">
					<div class="box span12">
						<div class="box-header" data-original-title>
							<h2><i class="halflings-icon white user"></i><span class="break"></span>权限列表</h2>
							<div class="box-icon">
								<a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
								<a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
								<a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
							</div>
						</div>
						<div class="box-content">
							<table class="table table-striped table-bordered bootstrap-datatable datatable">
								<thead>
								<tr>
									<th width="8%">角色名称</th>
									<th>设备分组</th>
									<th width="14%">设备名称</th>
									<th width="12%">设备ip</th>
									<th width="25%">可见状态</th>
									<th width="25%">可控状态</th>

								</tr>
								</thead>
								<tbody>

								<c:forEach var="rolePduRelations" items="${selectquanxian}">
									<tr>
										<input type="hidden" name="roleId" value="${rolePduRelations.roleId}">
										<input type="hidden" name="mpermissionsId" value="${rolePduRelations.mpermissionsId}">
										<td class="center">${rolePduRelations.roleName}</td>
										<td class="center">${rolePduRelations.groupname}</td>
										<td class="center">${rolePduRelations.name}</td>
										<td class="center">${rolePduRelations.ip}</td>
										<td class="center">
												<%--<input type="radio" name="${rolePduRelations.roleName}"  value="0" ${rolePduRelations.ifview=="0" ? "checked='checked'" : ""} />可见--%>
												<%--<input type="radio" name="${rolePduRelations.roleName}"  value="1" ${rolePduRelations.ifview=="1" ? "checked='checked'" : ""} />不可见--%>
											<label class="radio-inline">
												<input type="radio"name="${rolePduRelations.pduifiewradio}" value="0" <c:if test="${rolePduRelations.ifview eq '0'}">checked=true</c:if> >可见
												<input type="radio"name="${rolePduRelations.pduifiewradio}" value="1"<c:if test="${rolePduRelations.ifview eq '1'}">checked=true</c:if>>不可见
											</label>
										</td>
										<td class="center">
											<label class="radio-inline">
												<input type="radio" name="${rolePduRelations.pduifcontrolradio}" value="0"<c:if test="${rolePduRelations.ifcontrol eq '0'}">checked=true</c:if>>可控
												<input type="radio"name="${rolePduRelations.pduifcontrolradio}" value="1"<c:if test="${rolePduRelations.ifcontrol eq '1'}">checked=true</c:if>>不可控
											</label>
										</td>
									</tr>
								</c:forEach>


								</tbody>
							</table>
							<div id="tijiao2" style="margin-left:43%;"margin:auto;>
								<button type="submit" class="btn btn-primary" onclick="addUser()"> 提交 </button>
								<button class="btn" onclick="javascript:history.back(-1);"> 返回 </button>

							</div>
						</div><!--/span-->
					</div>
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
        function addUser() {
            var form = document.forms[1];
            form.action = "<%=basePath %>/updatepermissionslist";
            form.method = "post";
            form.submit();
        }
	</script>
</body>
</html>
