<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="text.title.error"/> 404</title>
</head>
<body>
<div class="row">
    <div class="col-md text-center">
        <br/>
        <br/>
        <h2 class="uppercase"><fmt:message key="text.error"/> 404</h2>
    </div>
</div>
</body>
</html>
