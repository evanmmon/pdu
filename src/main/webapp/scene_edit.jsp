<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<%@include file="base.jsp" %>
<html lang="zh">
<head>
    <title>场景编辑</title>
</head>

<body>
<!-- start: Header -->
<%--顶部--%>
<%@include file="head.jsp"%>
<%--顶部--%>

<div class="container-fluid-full">
    <div class="row-fluid">
        <jsp:include page="menu.jsp"></jsp:include>
        <!-- start: Content -->
        <div id="content" class="span10">


            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="/sceneList">场景管理</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">场景编辑</a></li>
            </ul>

            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon white edit"></i><span class="break"></span>场景编辑</h2>
                        <div class="box-icon">
                            <a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
                            <a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                            <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content">
                        <form id="modify" class="form-horizontal" action="<%=basePath %>updateScene" onsubmit="return check()" method="post">
                            <fieldset>
                                <input type="hidden" name="id" value="${scene.id }"/>
                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">场景名称</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="text" type="text"
                                               value="${scene.scenename}" name="scenename">
                                        <span><font color="red" id="text2"></font></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">场景描述</label>
                                    <div class="controls">
                                        <%--<input class="input-xlarge focused" id="focusedInput" type="text" placeholder="${scene.remark}" name="remark">--%>
                                        <textarea class="input-xlarge focused" id="focusedInput" style="height:120px;"
                                                  value="${scene.remark}"   name="remark"></textarea>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">场景状态</label>
                                    <div class="controls">
                                        <label class="radio-inline">
                                            <input type="radio" name="state" id="optionsRadios3" value="1"  <c:if
                                                    test="${scene.state eq '1'}"> checked</c:if>> 开启
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="state" id="optionsRadios4" value="0" <c:if
                                                    test="${scene.state eq '0'}"> checked</c:if>> 关闭
                                        </label>
                                    </div>
                                </div>
                                <%--<div class="control-group">
                                    <label class="control-label" for="focusedInput">电压</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="voltage" type="text"
                                              value="${scene.voltage}" name="voltage">
                                        <span><font color="red" id="voltage2"></font></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">电流</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="current" type="text"
                                               value="${scene.current}" name="current">
                                        <span><font color="red" id="current2"></font></span>
                                    </div>
                                </div>--%>
                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">功率幅度</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="watt" type="text"
                                               value="${scene.watt}" name="watt" placeholder="功率幅度,0(不限制) 0.02(上下幅度为2%)">
                                        <span><font color="red" id="watt2"></font></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">过压幅度</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="overvoltage" type="text"
                                               value="${scene.overvoltage}" name="overvoltage" placeholder="过压幅度,0(不限制) 0.02(上下幅度为2%)">
                                        <span><font color="red" id="overvoltage2"></font></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">欠压幅度</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="undervoltage" type="text"
                                               value="${scene.undervoltage}" name="undervoltage" placeholder="欠压幅度,0(不限制) 0.02(上下幅度为2%)">
                                        <span><font color="red" id="undervoltage2"></font></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">过流幅度</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="overcurrent" type="text"
                                               value="${scene.overcurrent}" name="overcurrent" placeholder="过流幅度,0(不限制) 0.02(上下幅度为2%)">
                                        <span><font color="red" id="overcurrent2"></font></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">断路</label>
                                    <div class="controls">
                                        <label class="radio-inline">
                                            <input type="radio" name="circuitbreaker" id="optionsRadios3" value="1" <c:if
                                                    test="${scene.circuitbreaker eq '1'}"> checked</c:if>> 监控
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="circuitbreaker" id="optionsRadios4" value="0" <c:if
                                                    test="${scene.circuitbreaker eq '0'}"> checked</c:if>> 不限制
                                        </label>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">漏电</label>
                                    <div class="controls">
                                        <label class="radio-inline">
                                            <input type="radio" name="electricleakage" id="optionsRadios3" value="1" <c:if
                                                    test="${scene.electricleakage eq '1'}"> checked</c:if>> 监控
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="electricleakage" id="optionsRadios4" value="0" <c:if
                                                    test="${scene.electricleakage eq '0'}"> checked</c:if>> 不限制
                                        </label>
                                    </div>
                                </div>
                                <!--自动任务表格-->
                                <div class="box-content">
                                    <table class="table">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <h2><i class="halflings-icon white align-justify"></i><span
                                                        class="break"></span>自动任务列表</h2>
                                            </div>
                                            <%--<h2><i class="halflings-icon white align-justify"></i><span class="break"></span>自动任务列表</h2>--%>
                                            <%--<div class="col-md-6"><button href="#" class="btn btn-primary" data-toggle="modal" data-target="#EditDialog">编辑</button></div>--%>
                                        </div>
                                        <thead>
                                        <tr>
                                            <th class="center"></th>
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
                                                    <td class="center">
                                                        <input type="checkbox" name="pduIds" value="${pdu.id}"
                                                               <c:if test="${scenePduIdList.contains(pdu.id)}">checked</c:if>
                                                        >
                                                    </td>
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
                                <div class="form-actions">
                                    <button type="submit" class="btn btn-primary"> 提交</button>
                                    <button class="btn" onclick="javascript:history.back(-1);"> 返回</button>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div><!--/span-->
            </div><!--/row-->
        </div><!--/.fluid-container-->
        <!-- end: Content -->
    <%@include file="foot.jsp"%>
    <!-- start: JavaScript-->
    <script type="text/javascript">
        /*function updateScene() {
            var form = document.forms[0];
            form.action = "<%=basePath %>updateScene";
            form.method = "post";
            form.submit();
        }*/
        function check() {
            //验证场景名字
            var text = $.trim($("#text").val());
            if(text.length == 0){
                $("#text2").html("*必填(可以为汉字、字母、数字的任意组合)");
                return false;
            }else{
                $("#text2").html("");
            }
           /* //电压
            var voltage = $("#voltage").val();
            if( isNaN(voltage)){
                $("#voltage2").html("*输入错误！请输入数字，范围为1到1000");
                return false;
            }else if(voltage<0 || voltage >1000) {
                $("#voltage2").html("*输入错误！请输入数字，范围为1到1000");
                return false;
            }else {
                $("#voltage2").html("");
            }
            //电流
            var current = $("#current").val();
            if( isNaN(current)){
                $("#current2").html("*输入错误！请输入数字，范围为1到1000");
                return false;
            }else if(current<0 || current >1000) {
                $("#current2").html("*输入错误！请输入数字，范围为1到1000");
                return false;
            }else {
                $("#current2").html("");
            }*/
            //功率
            var watt = $("#watt").val();
            if( isNaN(watt)){
                $("#watt2").html("*输入错误！请输入数字，范围为0到1");
                return false;
            }else if(watt<0 || watt >1){
                $("#watt2").html("*输入错误！请输入数字，范围为0到1");
                return false;
            }else {
                $("#watt2").html("");
            }
            //过压
            var overvoltage = $("#overvoltage").val();
            if( isNaN(overvoltage)){
                $("#overvoltage2").html("*输入错误！请输入数字，范围为0到1");
                return false;
            }else if(overvoltage<0 || overvoltage >1){
                $("#overvoltage2").html("*输入错误！请输入数字，范围为0到1");
                return false;
            }else {
                $("#overvoltage2").html("");
            }
            //欠压
            var undervoltage = $("#undervoltage").val();
            if( isNaN(undervoltage)){
                $("#undervoltage2").html("*输入错误！请输入数字，范围为0到1");
                return false;
            }else if(undervoltage<0 || undervoltage >1){
                $("#undervoltage2").html("*输入错误！请输入数字，范围为0到1");
                return false;
            }else {
                $("#undervoltage2").html("");
            }
            //过流
            var overcurrent = $("#overcurrent").val();
            if( isNaN(overcurrent)){
                $("#overcurrent2").html("*输入错误！请输入数字，范围为0到1");
                return false;
            }else if(overcurrent<0 || overcurrent >1){
                $("#overcurrent2").html("*输入错误！请输入数字，范围为0到1");
                return false;
            }else {
                $("#overcurrent2").html("");
            }
            return true;
        }
    </script>
