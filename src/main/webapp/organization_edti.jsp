<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<%@include file="base.jsp" %>
<html lang="zh">
<head>

    <title>部门管理</title>
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
                <li><a href="/organizationlist">部门管理</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">部门修改</a></li>
            </ul>

            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon white edit"></i><span class="break"></span>部门修改</h2>
                        <div class="box-icon">
                            <a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                            <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content">
                        <form  id="modify" class="form-horizontal" action="<%=basePath %>updateOrganization" onsubmit="return check()" method="post">
                            <fieldset>
                                <input type="hidden" name="id" value="${organization.id}">
                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">部门名称</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="name" type="text" value="${organization.name}" name="name">
                                        <span><font color="red" id="name2"></font></span>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">描述</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="focusedInput" type="text" value="${organization.remark}" name=state>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="selectError3">上级部门</label>
                                    <div class="controls">
                                        <select id="selectError3" name="parentid">
                                            <c:if test="${organization.id == organization.parentid}">
                                                <option value="${organization.parentid}" selected>-----无-----</option>
                                            </c:if>
                                            <c:forEach  var="Organization" items="${OrganizationLists}">
                                                <option value="${Organization.id}" <c:if test="${Organization.id == organization.parentid}"> selected</c:if>>${Organization.name}</option>
                                            </c:forEach>
                                        </select>
                                        <c:if test="${organization.id == organization.parentid}">
                                          <span><font color="red">* 编辑部门为一级部门可不选择</font></span>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <button type="submit" class="btn btn-primary" onclick="addUser()"> 提交 </button>
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
    function check() {
        var name = $.trim($("#name").val());
        if (name.length == 0) {
            $("#name2").html("*请输入部门名称");
            return false;
        } else {
            $("#name2").html("");
        }
        return true;
    }
</script>
</body>
</html>
