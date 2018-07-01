<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<%@include file="base.jsp" %>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>角色管理</title>
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
                <li><a href="/rolelist">角色管理</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">角色编辑</a></li>
            </ul>

            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon white edit"></i><span class="break"></span>用户编辑</h2>
                        <div class="box-icon">
                            <a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
                            <a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                            <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content">
                        <form  id="modify" class="form-horizontal" action="">
                            <fieldset>
                                <input type="hidden" name="id" value="${role.id }" />
                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">角色名称</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="focusedInput" type="text" value="${role.rolename}" name="rolename">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">描述</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="focusedInput" type="text" value="${role.remark}"name="remark">
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
    function addUser() {
        var form = document.forms[0];
        form.action = "<%=basePath %>updateRole";
        form.method = "post";
        form.submit();
    }
</script>
</body>
</html>
