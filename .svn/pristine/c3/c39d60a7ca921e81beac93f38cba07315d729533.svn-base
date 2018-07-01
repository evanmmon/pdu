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
                    <i class="icon-zoom-in"></i>
                    <a href="#">设备运行状态</a>
                </li>
            </ul>


            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon white edit"></i><span class="break"></span>设备详情</h2>
                        <div class="box-icon">
                            <a href="#" class="btn-setting"><i class="halflings-icon white arrow-left"></i></a>
                            <a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                            <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content">
                        <form class="form-horizontal">
                            <fieldset>


                                <div class="control-group">
                                    <label class="control-label" for="disabledInput">设备名称</label>
                                    <div class="controls">
                                        <input class="input-xlarge disabled" id="name" type="text"
                                               value="${pdu.name}" disabled="">
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="disabledInput">设备类型</label>
                                    <div class="controls">
                                        <input class="input-xlarge disabled" id="type" type="text"
                                               value="${pdu.type}" disabled="">
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="disabledInput">设备IP</label>
                                    <div class="controls">
                                        <input class="input-xlarge disabled" id="ip" type="text"
                                               value="${pdu.ip}" disabled="">
                                    </div>
                                </div>

                                <!--

                                <div class="form-actions">
                                  <button type="submit" class="btn btn-primary">返回</button>

                                </div>
                                -->

                            </fieldset>
                        </form>

                    </div>
                    <div class="box">
                        <div class="box-header">
                            <h2><i class="halflings-icon white list-alt"></i><span class="break"></span>状态图</h2>
                            <div class="box-icon">
                                <!--
                                    <a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
                                -->
                                <a href="#" class="b tn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                                <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                            </div>
                        </div>

                        <div class="box-content">
                            <div class="center" style="height:350px;" id="charts"></div>
                            <p class="stackControls center">
                                <input class="btn" type="button" value="电压" onclick="voltagesInfo();">
                                <input class="btn" type="button" value="电流" onclick="currentsInfo();">
                                <input class="btn" type="button" value="功率" onclick="wattsInfo();" style="color: ">
                            </p>

                            <%--<div id="sincos" class="center" style="height:300px;"></div>
                            <p id="hoverdata">Mouse position at (<span id="x">0</span>, <span id="y">0</span>). <span
                                    id="clickdata"></span></p>

                            <p><input type="image" src="timeline/时间轴.png"/></p>--%>

                        </div>


                    </div>
                </div><!--/span-->

            </div><!--/row-->

        </div><!--/.fluid-container-->

        <!-- end: Content -->
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
<script src="js/echarts.js"></script>
<!-- end: JavaScript-->

