<%--
  Created by IntelliJ IDEA.
  User: STY
  Date: 2023/3/27
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../static/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../../static/css/public.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div class="layui-form layuimini-form">
    <div class="layui-form-item">
        <label class="layui-form-label required">用户ID</label>
        <div class="layui-input-block">
            <input type="text" name="clientid" lay-verify="required" lay-reqtext="用户ID不能为空" placeholder="请输入用户名" value="" class="layui-input">
            <tip>填写自己账号的名称。</tip>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">目的地</label>
        <div class="layui-input-block">
            <select name="end" lay-verify="required">
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
            <select name="start" lay-verify="required">
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
        <label class="layui-form-label required">手机号码</label>
        <div class="layui-input-block">
            <input type="text" name="number" lay-verify="required" lay-reqtext="手机不能为空" placeholder="请输入手机" value="10086" class="layui-input">
        </div>
    </div>


    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注信息</label>
        <div class="layui-input-block">
            <textarea name="detail" class="layui-textarea" placeholder="请输入备注信息"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</div>
<script src="../../static/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form','jquery'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.$;

        //监听提交
        form.on('submit(saveBtn)', function (data) {
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
                    var iframeIndex = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(iframeIndex);
                }
            });
            return false;
        });
    });
</script>
</body>
</html>