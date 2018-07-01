<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<%@include file="base.jsp" %>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>数据分析</title>
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
                    <a href="#">数据分析</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">预警分析</a></li>
            </ul>

            <div class="row-fluid sortable">
                <div class="box span12">
                    <div id="container" style="height:300%;width:90%;float:left;"></div>
                    <div id="container3" style="height:100px;width:70%"></div>
                    <div id="container1" style="height:300%;width:90%;float:left;"></div>

                    <%--<div id="container" style="height:500px;width:700px" ></div>--%>
                    <%--&lt;%&ndash;<div id="container3" style="height:100px;width:70%"></div>&ndash;%&gt;--%>
                    <%--<div id="container1" style="height:500px;width: 700px;"></div>--%>




                </div><!--/span-->

            </div><!--/row-->

        </div>

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
<!-- start: JavaScript-->
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>

<script type="text/javascript">
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        title : {
            text: '预警类型',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['功率','过压','欠压','过流','断路','漏电']
        },
        series : [
            {
                name: '预警类型',
                type: 'pie',
                radius : '80%',
                center: ['50%', '60%'],
                data:[
                    {value:${jidianqi}, name:'功率'},
                    {value:${guoya}, name:'过压'},
                    {value:${qianya}, name:'欠压'},
                    {value:${guoliu}, name:'过流'},
                    {value:${duanlu}, name:'断路'},
                    {value:${loudian}, name:'漏电'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>


<script type="text/javascript">
    var dom = document.getElementById("container1");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        title : {
            text: '预警状态',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['已处理','未处理','处理中','已忽略']
        },
        series : [
            {
                name: '处理情况',
                type: 'pie',
                radius : '80%',
                center: ['50%', '60%'],
                data:[
                    {value:${weichulishu}, name:'未处理'},
                    {value:${yichulishu}, name:'已处理'},
                    {value:${chulizhong}, name:'处理中'},
                    {value:${yihulue}, name:'已忽略'},
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>
    </body>
    </html>
    <%--<script type="text/javascript">--%>
    <%--function addUser() {--%>
    <%--var form = document.forms[0];--%>
    <%--form.action = "<%=basePath %>addUser";--%>
    <%--form.method = "post";--%>
    <%--form.submit();--%>
    <%--}--%>
    <%--</script>--%>

