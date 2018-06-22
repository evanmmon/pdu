<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/"/>
</head>
<body>
    <h3>${msg}</h3>
    <form action="doLogin" method="post">
        <input type="text" name="username"/><br/>
        <input type="text" name="password"/><br/>
        <input type="submit" />
    </form>
</body>
</html>
