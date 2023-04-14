<%--
  Created by IntelliJ IDEA.
  User: STY
  Date: 2023/3/8
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/logDeal" method="post">
    <div class="form-group">
        <label>id：</label>
        <input type="int" name="id" class="form-control" required>
    </div>
    <div class="form-group">
        <label>密码</label>
        <input type="text" name="pwd" class="form-control" required>
    </div>
    <span style="color: red;font-weight: bold"> ${errorpwd} </span>
    <input type="submit" class="form-control" value="登录">
</form>
<%
    response.sendRedirect("/admin/log");
%>


</body>
</html>