</div>
</div>
<!-- 自动任务编辑对话框 -->
<%--<div class="modal fade" id="EditDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改自动任务</h4>
            </div>
            <div class="modal-body">
                <!--自动任务表格-->
                <div class="box-content">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="center"></th>
                            <th class="center">设备名称</th>
                            <th class="center">电压</th>
                            <th class="center">电流</th>
                            <th class="center">功率</th>
                            <th class="center">过压</th>
                            <th class="center">欠压</th>
                            <th class="center">过流</th>
                            <th class="center">断路</th>
                            <th class="center">漏电</th>
                        </tr>
                        </thead>
                        <tbody>

                        <!--获取后台的数据列表，循环输出-->
                        <c:if test="${not empty pduList}">
                            <c:forEach items="${pduList}" var="pdu">
                                <tr>
                                    <td class="center">
                                        <input type="checkbox" name="pduIds" value="${pdu.id}"
                                               <c:if test="${scenePduIdList.contains(pdu.id)}">checked</c:if>
                                        >
                                    </td>
                                    <td class="center">${pdu.name}</td>
                                    <td class="center">${pdu.voltage}</td>
                                    <td class="center">${pdu.current}</td>
                                    <td class="center">${scene.watt}</td>
                                    <td class="center">${scene.overvoltage}</td>
                                    <td class="center">${scene.undervoltage}</td>
                                    <td class="center">${scene.overcurrent}</td>
                                    <td class="center">${scene.circuitbreaker}</td>
                                    <td class="center">${scene.electricleakage}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="updateCustomer()">保存修改</button>
            </div>
        </div>
    </div>
