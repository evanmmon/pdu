<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<%@include file="base.jsp" %>
<html lang="zh">
<head>
    <script type="text/javascript">
        function delPduSelfcheck(id) {
            bootbox.confirm("<div style='line-height: 1.5;'>确定要删除吗?</div>", function (result){
                if(result){
                    window.location = '${path}/delPduSelfcheck?id='+id;
                }
            });
        }
    </script>

    <title>自检管理</title>
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
                    <a href="#">自检管理</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">自检管理</a></li>
            </ul>

            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon white user"></i><span class="break"></span>设备自检表</h2>
                        <div class="box-icon">
                            <shiro:hasPermission name="sys:product:add">
                                <a  href="addPduSelfcheck"><i class="halflings-icon white plus"></i></a>
                            </shiro:hasPermission>
                            <a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                            <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content">
                        <table class="table table-striped table-bordered bootstrap-datatable datatable">
                            <thead>
                            <tr>
                                <th>自检名称</th>
                                <th>开始时间</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="PduSelfchecklist" items="${PduSelfchecklist}">
                                <tr>
                                    <td class="center">${PduSelfchecklist.selfcheckname}</td>
                                    <td class="center">${PduSelfchecklist.createtime}</td>
                                    <td class="center">
                                        <c:if test="${PduSelfchecklist.state eq '0'}">
                                                        <span
                                                                class="label label-important">关闭
                                                        </span>
                                        </c:if>
                                        <c:if test="${PduSelfchecklist.state eq '1'}">
                                                        <span
                                                                class="label label-success">开启
                                                        </span>
                                        </c:if>
                                    </td>
                                    <td class="center">
                                        <%--<shiro:hasPermission name="sys:product:update">--%>
                                            <%--<a class="btn btn-info" href="${path}/selectOnePduSelfcheck?id=${PduSelfchecklist.id}">--%>
                                                <%--<i class="halflings-icon white edit" title='编辑'></i>--%>
                                            <%--</a>--%>
                                        <%--</shiro:hasPermission>--%>
                                        <shiro:hasPermission name="sys:product:select">
                                            <a class="btn btn-success" href="${path}/PduSelfcheckInfo?id=${PduSelfchecklist.id}">
                                                <i class="halflings-icon white zoom-in" title='自检详情'></i>
                                            </a>
                                        </shiro:hasPermission>
                                        <shiro:hasPermission name="sys:product:delete">
                                            <a type="button" onclick="delPduSelfcheck(${PduSelfchecklist.id})"
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
<script type="text/javascript">
    function addUser() {
        var form = document.forms[0];
        form.action = "<%=basePath %>updateUser";
        form.method = "post";
        form.submit();
    }
</script>
</body>
</html>
