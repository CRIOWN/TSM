<%--
  Created by IntelliJ IDEA.
  User: STY
  Date: 2023/3/19
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>首页三</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../static/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../../static/lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" href="../../static/css/public.css" media="all">
    <style>
        .top-panel {
            border: 1px solid #eceff9;
            border-radius: 5px;
            text-align: center;
        }
        .top-panel > .layui-card-body{
            height: 60px;
        }
        .top-panel-number{
            line-height:60px;
            font-size: 30px;
            border-right:1px solid #eceff9;
        }
        .top-panel-tips{
            line-height:30px;
            font-size: 12px
        }
    </style>
</head>
<body>
<!--<div class="layuimini-container">-->
<div class="layuimini-main">

    <div class="layui-row layui-col-space15">
        <div class="layui-col-xs12 layui-col-md3">

            <div class="layui-card top-panel">
                <div class="layui-card-header">员工数量</div>
                <div class="layui-card-body">
                    <div class="layui-row layui-col-space5">
                        <div class="layui-col-xs9 layui-col-md9 top-panel-number">
                            ${StaffNum}
                        </div>

                    </div>
                </div>
            </div>

        </div>
        <div class="layui-col-xs12 layui-col-md4">

            <div class="layui-card top-panel">
                <div class="layui-card-header">客户数量</div>
                <div class="layui-card-body">
                    <div class="layui-row layui-col-space5">
                        <div class="layui-col-xs9 layui-col-md9 top-panel-number">
                           ${ClientNum}
                        </div>

                    </div>
                </div>
            </div>

        </div>
        <div class="layui-col-xs12 layui-col-md4">
            <div class="layui-card top-panel">
                <div class="layui-card-header">货物数量</div>
                <div class="layui-card-body">
                    <div class="layui-row layui-col-space5">
                        <div class="layui-col-xs9 layui-col-md9 top-panel-number">
                            ${GoodNum}
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div class="layui-row layui-col-space15">
        <div class="layui-col-xs12 layui-col-md12">
            <div id="Emap" style="background-color:#ffffff;min-height:400px;padding: 10px"></div>
        </div>
    </div>


    <div class="layui-row layui-col-space15">
        <div class="layui-col-xs12 layui-col-md6">
            <div id="EGood" style="background-color:#ffffff;min-height:300px;padding: 10px"></div>
        </div>
        <div class="layui-col-xs12 layui-col-md6">
            <div id="EPerson" style="background-color:#ffffff;min-height:300px;padding: 10px"></div>
        </div>
    </div>


</div>
<!--</div>-->
<%--<script src="https://cdn.jsdelivr.net/npm/echarts@5.4.1/dist/echarts.min.js "></script>--%>
<script src="../../static/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<%--<script src="//api.map.baidu.com/api?v=1.0&type=webgl&ak=pl2seBoP0MbDQuMUlogePygiCPAhKl5c"></script>--%>
<script src="../../static/js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script src="../../static/echarts/echarts.js" charset="utf-8"></script>

