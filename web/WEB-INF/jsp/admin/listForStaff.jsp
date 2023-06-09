<%--
  Created by IntelliJ IDEA.
  User: STY
  Date: 2023/3/11
  Time: 15:02
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
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>查询界面</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">用户姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="name" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">用户性别</label>
                            <div class="layui-input-inline">
                                <input type="text" name="sex" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">用户城市</label>
                            <div class="layui-input-inline">
                                <input type="text" name="local" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">用户职业</label>
                            <div class="layui-input-inline">
                                <input type="text" name="career" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button  type="submit" class="layui-btn layui-btn-primary"  lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

    </div>
</div>
<script src="../../static/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render({
            elem: '#currentTableId',
            url: 'listForStaff',
            toolbar: '#toolbarDemo',
            method:'post',
            contentType:'application/json',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'name', width: 180, title: '姓名'},
                {field: 'sex', width: 80, title: '性别',sort: true},
                {field: 'local', width: 120, title: '城市',sort: true},
                {field: 'number', title: '电话号码', minWidth: 70},
                {field: 'career', title: '职位', minWidth: 80,sort: true},
                {field: 'detail', width: 180, title: '描述'},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
             limits: [10, 15, 20, 25, 50, 100],
             limit: 15,
            page: false,
            skin: 'line'
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            var result = JSON.stringify(data.field);
            //执行搜索重载
            table.reload('currentTableId',
                {page: {
                        curr: 1
                    },
                 where: {
                     name :data.field.name,
                     sex :data.field.sex,
                     local :data.field.local,
                     career:data.field.career

                } ,

                },
                'data');

            return false;
        });

        /**
         * toolbar监听事件
         * 添加用户
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加用户',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: 'gotoAddStaff',
                    //刷新
                    end:function (){
                        table.reload('currentTableId')
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                var arr=[];
                for (index in data){
                    arr.push(data[index].id)
                }
                console.log(arr);
                $.ajax({
                    url: "DelStaff",
                    type: "POST",
                    dataType: "json",
                    contentType:"application/json",
                    data:JSON.stringify(arr) ,
                    success: function (data) {
                        if (data.code == 0) {
                            layer.msg("删除成功::" + data.msg);
                            table.reload('currentTableId');

                        } else {
                            layer.msg("删除失败::" + data.msg);
                        }
                        var iframeIndex = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(iframeIndex);
                    }
                });
            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {

                var index = layer.open({
                    title: '编辑用户',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content:  'gotoEditStaff?id='+data.id,
                    end:function (){
                        table.reload('currentTableId')
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                    let arry=[];
                    arry.push(data.id);
                    console.log(arry);
                    $.ajax({
                        url: "DelStaff",
                        type: "POST",
                        dataType: "json",
                        contentType:"application/json",
                        data:JSON.stringify(arry) ,
                        success: function (data) {
                            if (data.code == 0) {
                                layer.msg("删除成功::" + data.id);
                                table.reload('currentTableId');

                            } else {
                                layer.msg("删除失败::" + data.id);
                            }
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                        }
                    });
                });
            }
        });

    });
</script>

</body>
</html>