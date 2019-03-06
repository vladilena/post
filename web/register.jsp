<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 28.02.2019
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />


<html>
<head>
    <title><fmt:message key="text.title.register"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>

<c:if test="${not empty requestScope.invalidlogin}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.wrong.email.format"/></div>
</c:if>
<c:if test="${not empty requestScope.invalidPassword}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.wrong.password.format"/></div>
</c:if>
<c:if test="${not empty requestScope.invalidFirstName}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.wrong.first.name.format"/></div>
</c:if>
<c:if test="${not empty requestScope.invalidLastName}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.wrong.last.name.format"/></div>
</c:if>
<c:if test="${not empty requestScope.notAdd}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.user.exists"/></div>
</c:if>

<form role="form" method="post" action="controller?action=register">
    <div class="form-group">
        <label for="email"><fmt:message key="text.email"/></label>
        <input type="email" class="form-control" name ="email" id="email" placeholder="<fmt:message key="text.your.email"/>" required>
    </div>
    <div class="form-group">
        <label for="password"><fmt:message key="text.password"/></label>
        <input type="password" class="form-control" name="password" id="password" placeholder="<fmt:message key="text.your.password"/>" required>
        <p class="help-block"><fmt:message key="text.help.password"/></p>
    </div>
    <div class="form-group">
        <label for="firstName"><fmt:message key="text.first.name"/></label>
        <input type="text" class="form-control" name="firstName" id="firstName" placeholder="<fmt:message key="text.first.name"/>" required>
    </div>
    <div class="form-group">
        <label for="lastName"><fmt:message key="text.last.name"/></label>
        <input type="text" class="form-control" name="lastName" id="lastName" placeholder="<fmt:message key="text.last.name"/>" required>
    </div>
    <button type="submit" class="btn btn-success"><fmt:message key="text.registration"/></button>
</form>


</body>
</html>

