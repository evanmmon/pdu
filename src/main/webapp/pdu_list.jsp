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
                <li><a href="#">列表</a></li>
            </ul>

            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon white user"></i><span class="break"></span>设备列表</h2>
                        <div class="box-icon">
                            <!--
                                <a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>-->
                            <a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                            <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content">
                        <table class="table table-striped table-bordered bootstrap-datatable datatable">
                            <thead>
                            <tr>
                                <th>设备名称</th>
                                <th>设备IP</th>
                                <th>设备编号</th>
                                <th>在线状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <!--获取后台的数据列表，循环输出-->
                            <c:choose>
                                <c:when test="${not empty pduList}">
                                    <c:forEach items="${pduList}" var="pdu" varStatus="vs">
                                        <tr>
                                            <td>${pdu.name}</td>
                                            <td class="center">${pdu.ip}</td>
                                            <td class="center">${pdu.qrcode}</td>
                                            <td class="center">
                                                <c:if test="${pdu.state == '1' }"><span
                                                        class="label label-success">在线</span></c:if>
                                                <c:if test="${pdu.state == '0' }"><span
                                                        class="label label-important">离线</span></c:if>
                                            </td>
                                            <td class="center">
                                                <a class="btn btn-success" onclick="info(${pdu.id})">
                                                    <i class="halflings-icon white zoom-in" title="详情"></i>
                                                </a>
                                                <a class="btn btn-info" onclick="update(${pdu.id})">
                                                    <i class="halflings-icon white edit" title='编辑'></i>
                                                </a>
                                                <a class="btn btn-primary" onclick="warningSet(${pdu.id})">
                                                    <i class="halflings-icon white cog" title='预警设置'></i>
                                                </a>
                                                <a class="btn btn-inverse" href="#">
                                                    <i class="halflings-icon white wrench" data-toggle="modal"
                                                       data-target="#selfcheckModal" title='自检设置'></i>
                                                </a>
                                                <a class="btn btn-danger" onclick="del(${pdu.id})">
                                                    <i class="halflings-icon white trash" title='删除'></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                            </c:choose>

                            </tbody>
                        </table>
                    </div>
                </div><!--/span-->

            </div><!--/row-->

            <!--没有注册设备在下面显示-->

            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon white align-justify"></i><span class="break"></span>设备搜索</h2>
                        <div class="box-icon">
                            <a href="#" onclick="add();" class="btn-setting"><i class="halflings-icon white plus"></i></a>
                            <a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                            <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content" id="newPduContent">
                        <table class="table table-striped table-bordered bootstrap-datatable datatable">
                            <!-- <table class="table table-bordered table-striped table-condensed">-->
                            <thead>
                            <tr class="one">

                                <th width="45%" style="text-align:center">设备编号</th>
                                <th width="45%" style="text-align:center">设备IP</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:choose>
                                <c:when test="${not empty pduSearchList}">
                                    <c:forEach items="${pduSearchList}" var="pduSearch" varStatus="vs">

                                        <tr class="more">

                                            <td class="center">${pduSearch.qrcode}</td>
                                            <td class="center">${pduSearch.ip}</td>
                                            <input type="hidden" id="machineid" name="machineid"
                                                   value="${pduSearch.machineid}"/>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                            </tbody>
                        </table>
                        <!--
                        <div class="pagination pagination-centered">
                            <ul>
                                <li><a href="#">Prev</a></li>
                                <li class="active">
                                    <a href="#">1</a>
                                </li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">Next</a></li>
                            </ul>
                        </div>
                        -->
                    </div>
                </div><!--/span-->
            </div><!--/row-->
        </div>
    </div>
</div>

<div class="modal hide fade" id="selfcheckModal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>设备自检设置</h3>
    </div>
    <div class="modal-body">


        <div class="form-group">
            <label for="dtp_input1" class="col-md-2 control-label">选择时间：</label>
            <div class="input-group date form_datetime col-md-5">
                <input class="form-control" size="16" type="text" value="" readonly>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
            <input type="hidden" id="dtp_input1" value=""/><br/>
        </div>
    </div>

    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal">关闭</a>
        <a href="#" class="btn btn-primary">保存</a>
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
<div class="clearfix"></div>

<%@include file="foot.jsp"%>


<!-- start: JavaScript-->
<!--
        <script src="js/jquery.datetimepicker.js"></script>
-->
<script type="text/javascript" src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<%--<script type="text/javascript" src="js/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>--%>
<!-- end: JavaScript-->
<script language="javascript">


    //    $('#datetimepicker').datetimepicker();
    //    $('#datetimepicker').datetimepicker({value: '2018/01/01 00:00', step: 30});
    $('.form_datetime').datetimepicker({
        //精确到分的时间
        language: 'cn',
        format: 'yyyy-mm-dd - HH:ii p',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        minuteStep: 5

    });
       /* $(".form_datetime").datetimepicker({
            format: "yyyy-mm-dd hh:ii",
            autoclose: true,
            todayBtn: true,
            language:'zh-CN',
            pickerPosition:"bottom-left"
        });
*/
    var logic = function (currentDateTime) {
        if (currentDateTime.getDay() == 6) {
            this.setOptions({
                minTime: '11:00'
            });
        } else
            this.setOptions({
                minTime: '8:00'
            });
    };


