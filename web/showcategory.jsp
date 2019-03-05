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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>

<jstl:choose>
    <jstl:when test="${not empty sessionScope.user}">
        <p class="text-center">Hello ${sessionScope.user.firstName} ${sessionScope.user.lastName}</p>
        <div class="menu-bar">
            <a class="btn btn-info" href="controller?action=main" role="button">Main page</a>
        </div>
        <br/>
        <br/>
        <jstl:choose>
            <jstl:when test="${not empty sessionScope.noInfo}">
                <p>There are no messages with this parameters</p>
            </jstl:when>
            <jstl:otherwise>


                <jstl:forEach items="${requestScope.mails}" var="mail">
                    <ul class="menu">
                        <li><b>Sender:</b> ${mail.sender}</li>
                        <li><b>Recipient:</b> ${mail.recipient}</li>
                        <li><b>Date and time:</b> ${mail.dateTime}</li>
                        <li><b>Title:</b> ${mail.title}</li>
                        <li><b>Tags: </b>${mail.tags}</li>
                        <li><b>Category:</b> ${mail.category}</li>
                        <li><b>Message:</b> ${mail.message}</li>
                    </ul>

                    <div class="menu-bar">

                        <form role="form" class="btn btn-success btn-sm" method="post" action="controller?action=delete">
                            <input type="hidden" name="id" value="${mail.id}">
                            <button type="submit" class="btn btn-success btn-sm">Delete message</button>
                        </form>
                        <form role="form" class="btn btn-success btn-sm" method="post" action="controller?action=reportedspam">
                            <input type="hidden" name="id" value="${mail.id}">
                            <button type="submit" class="btn btn-success btn-sm">Reported spam</button>
                        </form>
                    </div>
                </jstl:forEach>
            </jstl:otherwise>
        </jstl:choose>
    </jstl:when>
    <jstl:otherwise>
        <div class="menu-bar">
            <a class="btn btn-info" href="register.jsp" role="button">Register</a>
            <a class="btn btn-info" href="login.jsp" role="button">Login</a></div>
    </jstl:otherwise>
</jstl:choose>

</body>
