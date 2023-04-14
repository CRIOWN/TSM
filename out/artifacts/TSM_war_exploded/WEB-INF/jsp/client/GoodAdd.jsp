<%--
  Created by IntelliJ IDEA.
  User: STY
  Date: 2023/4/3
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>增加订单</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../static/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../../static/css/public.css" media="all">
    <link rel="stylesheet" href="../../static/js/lay-module/step-lay/step.css" media="all">
</head>
<body>
<div class="layui-form layuimini-form">
    <div class="layuimini-main">
        <div class="layui-fluid">
            <div class="layui-card">
                <div class="layui-card-body" style="padding-top: 40px;">
                    <div class="layui-carousel" id="stepForm" lay-filter="stepForm" style="margin: 0 auto;">
                        <div carousel-item>
                            <div>
                                <form class="layui-form" style="margin: 0 auto;max-width: 460px;padding-top: 40px;">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label required">目的地</label>
                                        <div class="layui-input-block">
                                            <select id="end1" name="end" lay-verify="required">
                                                <option value=""></option>
                                                <option value="0">南京市</option>
                                                <option value="1">苏州市</option>
                                                <option value="2">无锡市</option>
                                                <option value="3">常州市</option>
                                                <option value="4">南通市</option>
                                                <option value="5">扬州市</option>
                                                <option value="6">镇江市</option>
                                                <option value="7">徐州市</option>
                                                <option value="8">淮安市</option>
                                                <option value="9">盐城市</option>
                                                <option value="10">连云港</option>
                                                <option value="11">泰州市</option>
                                                <option value="12">宿迁市</option>
                                            </select>
                                        </div>

                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label required">出发地</label>
                                        <div class="layui-input-block">
                                            <select id="start1" name="start" lay-verify="required">
                                                <option value=""></option>
                                                <option value="0">南京市</option>
                                                <option value="1">苏州市</option>
                                                <option value="2">无锡市</option>
                                                <option value="3">常州市</option>
                                                <option value="4">南通市</option>
                                                <option value="5">扬州市</option>
                                                <option value="6">镇江市</option>
                                                <option value="7">徐州市</option>
                                                <option value="8">淮安市</option>
                                                <option value="9">盐城市</option>
                                                <option value="10">连云港</option>
                                                <option value="11">泰州市</option>
                                                <option value="12">宿迁市</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">备注说明:</label>
                                        <div class="layui-input-block">
                                            <textarea id="detail1" name="detail" placeholder="备注" value="" class="layui-textarea"></textarea>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <div class="layui-input-block">
                                            <button class="layui-btn" lay-submit lay-filter="formStep">
                                                &emsp;下一步&emsp;
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div>
                                <form class="layui-form" style="margin: 0 auto;max-width: 460px;padding-top: 40px;">

                                    <div class="layui-form-item">
                                        <label class="layui-form-label" >下单时间:</label>
                                        <div class="layui-input-block" id="current-time">
                                            <div class="layui-form-mid layui-word-aux"></div>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">出发地:</label>
                                        <div class="layui-input-block">
                                            <div class="layui-form-mid layui-word-aux">
                                                <span id="start2" style="font-size: 18px;color: #333;">####</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">目的地:</label>
                                        <div class="layui-input-block">
                                            <div class="layui-form-mid layui-word-aux">
                                                <span id="end2" style="font-size: 18px;color: #333;">####</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="layui-form-item">
                                        <label class="layui-form-label">备注说明:</label>
                                        <div class="layui-input-block">
                                            <div id="detail2" class="layui-form-mid layui-word-aux">备注说明</div>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <div class="layui-input-block">
                                            <button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
                                            <button class="layui-btn " lay-submit lay-filter="formStep2">
                                                &emsp;确认订单&emsp;
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div>
                                <div style="text-align: center;margin-top: 90px;">
                                    <i class="layui-icon layui-circle"
                                       style="color: white;font-size:30px;font-weight:bold;background: #52C41A;padding: 20px;line-height: 80px;">&#xe605;</i>
                                    <div style="font-size: 24px;color: #333;font-weight: 500;margin-top: 30px;">
                                        订单创立成功
                                    </div>
                                     </div>
                                <div style="text-align: center;margin-top: 50px;">
                                    <button class="layui-btn next">再入一笔</button>
                                    <button class="layui-btn layui-btn-primary" lay-submit lay-filter="gotomain">查看账单</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div style="color: #666;margin-top: 30px;margin-bottom: 40px;padding-left: 30px;">
                        <h3>说明：</h3><br>
                        <h4>此为模拟系统</h4>
                        <p>只支持江苏省</p>
<%--                        <br><h4>入款到现金</h4>--%>
<%--                        <p>如果需要，这里可以放一些关于产品的常见问题说明。</p>--%>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<script src="../../static/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="../../static/js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script src="../../static/js/cityMapper.js" charset="utf-8"></script>
<script>
    layui.use([ 'form', 'jquery','step'], function () {
        var $ = layui.$,
            layer = layui.layer,
            form = layui.form,
            step = layui.step;


        step.render({
            elem: '#stepForm',
            filter: 'stepForm',
            width: '100%', //设置容器宽度
            stepWidth: '750px',
            height: '500px',
            stepItems: [{
                title: '填写订单信息'
            }, {
                title: '确认订单信息'
            }, {
                title: '完成'
            }]
        });

        // 获取当前时间
        var currentTime = new Date();
        // 格式化时间，例如：2023年3月27日 15:30
        var formattedTime = currentTime.getFullYear() + "年" + (currentTime.getMonth() + 1) + "月" + currentTime.getDate() + "日 " + currentTime.getHours() + ":" + currentTime.getMinutes();
        document.getElementById("current-time").innerHTML = formattedTime;

        //convertIdToCityName()
        //下一步
        form.on('submit(formStep)', function () {
            console.log(document.getElementById("start1").value);
            console.log(convertIdToCityName(document.getElementById("start1").value));
            document.getElementById("start2").innerHTML =  convertIdToCityName(document.getElementById("start1").value);
            document.getElementById("end2").innerHTML = convertIdToCityName( document.getElementById("end1").value);
            document.getElementById("detail2").innerHTML =document.getElementById("detail1").value;

            step.next('#stepForm');
            return false;
        });

        //确认订单
        form.on('submit(formStep2)', function (data) {
            console.log("============");
            console.log(data.field);
            console.log("============");
            $.ajax({
                url: "AddGood",
                type: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(data.field),
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg("添加成功::" + data.msg);

                    } else {
                        layer.msg("添加失败::" + data.msg);
                    }

                }
            });
            step.next('#stepForm');
            return false;
        });

        //查看订单
        form.on('submit(gotomain)', function(data) {
            var index = layer.open({
                title: '添加用户',
                type: 2,
                shade: 0.2,
                maxmin:true,
                shadeClose: true,
                area: ['100%', '100%'],
                content: 'gotoMain1',

            });
            $(window).on("resize", function () {
                layer.full(index);
            });
            return false; // 阻止表单提交
        });
        $('.pre').click(function () {
            step.pre('#stepForm');
        });

        $('.next').click(function () {
            step.next('#stepForm');
        });
    })
</script>
</body>
</html>