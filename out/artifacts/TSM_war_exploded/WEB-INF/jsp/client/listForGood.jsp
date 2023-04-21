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
                            <label class="layui-form-label">ID</label>
                            <div class="layui-input-inline">
                                <input type="text" name="id" autocomplete="off" class="layui-input" required>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary"  lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
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
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">查看</a>
        </script>

    </div>
</div>
<script src="../../static/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="../../static/js/cityMapper.js" charset="utf-8"></script>

<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render(
            {
            elem: '#currentTableId',
            url: 'listForGood',
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
                {field: 'id', width: 60, title: 'ID', sort: true},
                {field: 'clientid', width: 95, title: '客户名',sort: true},
                {field: 'startS', width: 110, title: '出发地', sort: true},
                {field: 'endS', width: 110, title: '目的地',sort: true},
                {field: 'sendtimeS', title: '发货时间', minWidth: 150,sort: true},
                {field: 'recetimeS', title: '收货时间', minWidth: 150,sort: true},
                {field: 'detail', width: 140, title: '描述'},
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
                {
                    page: {
                        curr: 1
                    }
                    , where: {
                        id :data.field.id,
                        clientid :data.field.clientid,
                        // start :data.field.start,
                        // end :data.field.end,
                    }
                }, 'data');
            return false;
        });

        /**
         * toolbar监听事件
         * ADD
         * delete
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            // 监听添加操作
            if (obj.event === 'add') {
                var index = layer.open({
                    title: '添加用户',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: 'gotoAddGood',
                    //刷新
                    end:function (){
                        table.reload('currentTableId')
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }
            // 监听删除操作
            else if (obj.event === 'delete') {
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                var arr=[];
                for (index in data){
                    arr.push(data[index].id)
                }
                console.log(arr);
                $.ajax({
                    url: "DelGood",
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


        //EDit
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;


            if (obj.event === 'edit')
            {
                var index = layer.open({
                    title: '编辑订单',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: 'gotoEditGood?id='+data.id,
                    end:function (){
                        table.reload('currentTableId')
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            }
            else if (obj.event === 'delete')
            {
                var index = layer.open({
                    title: '查看订单',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: 'gotoQS?id='+data.id,
                    end:function (){
                        table.reload('currentTableId')
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            }
        });

    });
</script>

</body>
</html>
