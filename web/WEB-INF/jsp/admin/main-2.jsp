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
        <div class="layui-col-xs12 layui-col-md4">
            <div class="layui-card top-panel">
                <div class="layui-card-header">未发货数量</div>
                <div class="layui-card-body">
                    <div class="layui-row layui-col-space5">
                        <div class="layui-col-xs9 layui-col-md9 top-panel-number">
                            ${noDone}
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
<script src="../../static/js/cityMapper.js" charset="utf-8"></script>
<script>
    layui.use(['layer','jquery'], function () {
        var $ = layui.jquery;
        /**
         * 地图
         */
        var pathnode=[];
        var lineData=[];
        const myData ={
            "id":1,
            "clientid":1
        }

        //基础绘制
        var Emap = echarts.init(document.getElementById('Emap'), 'walden');
        Emap.showLoading();
        $.ajax({
            url:"showTsp",
            type:"post",
            data:JSON.stringify(myData),
            dataType:"json",
            contentType:'application/json',
            success:function (data){
                pathnode.push(...data.data)
                console.log(pathnode)
                for(let i=0;i<pathnode.length-1;i++)
                {
                    let from= convertIdToCityName(pathnode[i])
                    let to = convertIdToCityName(pathnode[i+1])
                    let frompoint=convertIdToCityLocal(pathnode[i]);
                    let topoint=convertIdToCityLocal(pathnode[i+1]);
                    lineData.push({
                        point:[from,to],
                        coords: [frompoint,topoint]
                    })
                }

                //基础绘制
                var option;
                $.get('https://geo.datav.aliyun.com/areas_v3/bound/320000_full.json', function (geoJson) {
                    echarts.registerMap('jiangsu', geoJson);
                    option = {
                        title: {
                            text: '物流路径',
                            subtext: '江苏省',
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
                                dataView: {readOnly: false},
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        geo: {
                            show: false,
                            map: 'jiangsu',
                            type: 'map',
                            roam: true,
                            label: {
                                normal: {
                                    // 显示省份标签
                                    show: false,
                                    textStyle: {
                                        color: '#fff',
                                        fontSize: 10
                                    }
                                },
                                emphasis: {
                                    // 对应的鼠标悬浮效果
                                    show: true,
                                    // 选中后的字体样式
                                    textStyle: {
                                        color: '#000',
                                        fontSize: 14
                                    }
                                }
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
                                roam: false,
                                data: [
                                ],
                                markPoint: {
                                    symbol: "circle",
                                    symbolSize: 10,
                                    label: {
                                        show: false,
                                        formatter: '{b}'
                                    },
                                    itemStyle: {
                                        color: '#F00'
                                    },
                                    data: [
                                        {name: '南京市', coord: [118.802422, 32.064652]},
                                        {name: '无锡市', coord: [120.31191, 31.491169]},
                                        {name: '徐州市', coord: [117.284124, 34.205768]},
                                        {name: '常州市', coord: [119.973987, 31.810689]},
                                        {name: '苏州市', coord: [120.585315, 31.298886]},
                                        {name: '南通市', coord: [120.856394, 32.016212]},
                                        {name:'连云港市',coord: [119.22295, 34.59669]},
                                        {name: '淮安市', coord: [119.021265, 33.597506]},
                                        {name: '盐城市', coord: [120.163562, 33.347383]},
                                        {name: '扬州市', coord: [119.412966, 32.39421]},
                                        {name: '镇江市', coord: [119.425836, 32.187849]},
                                        {name: '泰州市', coord: [119.915176, 32.484882]},
                                        {name: '宿迁市', coord: [118.275162, 33.963008]}
                                    ]
                                },
                            },
                            {
                                name: '',
                                type: 'scatter',
                                coordinateSystem: 'geo',
                                color: ['#000'],
                                tooltip: {
                                    position: "right",
                                    color: "#000",
                                    formatter(d) {
                                        console.log(d)
                                        return `<div style="padding: 5px 10px;">【${d.data.name}】</div>`;
                                    },
                                },
                                data: [
                                    {name: '南京市', coord: [118.802422, 32.064652]},
                                    {name: '无锡市', coord: [120.31191, 31.491169]},
                                    {name: '徐州市', coord: [117.284124, 34.205768]},
                                    {name: '常州市', coord: [119.973987, 31.810689]},
                                    {name: '苏州市', coord: [120.585315, 31.298886]},
                                    {name: '南通市', coord: [120.856394, 32.016212]},
                                    {name: '连云港市', coord: [119.22295, 34.59669]},
                                    {name: '淮安市', coord: [119.021265, 33.597506]},
                                    {name: '盐城市', coord: [120.163562, 33.347383]},
                                    {name: '扬州市', coord: [119.412966, 32.39421]},
                                    {name: '镇江市', coord: [119.425836, 32.187849]},
                                    {name: '泰州市', coord: [119.915176, 32.484882]},
                                    {name: '宿迁市', coord: [118.275162, 33.963008]}
                                ],
                            },
                            {
                                type: 'lines',
                                coordinateSystem: 'geo',
                                effect: {
                                    constantSpeed: 20,
                                    show: true,
                                    trailLength: 0.1,
                                    symbolSize: 1.5
                                },
                                lineStyle: {
                                    type: 'solid',
                                    width: 3,
                                    opacity: 1,
                                    curveness: 0,
                                    orient: 'horizontal',
                                    color: "#f80e0e",
                                },
                                tooltip: {
                                    position: "right",
                                    color: "#000",
                                    formatter(d) {
                                        //console.log(d.data.point[0]);
                                        // console.log(d.data.point[1]);
                                        return '<div style="padding: 5px 10px;"> 【' + d.data.point[0] + '】< ---- >【' + d.data.point[1] + '】</div>';;
                                    },
                                },
                                show: true,
                                data: lineData,
                                zlevel:3

                            }
                        ]
                    };
                    Emap.setOption(option);
                    //console.log("do get"+option);
                    Emap.hideLoading();
                });


            }

        })


        /**
         *
         * 收货
         */
        var EPerson = echarts.init(document.getElementById('EPerson'), 'walden');
        EPerson.showLoading();
        $.ajax({
            type:"post",
            async:true,//异步请求 可进行其他操作
            url:"Main2Data2",
            data:{},
            dataType:"json",
            success: function (result)
            {
                console.log("==========")
                // let aresult =jQuery.parseJSON(result);
                console.log("M2::"+result); console.log("==========")
                EPerson.setOption({
                    title: {
                        text: '各地收货数量'
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
                EPerson.hideLoading();

            }
        })



        /**
         * 柱状图
         * 发货
         */
        var EGood = echarts.init(document.getElementById('EGood'), 'walden');
        EGood.showLoading();
        $.ajax({
            type:"post",
            async:true,//异步请求 可进行其他操作
            url:"Main2Data1",
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
