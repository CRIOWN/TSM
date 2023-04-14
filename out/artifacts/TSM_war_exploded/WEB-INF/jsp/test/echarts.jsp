<%--
  Created by IntelliJ IDEA.
  User: STY
  Date: 2023/3/19
  Time: 0:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
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
<div class="layui-row layui-col-space15">
    <div class="layui-col-xs12 layui-col-md12">
        <div id="Emap" style="background-color:#ffffff;min-height:400px;padding: 10px"></div>
    </div>
</div>



<%--<script src="https://cdn.jsdelivr.net/npm/echarts@5.4.1/dist/echarts.min.js "></script>--%>
<script src="../../static/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<%--<script src="//api.map.baidu.com/api?v=1.0&type=webgl&ak=pl2seBoP0MbDQuMUlogePygiCPAhKl5c"></script>--%>
<script src="../../static/js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script src="../../static/echarts/echarts.js" charset="utf-8"></script>
<script>
    layui.use(['layer','jquery'], function ()
    {
        var $ = layui.jquery;

        //基础绘制
        var Emap = echarts.init(document.getElementById('Emap'), 'walden');
        Emap.showLoading();

        $.get('https://geo.datav.aliyun.com/areas_v3/bound/320000_full.json', function (geoJson) {
            //console.log("test1");
            echarts.registerMap('jiangsu', geoJson);
            Emap.setOption(
                (option = {
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
                                    {name: '连云港市', coord: [119.22295, 34.59669]},
                                    {name: '淮安市', coord: [119.021265, 33.597506]},
                                    {name: '盐城市', coord: [120.163562, 33.347383]},
                                    {name: '扬州市', coord: [119.412966, 32.39421]},
                                    {name: '镇江市', coord: [119.425836, 32.187849]},
                                    {name: '泰州市', coord: [119.915176, 32.484882]},
                                    {name: '宿迁市', coord: [118.275162, 33.963008]}
                                ]
                            },
                            lines: {
                                data: [[118.796877, 32.060255], [120.585315, 31.298886], [120.305456, 31.570037],
                                    [119.981861, 31.771397]],
                                lineStyle: {
                                    color: "#0e00fe",
                                    width: 4,
                                    type: 'solid'
                                }
                            }
                        },
                        {
                            type: 'lines',
                            coordinateSystem: 'geo',
                            polyline: true,
                            data: [[118.796877, 32.060255], [120.585315, 31.298886], [120.305456, 31.570037],
                                [119.981861, 31.771397]],
                            lineStyle: {
                                color: "#0e00fe",
                                width: 0,
                                type: 'solid'
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
    });
</script>


</body>
</html>