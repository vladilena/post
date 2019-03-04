<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 28.02.2019
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>

<jstl:if test="${not empty requestScope.notExists}">
    <div class="alert alert-danger" role="alert">This user not exist</div>
</jstl:if>

<form role="form" class="form-inline" method="post" action="controller?action=login">
    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" name="email" id="email" placeholder="Your email">
    </div>
    <div class="form-group">
        <label for="pass">Password</label>
        <input type="password" class="form-control" name="password" id="pass" placeholder="Password">
    </div>
    <button type="submit" class="btn btn-success">Log in</button>
</form>
<%--
<form method="post" action="controller?action=login">
    <p><label>
        <input type="text" name="email" size="40"/>
    </label>email</p>
    <p><label>
        <input type="password" name="password" size="20" />
    </label>password</p>
    <p><input type="submit" value="Login" /></p>
    <p></p>
</form>
--%>

</body>
</html>
