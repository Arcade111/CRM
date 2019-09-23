<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2019/9/16
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.ucar.com/crm/functions" prefix="myFn" %>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <%@include file="/common/common.jsp" %>
    <script type="text/javascript" src="/js/employee.js"></script>
</head>
<body style="width: 100%;height: 100%">
<div id="emp_datagrid"></div>
<div id="emp_toolbar">
    <c:if test="${myFn:checkPermission('com.ucar.crm.web.controller.EmployeeController:save')}">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
    </c:if>
    <c:if test="${myFn:checkPermission('com.ucar.crm.web.controller.EmployeeController:edit')}">
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    </c:if>
    <c:if test="${myFn:checkPermission('com.ucar.crm.web.controller.EmployeeController:updateState')}">
        <a id="emp_remove_btn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="remove">离职</a>
    </c:if>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    <input type="text" name="keyword"/>
    <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchByKeyword">查询</a>
</div>
<div id="emp_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<div id="emp_dialog">
    <form id="emp_form" method="post" style="margin-top: 20px">
        <table align="center">
            <input type="hidden" name="id"/>
            <tr>
                <td>用户名:</td>
                <td><input name="username" type="text"/></td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input name="email" type="text"/></td>
            </tr>
            <tr>
                <td>入职时间:</td>
                <td><input name="inputtime" type="text" class="easyui-datebox"/></td>
            </tr>
            <tr>
                <td>部门:</td>
                <td>
                    <input name="dept.id" type="text" class="easyui-combobox"
                           data-options="
                                panelHeight:'auto',
                                url:'/dept_selectAll',
                                valueField: 'id',
                                textField: 'name',
                    "/>
                </td>
            </tr>
            <tr>
                <td>是否管理员:</td>
                <td>
                    <input name="admin" type="text" class="easyui-combobox"
                           data-options="
                                panelHeight:'auto',
                                valueField: 'value',
                                textField: 'label',
                                value:'false',
                                data:[
                                {
                                    label:'是',
                                    value:true
                                },{
                                    label:'否',
                                    value:false
                                }
                                ]
                    "/>
                </td>
            </tr>
            <tr>
                <td>角色:</td>
                <td>
                    <input id="roles_combobox" type="text" class="easyui-combobox"
                           data-options="
                                panelHeight:'auto',
                                multiple:true,
                                url:'/role_selectAll',
                                valueField: 'id',
                                textField: 'name',
                    "/>
                </td>
            </tr>
        </table>
    </form>

</div>
</body>
</html>
