<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2019/9/16
  Time: 15:09
  To change this trolelate use File | Settings | File Trolelates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <%@include file="/common/common.jsp" %>
    <script type="text/javascript" src="/js/role.js"></script>
</head>
<body style="width: 100%;height: 100%">
<div id="role_datagrid"></div>
<div id="role_toolbar">
    <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    <a id="role_remove_btn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="remove">删除</a>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    <input type="text" name="keyword"/>
    <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchByKeyword">查询</a>
</div>
<div id="role_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<div id="role_dialog">
    <form id="role_form" method="post" style="margin-top: 20px">
        <table align="center">
            <input type="hidden" name="id"/>
            <tr>
                <td>角色编号:<input name="sn" type="text"/></td>
                <td>角色名称：<input name="name" type="text"/></td>
            </tr>
            <tr>
                <td>
                    <table id="allPermission"></table>
                </td>
                <td>
                    <table id="selfPermission"></table>
                </td>
            </tr>
        </table>
    </form>

</div>
</body>
</html>
