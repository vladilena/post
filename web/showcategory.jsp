<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 03.03.2019
  Time: 14:56
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
        <p>Hello ${sessionScope.user.firstName} ${sessionScope.user.lastName}</p>
        <p><a href="controller?action=main">Main page</a></p>
        <br>
        <br>
        <jstl:choose>
            <jstl:when test="${not empty sessionScope.notFind}">
                <p>There are no messages with this parameters</p>
            </jstl:when>
            <jstl:otherwise>
               <jstl:forEach items="${requestScope.mails}" var="mail">
                <br/>
                <p>Sender: ${mail.sender}</p>
                <p>Recipient: ${mail.recipient}</p>
                <p>Date and time: ${mail.dateTime}</p>
                <p>Title: ${mail.title}</p>
                <p>Tags: ${mail.tags}</p>
                <p>Category: ${mail.category}</p>
                <p>Message: ${mail.message}</p>
            </jstl:forEach>
            </jstl:otherwise>
        </jstl:choose>
    </jstl:when>
    <jstl:otherwise>
        <p><a href="register.jsp">Register</a></p>
        <p><a href="login.jsp">Login</a></p>
    </jstl:otherwise>
</jstl:choose>

</body>