</div>--%>
<!-- end: JavaScript-->
<!--
<script>

 $(function(){
 function initTableCheckbox() {
 var $thr = $('table thead tr');
 var $checkAllTh = $('<th><input type="checkbox" id="checkAll" name="checkAll" /></th>');
 /*将全选/反选复选框添加到表头最前，即增加一列*/
 $thr.prepend($checkAllTh);
 /*“全选/反选”复选框*/
 var $checkAll = $thr.find('input');
 $checkAll.click(function(event){
  /*将所有行的选中状态设成全选框的选中状态*/
  $tbr.find('input').prop('checked',$(this).prop('checked'));
  /*并调整所有选中行的CSS样式*/
  if ($(this).prop('checked')) {
  $tbr.find('input').parent().parent().addClass('warning');
  } else{
  $tbr.find('input').parent().parent().removeClass('warning');
  }
  /*阻止向上冒泡，以防再次触发点击操作*/
  event.stopPropagation();
 });
 /*点击全选框所在单元格时也触发全选框的点击操作*/
 $checkAllTh.click(function(){
  $(this).find('input').click();
 });
 var $tbr = $('table tbody tr');
 var $checkItemTd = $('<td><input type="checkbox" name="checkItem" /></td>');
 /*每一行都在最前面插入一个选中复选框的单元格*/
 $tbr.prepend($checkItemTd);
 /*点击每一行的选中复选框时*/
 $tbr.find('input').click(function(event){
  /*调整选中行的CSS样式*/
  $(this).parent().parent().toggleClass('warning');
  /*如果已经被选中行的行数等于表格的数据行数，将全选框设为选中状态，否则设为未选中状态*/
  $checkAll.prop('checked',$tbr.find('input:checked').length == $tbr.length ? true : false);
  /*阻止向上冒泡，以防再次触发点击操作*/
  event.stopPropagation();
 });
 /*点击每一行时也触发该行的选中操作*/
 $tbr.click(function(){
  $(this).find('input').click();
 });
 }
 initTableCheckbox();
 });
 </script>
--><
</body>
</html>