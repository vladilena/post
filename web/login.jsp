<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 28.02.2019
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title><fmt:message key="text.title.login"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>

<c:if test="${not empty requestScope.notExists}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.error.not.exists"/></div>
</c:if>
<c:if test="${not empty requestScope.wrongInput}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.error.wrong.input"/></div>
</c:if>

<form role="form" class="form-inline" method="post" action="controller?action=login">
    <div class="form-group">
        <label for="email"><fmt:message key="text.email"/></label>
        <input type="email" class="form-control" name="email" id="email" placeholder="<fmt:message key="text.your.email"/>" required>
    </div>
    <div class="form-group">
        <label for="pass"><fmt:message key="text.password"/></label>
        <input type="password" class="form-control" name="password" id="pass" placeholder="<fmt:message key="text.your.password"/>" required>
    </div>
    <button type="submit" class="btn btn-success"><fmt:message key="text.log.in"/></button>
</form>


</body>
</html>