</script>

<!--checkbox全选控制js-->
<script>
    $(function () {
        function initTableCheckbox() {
            var $thr = $('.one');
            var $checkAllTh = $('<th style = "text-align:center"><input type="checkbox" id="checkAll" name="checkAll" /></th>');
            /*将全选/反选复选框添加到表头最前，即增加一列*/
            $thr.prepend($checkAllTh);
            /*“全选/反选”复选框*/
            var $checkAll = $thr.find('input');
            $checkAll.click(function (event) {
                /*将所有行的选中状态设成全选框的选中状态*/
                $tbr.find('input').prop('checked', $(this).prop('checked'));
                /*并调整所有选中行的CSS样式*/
                if ($(this).prop('checked')) {
                    $tbr.find('input').parent().parent().addClass('warning');
                } else {
                    $tbr.find('input').parent().parent().removeClass('warning');
                }
                /*阻止向上冒泡，以防再次触发点击操作*/
                event.stopPropagation();
            });
            /*点击全选框所在单元格时也触发全选框的点击操作*/
            $checkAllTh.click(function () {
                $(this).find('input').click();
            });
            var $tbr = $('.more');
            var $checkItemTd = $("<td style = 'text-align:center'><input type='checkbox' name='checkItem' id='checkItem' value='" + document.getElementById('machineid').value + "'/></td>");
            /*每一行都在最前面插入一个选中复选框的单元格*/
            $tbr.prepend($checkItemTd);
            /*点击每一行的选中复选框时*/
            $tbr.find('input').click(function (event) {
                /*调整选中行的CSS样式*/
                $(this).parent().parent().toggleClass('warning');
                /*如果已经被选中行的行数等于表格的数据行数，将全选框设为选中状态，否则设为未选中状态*/
                $checkAll.prop('checked', $tbr.find('input:checked').length == $tbr.length ? true : false);
                /*阻止向上冒泡，以防再次触发点击操作*/
                event.stopPropagation();
            });
            /*点击每一行时也触发该行的选中操作*/
            $tbr.click(function () {
                $(this).find('input').click();
            });
        }

        initTableCheckbox();
    });
</script>

<script>
    function add() {
        var str = '';
        //循环获取需要添加的信息
        for (var i = 0; i < document.getElementsByName('checkItem').length; i++) {
            if (document.getElementsByName('checkItem')[i].checked) {
                if (str == '') str += document.getElementsByName('machineid')[i].value;
                else str += ',' + document.getElementsByName('machineid')[i].value;
            }
        }
       // alert("str====:" + str);

        if (str == '') {
            alert("您没有选择任何记录!");
//            bootbox.dialog("您没有选择任何记录!",
//                [
//                    {
//                        "label": "关闭",
//                        "class": "btn-small btn-success",
//                        "callback": function () {
//                            setTimeout("self.location=self.location", 100);
//                        }
//                    }
//                ]
//            );

        } else {
           // alert("str===" + str);
            //ajax异步添加数据并刷新页面
            var options = {
                url: '/pdusave',
                type: 'post',
                dataType: 'text',
                data: {ids: str},
                success: function (data) {
                    //$("#PduContent").html(data);//要刷新的div
                    //$("#newPduContent").html(data);//要刷新的div
                    alert("添加设备成功！");
                    window.location.reload();
//                        setTimeout("self.location=self.location",100);
                },
                error: function () {
                    alert("添加设备失败！");
                }
//                        if (data.length > 0)
//                            //jQuery("#user_info").html(data);
//                    }
            };
            $.ajax(options);
        }
    }


    function del(id) {

        bootbox.confirm("<div style='line-height: 1.5;'>确定要删除记录吗?</div>", function (result) {

            if (result) {
                var url = "<%=basePath%>/pdudelete";
//                $.get(url, function (id) {
//                    setTimeout("self.location=self.location", 100);
//                });

                $.ajax({
                    type: "POST",
                    url: '<%=basePath%>/pdudelete',
                    data: {id: id},
                    dataType: 'json',
                    //beforeSend: validateData,
                    cache: false,
                    success: function (data) {
                        setTimeout("self.location=self.location", 100);
                        alert("成功删除设备！");
                    }
                });
            }
        });

    }

    //编辑
    function update(id) {
        window.location = '<%=basePath%>pduinfo?id=' + id;
    }

    //预警设置
    function warningSet(id) {
        window.location = '<%=basePath%>pduWarninginfo?id=' + id;
    }

    //详情
    function info(id) {
        window.location = '<%=basePath%>pduInfoRun?id=' + id;
    }
</script>

</body>
</html>
