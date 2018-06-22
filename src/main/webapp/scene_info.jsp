<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<%@include file="base.jsp"%>
<html lang="zh">
<head>
    <title>场景详情</title>
</head>

<body>
<!-- start: Header -->
<%--顶部--%>
<%@include file="head.jsp"%>
<%--顶部--%>
<div class="container-fluid-full">
    <div class="row-fluid">
        <!-- start: Main Menu -->
        <jsp:include page="menu.jsp"></jsp:include>
        <!-- end: Main Menu -->
        <div id="content" class="span10">
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="/sceneList">场景管理</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">场景详情</a></li>
            </ul>

            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon white user"></i><span class="break"></span>场景详情</h2>
                        <div class="box-icon">
                            <a href="/toAddScene"><i class="halflings-icon white plus"></i></a>
                            <a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                            <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content">
                        <table class="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th width="20%">场景名称</th>
                                <th width="20%">场景描述</th>
                                <th width="20%">场景状态</th>
                            </tr>
                            </thead>
                            <tbody>
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
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <!--自动任务表格-->
                    <div class="box-content">
                        <table class="table table-bordered bootstrap-datatable datatable">
                            <div class="row">
                                <div class="col-md-6">
                                    <h2><i class="halflings-icon white align-justify"></i><span class="break"></span>自动任务列表</h2>
                                </div>
                                <%--<h2><i class="halflings-icon white align-justify"></i><span class="break"></span>自动任务列表</h2>--%>
                                <%--<div class="col-md-6"><button href="#" class="btn btn-primary" data-toggle="modal" data-target="#EditDialog">编辑</button></div>--%>
                            </div>
                            <thead>
                            <tr>
                                <th class="center">设备名称</th>
                                <th class="center">电压</th>
                                <th class="center">电流</th>
                                <th class="center">功率幅度</th>
                                <th class="center">过压幅度</th>
                                <th class="center">欠压幅度</th>
                                <th class="center">过流幅度</th>
                                <th class="center">断路</th>
                                <th class="center">漏电</th>
                            </tr>
                            </thead>
                            <tbody>

                            <!--获取后台的数据列表，循环输出-->
                            <c:if test="${not empty deviceWarningList}">
                                <c:forEach items="${deviceWarningList}" var="pdu">
                                    <tr>
                                        <td class="center">${pdu.name}</td>
                                        <td class="center">${pdu.voltage}</td>
                                        <td class="center">${pdu.current}</td>
                                        <td class="center">${scene.watt}</td>
                                        <td class="center">${scene.overvoltage}</td>
                                        <td class="center">${scene.undervoltage}</td>
                                        <td class="center">${scene.overcurrent}</td>
                                        <td class="center">
                                                <c:if test="${scene.circuitbreaker eq '0'}">不限制</c:if>
                                                <c:if test="${scene.circuitbreaker eq '1'}">监控</c:if>
                                        </td>
                                        <td class="center">
                                                <c:if test="${scene.electricleakage eq '0'}">不限制</c:if>
                                                <c:if test="${scene.electricleakage eq '1'}">监控</c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                </div><!--/span-->



            </div>

        </div><!--/span-->
    </div><!--/row-->


</div><!--/.fluid-container-->

        <!-- end: Content -->

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
        </div></div>
    <div class="common-modal modal fade" id="common-Modal1" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <ul class="list-inline item-details">
                <li><a href="#">Admin templates</a></li>
                <li><a href="http://themescloud.org">Bootstrap themes</a></li>
            </ul>
        </div>
    </div>
    <div class="clearfix"></div>
<%@include file="foot.jsp"%>

    <!-- start: JavaScript-->
    <script type="text/javascript">
        function updateScene() {
            var form = document.forms[0];
            form.action = "<%=basePath %>updateScene";
            form.method = "post";
            form.submit();
        }
    </script>
</div>
</div>
</body>
</html>