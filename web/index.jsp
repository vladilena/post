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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <title>Title</title>
</head>
<body>

<jstl:choose>
    <jstl:when test="${not empty sessionScope.user}">

        <p class="text-center">Hello ${sessionScope.user.firstName} ${sessionScope.user.lastName}</p>

        <div class="menu-bar">
            <a class="btn btn-info" href="controller?action=send" role="button">Send email</a>
            <a class="btn btn-info" href="controller?action=logout" role="button">Logout</a></div>
        <br/>
        <br/>

    </jstl:when>
    <jstl:otherwise>
        <div class="menu-bar">
            <a class="btn btn-info" href="register.jsp" role="button">Register</a>
            <a class="btn btn-info" href="login.jsp" role="button">Login</a></div>
    </jstl:otherwise>
</jstl:choose>

</body>
</html>

