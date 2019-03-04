<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 28.02.2019
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Addcategory</title>
</head>
<body>
<jstl:if test="${not empty requestScope.notAdd}">
    <p>This category is exists</p>
</jstl:if>

<form method="post" action="controller?action=addcategory">
    <p><label>
        <input type="text" name="category" size="30"/>
    </label>category name</p>
    <p><label>

    <p><input type="submit" value="Add new category"/></p>
    <p></p>
</form>

</body>
</html>