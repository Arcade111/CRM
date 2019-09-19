$(function () {
    //抽取变量
    var role_datagrid = $("#role_datagrid");
    var role_dialog = $("#role_dialog");
    var role_form = $("#role_form");
    var allPermission = $("#allPermission");
    var selfPermission = $("#selfPermission");

    role_datagrid.datagrid({
        url: '/role_list',
        fitColumns: true,
        scrollbarSize:0,
        fit: true,
        striped: true,
        singleSelect: true,
        rownumbers: true,
        pagination: true,
        toolbar: "#role_toolbar",
        columns: [[
            {
                field:'hidden',
                // hidden:true
            },
            {
                title: '角色编号',
                field: 'sn',
                width: 100
            },
            {
                title: '角色名称',
                field: 'name',
                width: 100
            },
        ]],
    });

    //新增对话框
    role_dialog.dialog(
        {
            width: 550,
            height: 500,
            buttons: '#role_btns',
            closed: true
        }
    );

    allPermission.datagrid({
        title:'所有权限',
        url:'/permission_list',
        height:350,
        width:250,
        fitColumns:true,
        scrollbarSize:0,
        rownumbers: true,
        // singleSelect: true,
        columns: [[
            {
                field:'hidden',
                // hidden:true
            },
            {
                title: '权限名称',
                field: 'name',
                width: 1,
                align:'center',
            }
        ]],
        onDblClickRow :function (rowIndex, rowData){
            //先判断是否已经存在已有权限zhong
            var rows = selfPermission.datagrid('getRows');
            for (var i = 0; i < rows.length; i++) {
                //清空所有的选中
                selfPermission.datagrid('unselectAll');
                //如果已经存在，选中对应数据
                if(rowData.id == rows[i].id){
                    selfPermission.datagrid('selectRow',i);
                    return;
                }
            }
            //没有的话就添加到已有权限中
            selfPermission.datagrid('appendRow',rowData);
        },
    });

    selfPermission.datagrid({
        title:'已有权限',
        height:350,
        width:250,
        fitColumns:true,
        scrollbarSize:0,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {
                field:'hidden',
                // hidden:true
            },
            {
                title: '权限名称',
                field: 'name',
                width: 1,
                align:'center'
            }
        ]],
        //删除不想要的权限
        onDblClickRow :function (rowIndex, rowData){
            selfPermission.datagrid('deleteRow',rowIndex);
        },
    });

    var methodObj = {

        save: function () {
            //判断url是新增还是编辑
            var url;
            if ($("[name='id']").val()) {
                //更新角色
                url = "role_update";
            } else {
                //新增角色
                url = "role_save";
            }
            // call 'submit' method of form plugin to submit the form
            role_form.form('submit', {
                url: url,
                onSubmit: function (param) {
                    //表单提交之前做的动作
                    //将已有权限封装成指定格式：通过param参数去设置要传递的对象信息
                    var rows = selfPermission.datagrid('getRows');
                    for (var i = 0; i < rows.length; i++) {
                        param["permissions["+i+"].id"] = rows[i].id;
                    }
                },
                success: function (data) {
                    // console.log(data);刚传过来的不是json数据，而是json字符串
                    data = $.parseJSON(data);//转换成json数据
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                                //关闭对话框
                                role_dialog.dialog('close');
                                //重新加载数据表格
                                role_datagrid.datagrid("reload");//注意reload和load的区别在哪
                            }
                        );
                    } else {
                        $.messager.alert('温馨提示', data.msg);
                    }
                }
            });
        },

        cancel: function () {
            role_dialog.dialog('close');
        },

        add: function () {
            //清空表单数据
            role_form.form('clear');
            //清空原来有的行
            selfPermission.datagrid('loadData', {
                total: 0,
                rows: []
            });
            //设置标题
            role_dialog.dialog('setTitle', '新增角色');
            //打开对话框
            role_dialog.dialog('open');
        },

        edit: function () {
            //判断是否选中数据
            var row = role_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选择要操作数据！");
                return;
            }
            //清空数据表单
            role_dialog.form('clear');
            //设置标题
            role_dialog.dialog('setTitle', '编辑角色');

            //获得datagrid的属性对象，即通过options对象去设置要传递的参数
            var options = selfPermission.datagrid("options");
            options.url = "/getPermissionByRid";

            //回显表单：回显的是permission对象的数据
            selfPermission.datagrid('load',{
                rid:row.id
            });
            //回显表单：回显的是role的角色对象数据
            role_form.form('load',row);
            //打开对话框
            role_dialog.dialog('open');
        },

        remove: function () {
            //判断是否选中数据
            var row = role_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选择要操作数据！", "info");
                return;
            }
            $.messager.confirm('确认对话框', '您确定删除吗？', function (r) {
                if (r) {
                    $.get("/role_delete?id=" + row.id, function (data) {
                        if (data.success) {
                            //删除成功,重新加载datagrid数据表格
                            $.messager.alert("提示", data.msg, "info", function () {
                                role_datagrid.datagrid('reload');
                            });
                        } else {
                            alert(data.msg);
                        }
                    });
                }
            });
        },

        reload: function () {
            $("[name='keyword']").val("");
            role_datagrid.datagrid("reload", {
                keyword: ""
            });
        },

        searchByKeyword: function () {
            //获取输入的keyword
            var keyword = $("[name='keyword']").val();
            //重新加载datagrid表格并带上自定义参数
            role_datagrid.datagrid('load', {
                keyword: keyword
            });
        }
    }

    //统一给按钮绑定事件
    $("[data-cmd]").on('click', function () {
        var methodName = $(this).data("cmd");
        methodObj[methodName]();
    });
});