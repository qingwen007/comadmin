layui.use(['layer','form','table'], function() {
    var layer = layui.layer,
        $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        t;                  //表格数据变量

    t = {
        elem: '#inventTable',
        url:'/admin/system/user/inventList',
        method:'post',
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
            //,curr: 5 //设定初始在第 5 页
            groups: 6, //只显示 1 个连续页码
            first: "首页", //显示首页
            last: "尾页", //显示尾页
            limits:[3,10, 20, 30]
        },
        width: $(parent.window).width()-223,
        cols: [[
            {type:'checkbox'},
            {field:'itemId', title: '物料编号', width:'13%'},
            {field:'itemName',  title: '物料名称',    width:'13%'},
            {field:'itemGroup',     title: '物料组',    width:'13%' },
            {field:'englishName',       title: '英文名称',    width:'13%'},
            {field:'colorName', title: '颜色', width:'13%'},
            {field:'material',    title: '材质',width:'13%'},
            {fixed: 'right', align: 'center', toolbar: '#inventBar'}
        ]]
    };
    table.render(t);

    //监听工具条
    table.on('tool(inventList)', function(obj){
        var data = obj.data;
       /* if(obj.event === 'lock'){
            var locked = data.locked;
            layer.confirm("你确定要" + (locked ? "重新启用" : "锁定") + "该用户么？" ,{btn:['是的,我确定','我再想想']},
                function(){
                    $.post("/admin/system/user/lock",{"id":data.id},function (res){
                        if(res.success){
                            layer.msg("操作成功",{time: 1000},function(){
                                table.reload('userTable', t);
                            });
                        }else{
                            layer.msg(res.message);
                        }
                    });
                }
            );
        }*/


        if(obj.event === 'edit'){
            var editIndex = layer.open({
                title : "编辑物料",
                type : 2,
                content : "/admin/system/user/editInvent?itemId="+data.itemId,
                success : function(layero, index){
                    setTimeout(function(){
                        layer.tips('点击此处返回物料列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },500);
                }
            });
            //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
            $(window).resize(function(){
                layer.full(editIndex);
            });
            layer.full(editIndex);
        }

        if(obj.event === "del"){
            layer.confirm("你确定要删除该物料么？",{btn:['是的,我确定','我再想想']},
                function(){
                    $.post("/admin/system/user/deleteInvent",{"id":data.id},function (res){
                        if(res.success){
                            layer.msg("删除成功",{time: 1000},function(){
                                table.reload('inventTable', t);
                            });
                        }else{
                            layer.msg(res.message);
                        }

                    });
                }
            );
        }
    });

    //功能按钮
    var active={
        addUser : function(){
            var addIndex = layer.open({
                title : "添加物料",
                type : 2,
                content : "/admin/system/user/addInvent",
                success : function(layero, addIndex){
                    setTimeout(function(){
                        layer.tips('点击此处返回物料列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },500);
                }
            });
            //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
            $(window).resize(function(){
                layer.full(addIndex);
            });
            layer.full(addIndex);
        },
        deleteSome : function(){                        //批量删除
            var checkStatus = table.checkStatus('inventTable'),
                data = checkStatus.data;
            if(data.length > 0){
                for(var i=0;i<data.length;i++){
                    var d = data[i];
                    if(d.adminUser){
                        layer.msg("不能删除超级管理员");
                        return false;
                    }
                }
                layer.confirm("你确定要删除这些物料么？",{btn:['是的,我确定','我再想想']},
                    function(){
                        var deleteindex = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
                        $.ajax({
                            type:"POST",
                            url:"/admin/system/user/deleteSomeInvent",
                            dataType:"json",
                            contentType:"application/json",
                            data:JSON.stringify(data),
                            success:function(res){
                                layer.close(deleteindex);
                                if(res.success){
                                    layer.msg("删除成功",{time: 1000},function(){
                                        table.reload('inventTable', t);
                                    });
                                }else{
                                    layer.msg(res.message);
                                }
                            }
                        });
                    }
                )
            }else{
                layer.msg("请选择需要删除的物料",{time:1000});
            }
        }
    };

    $('.layui-inline .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //搜索
    form.on("submit(searchForm)",function(data){
        t.where = data.field;
        table.reload('inventTable', t);
        return false;
    });

});