<script>
    layui.use(['layer','jquery'], function () {
        var $ = layui.jquery;

        /**
         *
         * 地图
         */
            // 添加连线
        var geoCoordMap = {
                '南京市':[118.796877,32.060255],
                '苏州市':[120.585315,31.298886],
                '无锡市':[120.305456,31.570037],
                '常州市':[119.981861,31.771397],
                '南通市':[120.873801,32.014665],
                '扬州市':[119.421003,32.393159],
                '镇江市':[119.452753,32.204402],
                '徐州市':[117.184811,34.261792],
                '淮安市':[119.015285,33.610353],
                '盐城市':[120.163561,33.347382]
            };
        var convertData = function(data) {
            var res =[];
            for (var i = 0; i < data.length; i++) {
                var geoCoord = geoCoordMap[data[i].name];
                if (geoCoord) {
                    res.push({
                        name: data[i].name,
                        value: geoCoord.concat(data[i].value)
                    });
                }
            }
            console.log("convertData::"+res);
            return res;
        };
        //基础绘制
        var Emap = echarts.init(document.getElementById('Emap'), 'walden');
        Emap.showLoading();
        //https://geo.datav.aliyun.com/areas_v3/bound/320000_full.json
        $.get('https://geo.datav.aliyun.com/areas_v3/bound/320000_full.json', function (geoJson)
        {
            //console.log("test1");
            echarts.registerMap('jiangsu', geoJson);
            Emap.setOption(
                (option={
                    title: {
                        text: '物流路径',
                        subtext: '江苏省',
                        //副标题跳转地
                        // sublink:'http://zh.wikipedia.org/wiki/%E9%A6%99%E6%B8%AF%E8%A1%8C%E6%94%BF%E5%8D%80%E5%8A%83#cite_note-12'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '地名'
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        left: 'right',
                        top: 'center',
                        feature: {
                            dataView: { readOnly: false },
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    series: [
                        {
                            name: '江苏路径图',
                            type: 'map',
                            map: 'jiangsu',
                            label: {
                                show: true
                            },
                            roam: true,
                            data: [
                                //赋值
                                // { name: '中西区', value: 20057.34 },
                            ],
                            markPoint:{
                                symbol:"circle",
                                symbolSize: 10,
                                label: {
                                    show: false,
                                    formatter: '{b}'
                                },
                                itemStyle: {
                                    color: '#F00'
                                },
                                data: [
                                    { name: '南京市', coord: [118.802422, 32.064652] },
                                    { name: '无锡市', coord: [120.31191, 31.491169] },
                                    { name: '徐州市', coord: [117.284124, 34.205768] },
                                    { name: '常州市', coord: [119.973987, 31.810689] },
                                    { name: '苏州市', coord: [120.585315, 31.298886] },
                                    { name: '南通市', coord: [120.856394, 32.016212] },
                                    { name: '连云港市', coord: [119.22295, 34.59669] },
                                    { name: '淮安市', coord: [119.021265, 33.597506] },
                                    { name: '盐城市', coord: [120.163562, 33.347383] },
                                    { name: '扬州市', coord: [119.412966, 32.39421] },
                                    { name: '镇江市', coord: [119.425836, 32.187849] },
                                    { name: '泰州市', coord: [119.915176, 32.484882] },
                                    { name: '宿迁市', coord: [118.275162, 33.963008] }
                                ]
                            },
                            lines:{
                                data:[[118.796877,32.060255],[120.585315,31.298886],[120.305456,31.570037],
                                    [119.981861,31.771397]],
                                lineStyle: {
                                    color:"#0e00fe",
                                    width: 4,
                                    type:'solid'
                                }
                            }
                        },
                        {
                            type: 'lines',
                            coordinateSystem:'geo',
                            polyline:true,
                            data:[[118.796877,32.060255],[120.585315,31.298886],[120.305456,31.570037],
                                [119.981861,31.771397]],
                            lineStyle: {
                                color:"#0e00fe",
                                width: 0,
                                type:'solid'
                            },
                            effect: {
                                constantSpeed: 20,
                                show: true,
                                trailLength: 0.1,
                                symbolSize: 1.5
                            },
                            zlevel: 1
                        }
                    ]
                })
            );
          //  console.log("test2");
            Emap.hideLoading();
        });

        var seriesdata = [
            {
                name: '江苏省主要城市',
                type: 'lines',
                zlevel: 1,
                effect: {
                    show: true,
                    period: 6,
                    trailLength: 0.7,
                    color: '#fff',
                    symbolSize: 3
                },
                lineStyle: {
                    normal: {
                        color: '#a6c84c',
                        width: 0,
                        curveness: 0.2
                    }
                },
                data: convertData([
                    {name: '南京市', value: 100},
                    {name: '苏州市', value: 50},
                    {name: '无锡市', value: 30},
                    {name: '常州市', value: 20},
                    {name: '南通市', value: 10},
                    {name: '扬州市', value: 5},
                    {name: '镇江市', value: 3},
                    {name: '徐州市', value: 2},
                    {name: '淮安市', value: 1},
                    {name: '盐城市', value: 1}
                ])
            },
            {
                name: '江苏省主要城市',
                type: 'effectScatter',
                coordinateSystem: 'geo',
                zlevel: 2,
                rippleEffect: {
                    brushType: 'stroke'
                },
                label: {
                    normal: {
                        show: true,
                        position: 'right',
                        formatter: '{b}'
                    }
                },
                symbolSize: function (val) {
                    return val[2] / 10;
                },
                itemStyle: {
                    normal: {
                        color: '#a6c84c'
                    }
                },
                data: convertData([
                    {name: '南京市', value: 100},
                    {name: '苏州市', value: 50},
                    {name: '无锡市', value: 30},
                    {name: '常州市', value: 20},
                    {name: '南通市', value: 10},
                    {name: '扬州市', value: 5},
                    {name: '镇江市', value: 3},
                    {name: '徐州市', value: 2},
                    {name: '淮安市', value: 1},
                    {name: '盐城市', value: 1}
                ])
            }
        ];


        /**
         * 玫瑰图表
         * 人数占比
         */
        var EPerson = echarts.init(document.getElementById('EPerson'), 'walden');
        EPerson.showLoading();
        $.ajax({
            type:"post",
            async:true,//异步请求 可进行其他操作
            url:"Main1Data1",
            data:{},
            dataType:"json",
            success: function (result)
            {
                //读map
               // console.log("M1::"+result.data['admin']);
                let admin=result.data['admin'];
                let staff=result.data['staff'];
                let client=result.data['client'];

                EPerson.setOption( {
                    title: {
                        text: '人数分布',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['管理员', '员工', '客户']
                    },
                    series: [
                        {
                            name: '人数',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            roseType: 'radius',
                            data:[
                                {value: admin,name:"管理员"},
                                {value:staff,name:"员工"},
                                {value:client,name:"客户"}
                            ],
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(100, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                });
                EPerson.hideLoading();
            }
        })



        /**
         * 柱状图
         * 件数
         */
        var EGood = echarts.init(document.getElementById('EGood'), 'walden');
        EGood.showLoading();
        $.ajax({
            type:"post",
            async:true,//异步请求 可进行其他操作
            url:"Main1Data2",
            data:{},
            dataType:"json",
            success: function (result)
            {
                console.log("==========")
               // let aresult =jQuery.parseJSON(result);
                console.log("M2::"+result); console.log("==========")
                EGood.setOption({
                    title: {
                        text: '各地发货数量'
                    },
                    tooltip: {},
                    legend: {
                        data:['货物数量']
                    },
                    xAxis: {
                        data: ['南京市','苏州市','无锡市','常州市','南通市','扬州市','镇江市','徐州市','淮安市','盐城市','连云港','泰州市','宿迁市']
                    },
                    yAxis: {},
                    series: [{
                        name: '货物数量',
                        type: 'bar',
                        data:result.data
                        // 动态更新
                    }]
                });
                EGood.hideLoading();

            }
        })

        // echarts 窗口缩放自适应
        window.onresize = function () {
            Emap.resize();
        }

    });
</script>
</body>
</html>
