<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 01.03.2019
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <title><fmt:message key="text.title.successful"/></title>
</head>
<body>
<br/>
<br/>


<c:choose>
<c:when test="${not empty sessionScope.user}">
<div class="menu-bar">
    <a class="btn btn-info" href="controller?action=main" role="button"><fmt:message key="text.main.page"/></a>
</div>

<br/>
<br/>
<p class="text-center"><fmt:message key="text.message.sent"/></p>
</c:when>
    <c:otherwise>
        <div class="row">
            <div class="col-md text-center">
                <div class="menu-bar">
                    <br/>
                    <br/>
                    <a class="btn btn-info" href="register.jsp" role="button"><fmt:message key="text.registration"/></a>
                    <a class="btn btn-info" href="login.jsp" role="button"><fmt:message key="text.log.in"/></a></div>
            </div>
        </div>
    </c:otherwise>
</c:choose>



</body>
</html>
