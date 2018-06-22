<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<%@include file="base.jsp" %>
<!-- saved from url=(0138)https://www.wconcept.cn/customer/address/new/is_checkout/1/isRedirect/1/is_gomonpage/1/sendtype/1,1/item-id/3057636,3057720/send-type/1,1/ -->
<html lang="zh">
<head>
    <title>场景管理</title>
</head>

<body>
<!-- start: Header -->
<%--顶部--%>
<%@include file="head.jsp"%>
<%--顶部--%>
<div class="container-fluid-full">
    <div class="row-fluid">
        <jsp:include page="menu.jsp"></jsp:include>
        <!-- end: Main Menu -->
        <div id="content" class="span10">
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="#">场景管理</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">场景管理</a></li>
            </ul>

            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon white user"></i><span class="break"></span>场景列表</h2>
                        <div class="box-icon">
                           <%--<a href="/toAddScene"  class="btn-info">
                                <i class="halflings-icon white plus"></i></a>--%>
                            <a href="/toAddScene"><i class="halflings-icon white plus"></i></a>
                            <a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                            <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content">
                        <table class="table table-striped table-bordered bootstrap-datatable datatable">
                            <thead>
                            <tr>
                                <th width="20%">场景名称</th>
                                <th width="20%">场景描述</th>
                                <th width="20%">是否启动</th>
                                <th width="20%">操作</th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${sceneList}" var="scene">
                                <tr>
                                    <td class="center">${scene.scenename}</td>
                                    <td class="center">${scene.remark}</td>
                                    <td class="center">
                                        <c:if test="${scene.state eq '0'}">
                                                        <span
                                                                class="label label-important">关闭
                                                        </span>
                                        </c:if>
                                        <c:if test="${scene.state eq '1'}">
                                                        <span
                                                                class="label label-success">开启
                                                        </span>
                                        </c:if>
                                    </td>
                                    <td class="center">
                                        <a class="btn btn-success" href="${path}/sceneInfo?id=${scene.id}">
                                            <i class="halflings-icon white zoom-in" title="详情"></i>
                                        </a>
                                        <a class="btn btn-info" href="${path}/selectSceneById?id=${scene.id}">
                                            <i class="halflings-icon white edit" title='编辑'></i>
                                        </a>
                                       <%-- bootbox.confirm("<div style='line-height: 1.5;'>确定要删除记录吗?</div>")--%>
                                        <a class="btn btn-danger" onclick="deleteSceneById(${scene.id})">
                                            <i class="halflings-icon white trash" title='删除'></i>
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
<%@include file="foot.jsp"%>
<script type="text/javascript">
    function deleteSceneById(id) {
        bootbox.confirm("<div style='line-height: 1.5;'>确定要删除吗?</div>", function (result){
            if(result){
                window.location = '${path}/deleteSceneById?id='+id;
            }
        });
    }
</script>

<%--<div class="modal hide fade" id="myModal">
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
</div>--%>
</body>
</html>
