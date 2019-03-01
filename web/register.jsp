<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 28.02.2019
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<jstl:if test="${not empty requestScope.notAdd}">
    <p>This user is exists</p>
</jstl:if>

<form method="post" action="controller?action=register">
    <p><label>
        <input type="text" name="email" size="40"/>
    </label>email*</p>
    <p><label>
        <input type="password" name="password" size="20" />
    </label>password*</p>
    <p><label>
        <input type="text" name="first_name" size="30" />
    </label>first name</p>
    <p><label>
        <input type="text" name="last_name" size="30" />
    </label>last name</p>

    <p><input type="submit" value="Register" /></p>
    <p></p>
</form>

</body>
</html>

