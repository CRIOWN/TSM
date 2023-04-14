<%--
  Created by IntelliJ IDEA.
  User: STY
  Date: 2023/4/4
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../static/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../../static/css/public.css" media="all">
    <style>
        .layui-form-item .layui-input-company {width: auto;padding-right: 10px;line-height: 38px;}
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <div class="layui-form layuimini-form">
            <div class="layui-form-item">
                <label class="layui-form-label required">旧的密码</label>
                <div class="layui-input-block">
                    <input type="password" id="oldpwd" name="oldPwd" lay-verify="required" lay-reqtext="旧的密码不能为空" placeholder="请输入旧的密码"  value="" class="layui-input">
                    <tip>填写自己账号的旧的密码。</tip>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">新的密码</label>
                <div class="layui-input-block">
                    <input type="password" id="pwd" name="pwd" lay-verify="required" lay-reqtext="新的密码不能为空" placeholder="请输入新的密码"  value="" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">新的密码</label>
                <div class="layui-input-block">
                    <input type="password" id="again_password" name="again_password" lay-verify="required" lay-reqtext="新的密码不能为空" placeholder="请输入新的密码"  value="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../static/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="../../static/js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script>
    layui.use(['form','jquery','miniTab'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.$,
            miniTab = layui.miniTab;

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            let pwd=document.getElementById("pwd").value;
            let againPwd=document.getElementById("again_password").value;
            let oldpwd=document.getElementById("oldpwd").value;
            console.log(pwd+"::"+againPwd)
            if(pwd!=againPwd)
            {
                layer.msg("请输入相同密码");
                return  false;
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/admin/changePassword",
                type:"post",
                data:JSON.stringify(data.field),
                dataType:"json",
                contentType:'application/json',
                success:function (data){
                    console.log(data);
                    if(data.code==200){
                        layer.msg("修改成功");
                        location.href="/admin/index";
                    }
                    else {
                        layer.msg(data.msg);
                    }
                }

            })
            return false;
        });

    });
</script>
</body>
</html>