<script>
    var url = "${basePath}/pduInfoCharts";
    var chartOutChar = echarts.init(document.getElementById('charts'));
    var option = {
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        title: {
            left: 'center',
            text: '设备电压运行图',
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: []
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%']
        },
        dataZoom: [{
            type: 'inside',
            start: 0,
            end: 10
        }, {
            start: 0,
            end: 10,
            handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
            handleSize: '80%',
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        }],
        series: [
            {
                name: '电压',
                type: 'line',
                smooth: true,
                symbol: 'none',
                sampling: 'average',
                itemStyle: {
                    normal: {
                        color: 'rgb(255, 70, 131)'
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(255, 158, 68)'
                        }, {
                            offset: 1,
                            color: 'rgb(255, 70, 131)'
                        }])
                    }
                },
                data: []
            }
        ]
    };

    chartOutChar.showLoading({text: '正在努力的读取数据中...'});

    var times = [];
    var voltages = [];
    var currents = [];
    var watts = [];

    $.ajax({	//使用JQuery内置的Ajax方法
        type: "post",		//post请求方式
        async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "<%=basePath%>/pduInfoCharts",	//请求发送到ShowInfoIndexServlet处
        data: {id: "${pdu.id}"},		//请求内包含一个key为name，value为A0001的参数；服务器接收到客户端请求时通过request.getParameter方法获取该参数值
        dataType: "json",		//返回数据形式为json
        success: function (data) {
//            alert("data===" + data.length);
//            chartOutChar.showLoading({text: '正在努力的读取数据中...'});
            if (data != null && data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    times.push(data[i].collectiontime);
                    voltages.push(data[i].voltage);
                    currents.push(data[i].current);
                    watts.push(data[i].watt);
                }
//                alert(times);
                chartOutChar.hideLoading();	//隐藏加载动画

                chartOutChar.setOption({
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: times
                    },
                    yAxis: {
                        type: 'value',
                        boundaryGap: [0, '100%']
                    },

                    series: [
                        {
                            name: '电压',
                            data: voltages
                        }
                    ]
                });
//                chartOutChar.hideLoading();
            }
            else {
                //返回的数据为空时显示提示信息
                alert("图表请求数据为空，可能服务器暂未录入近五天的观测数据，您可以稍后再试！");
                chartOutChar.hideLoading();
            }
        },
        error: function (errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败，可能是服务器开小差了");
            chartOutChar.hideLoading();
        }
    })

    chartOutChar.setOption(option);


    function voltagesInfo() {

        var voltagesChar = echarts.init(document.getElementById('charts'));
//        chartOutChar.showLoading({text: '正在努力的读取数据中...'});
        var option = {
            tooltip: {
                trigger: 'axis',
                position: function (pt) {
                    return [pt[0], '10%'];
                }
            },
            title: {
                left: 'center',
                text: '设备电压运行图',
            },
            toolbox: {
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    restore: {},
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: times
            },
            yAxis: {
                type: 'value',
                boundaryGap: [0, '100%']
            },
            dataZoom: [{
                type: 'inside',
                start: 0,
                end: 10
            }, {
                start: 0,
                end: 10,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            }],
            series: [
                {
                    name: '电压',
                    type: 'line',
                    smooth: true,
                    symbol: 'none',
                    sampling: 'average',
                    itemStyle: {
                        normal: {
                            color: 'rgb(255, 70, 131)'
                        }
                    },
                    areaStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: 'rgb(255, 158, 68)'
                            }, {
                                offset: 1,
                                color: 'rgb(255, 70, 131)'
                            }])
                        }
                    },
                    data: voltages
                }
            ]
        };

        voltagesChar.setOption(option);


    }

    function currentsInfo() {

        var chartOutChar = echarts.init(document.getElementById('charts'));
//        chartOutChar.showLoading({text: '正在努力的读取数据中...'});
        var option = {
            tooltip: {
                trigger: 'axis',
                position: function (pt) {
                    return [pt[0], '10%'];
                }
            },
            title: {
                left: 'center',
                text: '设备电流运行图',
            },
            toolbox: {
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    restore: {},
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: times
            },
            yAxis: {
                type: 'value',
                boundaryGap: [0, '100%']
            },
            dataZoom: [{
                type: 'inside',
                start: 0,
                end: 10
            }, {
                start: 0,
                end: 10,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            }],
            series: [
                {
                    name: '电流',
                    type: 'line',
                    smooth: true,
                    symbol: 'none',
                    sampling: 'average',
                    itemStyle: {
                        normal: {
//                            color: 'rgb(255, 70, 131)'
                            color: 'rgb(99, 173, 138)'
                        }
                    },
                    areaStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
//                                color: 'rgb(255, 158, 68)'
                                color: 'rgb(174, 212, 194)'
                            }, {
                                offset: 1,
//                                color: 'rgb(255, 70, 131)'
                                color: 'rgb(99, 173, 138)'
                            }])
                        }
                    },
                    data: currents
                }
            ]
        };

        chartOutChar.setOption(option);
    }

    function wattsInfo() {

        var chartOutChar = echarts.init(document.getElementById('charts'));
//        chartOutChar.showLoading({text: '正在努力的读取数据中...'});
        var option = {
            tooltip: {
                trigger: 'axis',
                position: function (pt) {
                    return [pt[0], '10%'];
                }
            },
            title: {
                left: 'center',
                text: '设备功率运行图',
            },
            toolbox: {
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    restore: {},
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: times
            },
            yAxis: {
                type: 'value',
                boundaryGap: [0, '100%']
            },
            dataZoom: [{
                type: 'inside',
                start: 0,
                end: 10
            }, {
                start: 0,
                end: 10,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            }],
            series: [
                {
                    name: '功率',
                    type: 'line',
                    smooth: true,
                    symbol: 'none',
                    sampling: 'average',
                    itemStyle: {
                        normal: {
//                            color: 'rgb(255, 70, 131)'
                            color: 'rgb(94, 156, 164)'
                        }
                    },
                    areaStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
//                                color: 'rgb(255, 158, 68)'
                                color: 'rgb(141, 185, 190)'
                            }, {
                                offset: 1,
//                                color: 'rgb(255, 70, 131)'
                                color: 'rgb(94, 156, 164)'
                            }])
                        }
                    },
                    data: watts
                }
            ]
        };

        chartOutChar.setOption(option);
    }


</script>


</body>
</html>
