<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 01.03.2019
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <title><fmt:message key="text.title.sendmail"/></title>
</head>
<body>
<%--<c:if test="${not empty requestScope.invalidRecipient}">--%>
    <%--<div class="alert alert-danger" role="alert"><fmt:message key="text.alert.invalid.recipient"/></div>--%>
<%--</c:if>--%>
<%--<c:if test="${not empty requestScope.invalidTitle}">--%>
    <%--<div class="alert alert-danger" role="alert"><fmt:message key="text.alert.invalid.title"/></div>--%>
<%--</c:if>--%>
<%--<c:if test="${not empty requestScope.invalidTags}">--%>
    <%--<div class="alert alert-danger" role="alert"><fmt:message key="text.alert.invalid.tags"/></div>--%>
<%--</c:if>--%>
<%--<c:if test="${not empty requestScope.invalidMessage}">--%>
    <%--<div class="alert alert-danger" role="alert"><fmt:message key="text.alert.invalid.message"/></div>--%>
<%--</c:if>--%>
<c:if test="${not empty requestScope.problem}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.problem.with.sending"/></div>
</c:if>
<c:if test="${not empty requestScope.wrongInput}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.error.wrong.input"/></div>
</c:if>
<div class="row">
    <div class="col-3"></div>
    <div class="col-6">
        <br/>
        <br/>
        <form role="form" method="post" action="controller?action=send">
            <div class="form-group">
                <label for="recipient"><fmt:message key="text.recipient"/></label>
                <input type="email" class="form-control" name="recipient" id="recipient"
                       placeholder="<fmt:message key="text.input.recipient"/>" required>
            </div>
            <div class="form-group">
                <label for="title"><fmt:message key="text.title"/></label>
                <input type="text" class="form-control" name="title" id="title"
                       placeholder="<fmt:message key="text.input.title"/>" required>
            </div>
            <div class="form-group">
                <label for="tags"><fmt:message key="text.tags"/></label>
                <input type="text" class="form-control" name="tags" id="tags"
                       placeholder="<fmt:message key="text.input.tags"/>" required>
            </div>
            <div class="form-group">
                <label for="message"><fmt:message key="text.message"/></label>
                <input type="text" class="form-control" name="message" id="message"
                       placeholder="<fmt:message key="text.input.message"/>" required>
            </div>
            <button type="submit" class="btn btn-success"><fmt:message key="text.send"/></button>
        </form>
    </div>
    <div class="col-3"></div>
</div>
</body>
</html>
