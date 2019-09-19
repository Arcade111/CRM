$(function () {
    //抽取变量
    var emp_datagrid = $("#emp_datagrid");
    var emp_dialog = $("#emp_dialog");
    var emp_form = $("#emp_form");
    //方法统一管理


    emp_datagrid.datagrid({
        url: '/employee_list',
        fitColumns: true,
        scrollbarSize:0,
        fit: true,
        striped: true,
        singleSelect: true,
        rownumbers: true,
        pagination: true,
        toolbar: "#emp_toolbar",
        columns: [[
            {
                title: '用户名',
                field: 'username',
                width: 100
            },
            {
                title: '真实姓名',
                field: 'realname',
                width: 100
            },
            {
                title: '电话',
                field: 'tel',
                width: 100
            },
            {
                title: '邮箱',
                field: 'email',
                width: 100
            },
            {
                title: '入职时间',
                field: 'inputtime',
                width: 100
            },
            {
                title: '状态',
                field: 'state',
                width: 100,
                formatter: stateFormatter
            },
            {
                title: '是否管理员',
                field: 'admin',
                width: 100,
                formatter: adminFormatter
            },
            {
                title: '部门',
                field: 'dept',
                width: 100,
                formatter: deptFormatter
            },
        ]],
        onClickRow: function (rowIndex, rowData) {
            // console.log(rowData);
            //判断选中员工是否已经离职
            if (!rowData.state) {
                //已经离职
                $('#emp_remove_btn').linkbutton('disable');
            } else {
                $('#emp_remove_btn').linkbutton('enable');
            }
        },
    });

    //部门显示格式
    function deptFormatter(value, row, index) {
        return value ? value.name : "";
    }

    //在职显示格式
    function stateFormatter(value, row, index) {
        return value ? "在职" : "<font color='#d3d3d3'>离职</font>";
    }

    //是否管理员显示格式
    function adminFormatter(value, row, index) {
        return value ? "<font color='red'>是</font>" : "否";
    }

    //新增对话框
    emp_dialog.dialog(
        {
            width: 310,
            height: 300,
            buttons: '#emp_btns',
            closed: true
        }
    );

    var methodObj = {

        save: function () {
            //判断url是新增还是编辑
            var url;
            if ($("[name='id']").val()) {
                url = "employee_update";
            } else {
                url = "employee_save";
            }
            // call 'submit' method of form plugin to submit the form
            emp_form.form('submit', {
                url: url,
                onSubmit: function () {
                    //表单提交之前做的动作
                    // do some check
                    // return false to prevent submit;
                },
                success: function (data) {
                    // console.log(data);刚传过来的不是json数据，而是json字符串
                    data = $.parseJSON(data);//转换成json数据
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                                //关闭对话框
                                emp_dialog.dialog('close');
                                //重新加载数据表格
                                emp_datagrid.datagrid("reload");//注意reload和load的区别在哪
                            }
                        );
                    } else {
                        $.messager.alert('温馨提示', data.msg);
                    }
                }
            });
        },

        cancel: function () {
            emp_dialog.dialog('close');
        },

        add: function () {
            //清空表单数据
            emp_form.form('clear');
            //设置标题
            emp_dialog.dialog('setTitle', '新增员工');
            //打开对话框
            emp_dialog.dialog('open');
        },

        edit: function () {
            //判断是否选中数据
            var row = emp_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选择要操作数据！");
                return;
            }
            //清空数据表单
            emp_dialog.form('clear');
            //设置标题
            emp_dialog.dialog('setTitle', '编辑员工');
            //row.dept-->特殊属性的处理
            if (row.dept) {
                row["dept.id"] = row.dept.id;
            }
            //数据回显
            emp_form.form('load', row);
            //打开对话框
            emp_dialog.dialog('open');
        },

        remove: function () {
            //判断是否选中数据
            var row = emp_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选择要操作数据！", "info");
                return;
            }
            $.messager.confirm('确认对话框', '您确定离职该员工吗？', function (r) {
                /*if (r){
                    // 退出操作;
                    //获取要删除的id
                    $.get("/employee_delete?id="+row.id,function(data){
                        if(data.success){
                            //删除成功
                            $.messager.alert('温馨提示', data.msg, 'info', function () {
                                    //重新加载数据表格
                                    emp_datagrid.datagrid("reload");//注意reload和load的区别在哪
                                }
                            );
                        }else{
                            //删除失败
                            alert(data.msg);
                        }
                    });
                }*/
                if (r) {
                    $.get("/employee_remove?id=" + row.id, function (data) {
                        if (data.success) {
                            //离职更改成功,重新加载datagrid数据表格
                            $.messager.alert("提示", data.msg, "info", function () {
                                emp_datagrid.datagrid('reload');
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
            emp_datagrid.datagrid("reload", {
                keyword: ""
            });
        },

        searchByKeyword: function () {
            //获取输入的keyword
            var keyword = $("[name='keyword']").val();
            //重新加载datagrid表格并带上自定义参数
            emp_datagrid.datagrid('load', {
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