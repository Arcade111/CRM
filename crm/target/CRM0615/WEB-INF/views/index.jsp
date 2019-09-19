<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
<%--    <c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>--%>
    <%@include file="/common/common.jsp"%>
    <script type="text/javascript" src="/js/index.js"></script>
</head>
<body>
<div class="easyui-layout" fit="true">
    <div data-options="region:'north'" style="height:50px" border="false">
        <h1 align="center">管理系统之jquery练手</h1>
    </div>

    <div data-options="region:'south',split:true" style="height:50px;">
        <p align="center"> 版权所有，盗版必究</p>
    </div>

    <div data-options="region:'west',split:true" style="width:200px;">
        <div class="easyui-accordion" fit="true">
            <div title="菜单" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
                <ul id="tree"></ul>
            </div>
            <div title="公司介绍" data-options="iconCls:'icon-help'" style="padding:10px;">
                xxxxxxxxxxx
            </div>
        </div>
    </div>

    <div data-options="region:'center',iconCls:'icon-ok'">
        <div id="tabs">
            <div title="主页">
            </div>
        </div>
    </div>
</div>

</body>
</html>
