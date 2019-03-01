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

    <form method="post" action="controller?action=send">
        <p><label>
            <input type="number" name="sender" size="100"/>
        </label>sender_id</p>
        <p><label>
            <input type="number" name="recipient" size="100" />
        </label>recipient_id</p>
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
