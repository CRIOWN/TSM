<%--
  Created by IntelliJ IDEA.
  User: STY
  Date: 2023/4/4
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../static/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../../static/css/public.css" media="all">
</head>
<body>
<div class="layui-form-item">
    <div class="layui-input-block">
        <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
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
            $.ajax({
                url: "function",
                type: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(data.field),
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg("修改成功::" + data.msg);

                    } else {
                        layer.msg("修改失败::" + data.msg);
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
