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
        <!-- start: Content -->
        <div id="content" class="span10">
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="#">设备管理</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li>
                    <i class="icon-cog"></i>
                    <a href="#">预警设置</a>
                </li>
            </ul>


            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon white edit"></i><span class="break"></span>设备预警值设置</h2>
                        <div class="box-icon">
                            <a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
                            <a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                            <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content">
                        <form class="form-horizontal" action="/pduWarningSet" name="warningSetForm" id="warningSetForm"
                              method="post">
                            <input type="hidden" id="pduid" name="pduid" value="${pduWarningSet.pduid}">
                            <fieldset>
                                <div class="control-group">
                                    <label class="control-label" for="disabledInput">设备编号</label>
                                    <div class="controls">
                                        <input class="input-xlarge disabled" id="machineid" type="text"
                                               placeholder="Disabled input here…" disabled="" value="${pdu.machineid}">
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="disabledInput">设备类型</label>
                                    <div class="controls">
                                        <input class="input-xlarge disabled" id="type" type="text"
                                               placeholder="Disabled input here…" disabled="" value="${pdu.type}">
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="disabledInput">设备IP</label>
                                    <div class="controls">
                                        <input class="input-xlarge disabled" id="ip" type="text"
                                               placeholder="Disabled input here…" disabled="" value="${pdu.ip}">
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="disabledInput">设备名称</label>
                                    <div class="controls">
                                        <input class="input-xlarge disabled" id="name" type="text"
                                               placeholder="Disabled input here…" disabled="" value="${pdu.name}">
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">电流</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="current" name="current" type="text"
                                               value="${pduWarningSet.current}"><span class="help-inline" style="color: #b94a48">&nbsp;&nbsp;单位：A &nbsp;&nbsp; 大于设置电流则预警</span>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">电压</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="voltage" name="voltage" type="text"
                                               value="${pduWarningSet.voltage}"><span class="help-inline" style="color: #b94a48">&nbsp;&nbsp;单位：V &nbsp;&nbsp; 大于设置电压则预警</span>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">功率</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="watt" name="watt" type="text"
                                               value="${pduWarningSet.watt}"><span class="help-inline" style="color: #b94a48">&nbsp;&nbsp;单位：W &nbsp;&nbsp; 大于设置功率则预警</span>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label">过压</label>
                                    <div class="controls">
                                        <label class="radio">
                                            <input type="radio" style="width: 100px; " name="overvoltage" id="overvoltage1" value="1"
                                                   <c:if test="${pduWarningSet.overvoltage eq '1' }">checked</c:if>>是
                                            <input type="radio" style="width: 100px; " name="overvoltage" id="overvoltage2"
                                                   value="0"
                                                   <c:if test="${pduWarningSet.overvoltage eq '0' }">checked</c:if>>否
                                        </label>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label">欠压</label>
                                    <div class="controls">
                                        <label class="radio">
                                            <input type="radio" style="width: 100px; " name="undervoltage" id="undervoltage1" value="1"
                                                   <c:if test="${pduWarningSet.undervoltage == '1' }">checked="checked"</c:if> >是
                                            <input type="radio" style="width: 100px; " name="undervoltage" id="undervoltage2"
                                                   value="0"
                                                   <c:if test="${pduWarningSet.undervoltage == '0' }">checked="checked"</c:if> >否
                                        </label>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label">过流</label>
                                    <div class="controls">
                                        <label class="radio">
                                            <input type="radio" style="width: 100px; " name="overcurrent" id="overcurrent1" value="1"
                                                   <c:if test="${pduWarningSet.overcurrent == '1' }">checked="checked"</c:if> >是
                                            <input type="radio" style="width: 100px; " name="overcurrent" id="overcurrent2"
                                                   value="0"
                                                   <c:if test="${pduWarningSet.overcurrent == '0' }">checked="checked"</c:if> >否
                                        </label>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label">断路</label>
                                    <div class="controls">
                                        <label class="radio">
                                            <input type="radio" style="width: 100px; " name="circuitbreaker" id="circuitbreaker1" value="1"
                                                   <c:if test="${pduWarningSet.circuitbreaker == '1' }">checked="checked"</c:if> >是
                                            <input type="radio" style="width: 100px; " name="circuitbreaker" id="circuitbreaker2"
                                                   value="0"
                                                   <c:if test="${pduWarningSet.circuitbreaker == '0' }">checked="checked"</c:if> >否
                                        </label>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label">漏电</label>
                                    <div class="controls">
                                        <label class="radio">
                                            <input type="radio" style="width: 100px; " name="electricleakage" id="electricleakage1" value="1"
                                                   <c:if test="${pduWarningSet.electricleakage == '1' }">checked="checked"</c:if> >是
                                            <input type="radio" style="width: 100px; " name="electricleakage" id="electricleakage2"
                                                   value="0"
                                                   <c:if test="${pduWarningSet.electricleakage == '0' }">checked="checked"</c:if> >否
                                        </label>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label">继电器</label>
                                    <div class="controls">
                                        <label class="radio">
                                            <input type="radio" style="width: 100px; " name="relaystate" id="relaystate1"
                                                   value="1"
                                                   <c:if test="${pduWarningSet.relaystate == '1'}">checked="checked"</c:if> >开
                                            <input type="radio" style="width: 100px; " name="relaystate" id="relaystate2"
                                                   value="0"
                                                   <c:if test="${pduWarningSet.relaystate == '0'}">checked="checked"</c:if> >关
                                        </label>
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


        </div><!--/.fluid-container-->

        <!-- end: Content -->
    </div><!--/#content.span10-->
</div><!--/fluid-row-->

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

<div class="clearfix"></div>

<%@include file="foot.jsp"%>

<!-- start: JavaScript-->

<!-- end: JavaScript-->

</body>
</html>
