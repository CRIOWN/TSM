<%--
  Created by IntelliJ IDEA.
  User: STY
  Date: 2023/3/8
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理-登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="../../static/lib/layui-v2.6.3/css/layui.css" media="all">
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        html, body {width: 100%;height: 100%;overflow: hidden}
        body {background: #1E9FFF;}
        body:after {content:'';background-repeat:no-repeat;background-size:cover;-webkit-filter:blur(3px);-moz-filter:blur(3px);-o-filter:blur(3px);-ms-filter:blur(3px);filter:blur(3px);position:absolute;top:0;left:0;right:0;bottom:0;z-index:-1;}
        .layui-container {width: 100%;height: 100%;overflow: hidden}
        .admin-login-background {width:360px;height:300px;position:absolute;left:50%;top:40%;margin-left:-180px;margin-top:-100px;}
        .logo-title {text-align:center;letter-spacing:2px;padding:14px 0;}
        .logo-title h1 {color:#1E9FFF;font-size:25px;font-weight:bold;}
        .login-form {background-color:#fff;border:1px solid #fff;border-radius:3px;padding:14px 20px;box-shadow:0 0 8px #eeeeee;}
        .login-form .layui-form-item {position:relative;}
        .login-form .layui-form-item label {position:absolute;left:1px;top:1px;width:38px;line-height:36px;text-align:center;color:#d2d2d2;}
        .login-form .layui-form-item input {padding-left:36px;}
        .captcha {width:60%;display:inline-block;}
        .captcha-img {display:inline-block;width:34%;float:right;}
        .captcha-img img {height:34px;border:1px solid #e6e6e6;height:36px;width:100%;}
    </style>
</head>
<body>
<div class="layui-container">
    <div class="admin-login-background">
        <div class="layui-form login-form">
            <form class="layui-form" action="" method="post">
            <div class="form-group">
                <div class="layui-form-item logo-title">
                    <h1>物流管理系统登录</h1>
                </div>
                <div class="layui-form-item">
                    <label  for="username" class="layui-icon layui-icon-username"></label>
                    <input type="text" name="username" lay-verify="required|account" placeholder="请输入用户名" autocomplete="off" class="layui-input" value="">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="password"></label>
                    <input type="password" name="password" lay-verify="required|password" placeholder="密码" autocomplete="off" class="layui-input" value="">
                    <span style="color: red;font-weight: bold"> ${errorpwd} </span>
                </div>
                <div class="layui-form-item">
                    <input type="checkbox"  value="true" id="rememenber" title="记住密码">
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">用户类型</label>
                    <div class="layui-input-block">
                        <select name="kind" lay-verify="required">
                            <option value=""></option>
                            <option value="0">管理员</option>
                            <option value="1">员工</option>
                            <option value="2">客户</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button type="submit" class="layui-btn layui-btn layui-btn-normal layui-btn-fluid" lay-submit="" lay-filter="login">登 录</button>
                </div>
                <div class="layui-form-item">
                <button type="button" id="register-btn" class="layui-btn layui-btn layui-btn-normal layui-btn-fluid" lay-submit="" lay-filter="reg">注 册</button>
                 </div>
            </div></form>
        </div>
    </div>
</div>

<script src="../../static/lib/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
<script src="../../static/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="../../static/js/jquery.cookie.js" charset="utf-8"></script>
<script src="../../static/lib/jq-module/jquery.particleground.min.js" charset="utf-8"></script>
<script>
    layui.use(['form','jquery'], function () {
        var form = layui.form,
            layer = layui.layer;
            // $ = layui.jquery;

        // 登录过期的时候，跳出ifram框架
        if (top.location != self.location) top.location = self.location;

        function setCookie(){
            var username = $('input[name="username"]').val();
            var password = $('input[name="password"]').val();
            console.log(username+"::"+password)
            var aa = $("input[type='checkbox']").is(":checked");//获取是否选中
            if(aa==true){//如果选中-->记住密码登录
                $.cookie("username",username.trim(),{ expires: 7 }); //7天
                $.cookie("password",password.trim(),7);
            }else{//如果没选中-->不记住密码登录
                $.cookie("password", "");
                $.cookie("username", "");
                alert("没有记住密码")
            }
        }

        function getCookie() { //获取cookie
            var username = $.cookie("username"); //获取cookie中的用户名
            var password = $.cookie("password"); //获取cookie中的登录密码
            if (password) { //密码存在的话把“记住用户名和密码”复选框勾选住
                $("input[name='rememenber']").prop("checked", true);
            }
            if (username) { //用户名存在的话把用户名填充到用户名文本框
                $("input[name='username']").val(username);
            } else {
                $("input[name='username']").val("");
            }
            if (password) { //密码存在的话把密码填充到密码文本框
                $("input[name='password']").val(password);
            } else {
                $("input[name='password']").val("");
            }
        }


        // 粒子线条背景
        $(document).ready(function(){
            $('.layui-container').particleground({
                dotColor:'#7ec7fd',
                lineColor:'#7ec7fd'
            });
        });
        getCookie()
        // 进行登录操作
        form.on('submit(login)', function (data) {
            setCookie()
            data = data.field;
            if (data.username == '') {
                layer.msg('用户名不能为空');
                return false;
            }
            if (data.password == '') {
                layer.msg('密码不能为空');
                return false;
            }
            if (data.kind == '') {
                layer.msg('验证码不能为空');
                return false;
            }
            else if(data.kind == '0'){
                $.ajax({
                url:"${pageContext.request.contextPath}/admin/logDeal",
                type:"post",
                data:JSON.stringify(data),
                dataType:"json",
                contentType:'application/json',
                success:function (data){
                    console.log(data);
                    if(data.code==200){
                        location.href="/admin/index";
                    }
                    else {
                        layer.msg(data.msg);
                    }
                }

            })
            }
            else if(data.kind == '1'){
                $.ajax({
                    url:"${pageContext.request.contextPath}/staff/logDeal",
                    type:"post",
                    data:JSON.stringify(data),
                    dataType:"json",
                    contentType:'application/json',
                    success:function (data){
                        console.log(data);
                        if(data.code==200){
                            location.href="/staff/index";
                        }
                        else {
                            layer.msg(data.msg);
                        }
                    }

                })

            }
            else if(data.kind == '2'){
                $.ajax({
                    url:"${pageContext.request.contextPath}/client/logDeal",
                    type:"post",
                    data:JSON.stringify(data),
                    dataType:"json",
                    contentType:'application/json',
                    success:function (data){
                        console.log(data);
                        if(data.code==200){
                            location.href="/client/index";
                        }
                        else {
                            layer.msg(data.msg);
                        }
                    }

                })

            }
            return false;
        });

        $('#register-btn').click(function () {
            // 执行注册逻辑...
            // 页面跳转
            window.location.href = "${pageContext.request.contextPath}/client/gotologreg";
        });
    });
</script>
</body>
</html>