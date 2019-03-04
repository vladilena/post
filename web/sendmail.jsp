<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 01.03.2019
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SendMail</title>
</head>
<body>

<jstl:if test="${not empty requestScope.notAdd}">
    <p>Something wrong. Try again</p>
</jstl:if>

    <form method="post" action="controller?action=send">

        <p><label>
            <input type="text" name="recipient" size="100" />
        </label>recipient email</p>
        <p><label>
            <input type="text" name="title" size="50" />
        </label>title</p>
        <p><label>
            <input type="text" name="tags" size="50" />
        </label>input tags with ","</p>
        <p><label>
            <input type="text" name="message" size="1000" />
        </label>your message</p>
        <p><input type="submit" value="Send" /></p>
        <p></p>
    </form>


</body>
</html>
