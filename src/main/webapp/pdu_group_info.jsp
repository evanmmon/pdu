<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<%@include file="base.jsp" %>
<html lang="zh">
<head>

    <title>设备分组</title>
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
                    <a href="/pduGrouplist">分组管理</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">分组详情</a></li>
            </ul>

            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon white user"></i><span class="break"></span>设备列表</h2>
                        <div class="box-icon">
                            <shiro:hasPermission name="sys:product:add">
                                <a id="www" href="user_add.jsp" target="main"onclick="www" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
                            </shiro:hasPermission>
                            <a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                            <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content">
                        <table class="table table-  table-bordered bootstrap-datatable datatable">
                            <thead>
                            <tr>
                                <th>设备名称</th>
                                <th>设备ip</th>
                                <th>设备编号</th>
                                <th>在线状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="PduGroupInfolist" items="${PduGroupInfolist}">
                                <tr>
                                    <td class="center">${PduGroupInfolist.pdus.get(0).name}</td>
                                    <td class="center">${PduGroupInfolist.pdus.get(0).ip}</td>
                                    <td class="center">${PduGroupInfolist.pdus.get(0).machineid}</td>
                                    <td class="center">${PduGroupInfolist.pdus.get(0).onlinestate}</td>
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
<script type="text/javascript">
    function addUser() {
        var form = document.forms[0];
        form.action = "<%=basePath %>addUser";
        form.method = "post";
        form.submit();
    }
</script>
</body>
</html>
