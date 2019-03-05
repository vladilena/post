<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 28.02.2019
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- LOCALIZATION -->
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<!-- LOCALIZATION -->

<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>

<!-- LOCALIZATION -->
<title><fmt:message key="text.title.users"/></title>
<!-- LOCALIZATION -->

<jstl:if test="${not empty requestScope.invalidlogin}">
    <div class="alert alert-danger" role="alert">Wrong email format. Try again</div>
</jstl:if>
<jstl:if test="${not empty requestScope.invalidPassword}">
    <div class="alert alert-danger" role="alert">Wrong password format. Try again</div>
</jstl:if>
<jstl:if test="${not empty requestScope.invalidFirstName}">
    <div class="alert alert-danger" role="alert">Wrong first name format. Try again</div>
</jstl:if>
<jstl:if test="${not empty requestScope.invalidLastName}">
    <div class="alert alert-danger" role="alert">Wrong last name format. Try again</div>
</jstl:if>
<jstl:if test="${not empty requestScope.notAdd}">
    <div class="alert alert-danger" role="alert">User with this email is already exists</div>
</jstl:if>

<form role="form" method="post" action="controller?action=register">
    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" name ="email" id="email" placeholder="Your email">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" name="password" id="password" placeholder="Password">
        <p class="help-block">Password must have 6-10 letters, you can use letters, numbers and "_"</p>
    </div>
    <div class="form-group">
        <label for="firstName">First name</label>
        <input type="text" class="form-control" name="firstName" id="firstName" placeholder="First name">
    </div>
    <div class="form-group">
        <label for="lastName">Last name</label>
        <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Last name">
    </div>
    <button type="submit" class="btn btn-success">Registration</button>
</form>


</body>
</html>

