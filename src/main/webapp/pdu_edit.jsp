<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<%@include file="base.jsp" %>
<html lang="zh">
<head>
    <title>设备管理</title>
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
                    <a href="/pdusearch">设备管理</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li>
                    <i class="icon-edit"></i>
                    <a href="#">编辑</a>
                </li>
            </ul>


            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon white edit"></i><span class="break"></span>设备信息</h2>
                        <div class="box-icon">
                            <!--
                            <a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
                            -->
                            <a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                            <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content">
                        <form class="form-horizontal" action="/pduupdate" name="pduForm" id="pduForm" method="post" onsubmit="return check()">
                            <input type="hidden" name="id" id="id" value="${pdu.id}">
                            <fieldset>
                                <div class="control-group">
                                    <label class="control-label" for="disabledInput">设备编号</label>
                                    <div class="controls">
                                        <input class="input-xlarge " id="qrcode" name="qrcode"
                                               style="height: 30px;width: 284px"
                                               type="text"
                                               placeholder="" value="${pdu.qrcode}" disabled="disabled">
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="disabledInput">设备类型</label>
                                    <div class="controls">
                                        <input class="input-xlarge " id="type" name="type"
                                               style="height: 30px;width: 284px"
                                               type="text"
                                               placeholder="Disabled input here…" value="${pdu.type}" disabled="disabled">
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">设备名称</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="name" name="name"
                                               style="height: 30px;width: 284px" type="text"
                                               value="${pdu.name}">
                                        <span><font color="red" id="name2"></font></span>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">设备IP</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="ip" name="ip"
                                               style="height: 30px;width: 284px"
                                               type="text"
                                               value="${pdu.ip }">
                                        <span><font color="red" id="ip2"></font></span>
                                    </div>
                                </div>


                                <div class="control-group">
                                    <label class="control-label" for="selectGroup">选择分组</label>
                                    <div class="controls">
                                        <!--
                                        <select id="selectError3">
                                            <option>默认</option>
                                            <option>办公室</option>
                                            <option>车间1</option>
                                            <option>车间2</option>
                                            <option>车间3</option>
                                        </select>
                                        -->
                                        <select name="pdugroupid" id="groupID" data-placeholder="请选择分组"
                                                style="vertical-align:top;">
                                            <c:forEach items="${pduGroupList}" var="group">
                                            <option value="${group.id }"
                                            <c:if test="${group.id == pduGroup.id}">selected</c:if>>${group.groupname}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-actions">
                                    <button type="submit" class="btn btn-primary">保存</button>
                                    <button class="btn" onclick="javascript:history.back(-1);">取消</button>
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
<%@include file="foot.jsp"%>


<!-- start: JavaScript-->
<script type="text/javascript">
    function check() {
        //设备名称
        var name = $.trim($("#name").val());
        if(name.length == 0){
            $("#name2").html("*请输入设备名称");
            return false;
        }else {
            $("#name2").html("");
        }
        //ip
        var ip = $("#ip").val();
        var ipreg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
        if(ip.length == 0){
            $("#ip2").html("*请输入IP地址");
            return false;
        }else if(!ipreg.test(ip)){
            $("#ip2").html("*请输入正确的IP地址");
            return false;
        }else {
            $("#ip2").html("");
        }
        return true;
    }
</script>
<!-- end: JavaScript-->

</body>
</html>
