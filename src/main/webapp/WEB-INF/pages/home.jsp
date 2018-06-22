<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/"/>
</head>
<body>
    <h1>首页, 欢迎你：<shiro:principal/></h1>

    <shiro:hasRole name="user">
        <h3>欢迎你，用户</h3>
    </shiro:hasRole>

    <shiro:hasAnyRoles name="user,admin">
        <h3>欢迎你，管理员或用户</h3>
    </shiro:hasAnyRoles>

    <shiro:lacksRole name="user">
        <h3>欢迎你，用户1</h3>
    </shiro:lacksRole>

    <shiro:hasPermission name="sys:product:select">
        <input type="button" value="查看"/><br/>
    </shiro:hasPermission>

    <shiro:hasPermission name="sys:product:add">
        <input type="button" value="添加"/><br/>
        <a href="list">列表</a><br/>
    </shiro:hasPermission>

    <shiro:hasPermission name="sys:product:delete">
        <input type="button" value="删除"/><br/>
    </shiro:hasPermission>

    <shiro:hasPermission name="sys:product:update">
        <input type="button" value="修改"/><br/>
    </shiro:hasPermission>
</body>
</html>
