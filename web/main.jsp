<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 28.02.2019
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<jstl:choose>
    <jstl:when test="${not empty sessionScope.user}">
        <p>${sessionScope.user.email}</p>
        <p><a href="controller?action=send">Send email</a></p>
        <p><a href="controller?action=logout">Logout</a></p>
    </jstl:when>
    <jstl:otherwise>
        <p><a href="register.jsp">Register</a></p>
        <p><a href="login.jsp">Login</a></p>
    </jstl:otherwise>
</jstl:choose>


</body>
</html>

