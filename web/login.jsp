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
</head>
<body>

<jstl:if test="${not empty requestScope.notExists}">
    <p>This user not exist</p>
</jstl:if>

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

</body>
</html>

