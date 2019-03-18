<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 03.03.2019
  Time: 14:56
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
    <title><fmt:message key="text.title.showcategory"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>


<c:choose>
    <c:when test="${not empty sessionScope.user}">

        <c:if test="${not empty requestScope.wrongInput}">
            <div class="alert alert-danger" role="alert"><fmt:message key="text.error.wrong.input"/></div>
        </c:if>

        <p class="text-center"><fmt:message
                key="text.hello"/> ${sessionScope.user.firstName} ${sessionScope.user.lastName}</p>
        <div class="menu-bar">
            <a class="btn btn-info" href="controller?action=main" role="button"><fmt:message key="text.main.page"/></a>
        </div>
        <br/>
        <br/>
        <c:choose>
            <c:when test="${not empty requestScope.noInfo}">
                <div class="alert alert-danger" role="alert"><fmt:message key="text.error.no.messages"/></div>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <div class="col-3"></div>
                    <div class="col-9">
                        <c:forEach items="${requestScope.mails}" var="mail">
                            <ul class="menu">
                                <li><b><fmt:message key="text.sender.data"/></b> ${mail.sender}</li>
                                <li><b><fmt:message key="text.recipient.data"/></b> ${mail.recipient}</li>
                                    <%--<li><b><fmt:message key="text.time.data"/></b> <javatime:parseLocalDateTime value="${mail.dateTime}" pattern="yyyy-MM-dd hh:mm" var="parsedDate" /></li>--%>
                                <li><b><fmt:message key="text.time.data"/></b> ${mail.dateTime}</li>
                                <li><b><fmt:message key="text.title.data"/></b> ${mail.title}</li>
                                <li><b><fmt:message key="text.tags.data"/></b> ${mail.tags}</li>
                                <li><b><fmt:message key="text.category.data"/></b> ${mail.category.uaCategoryName}</li>
                                <li><b><fmt:message key="text.custom.category.data"/></b> ${mail.customCategory.categoryName}</li>
                                <li><b><fmt:message key="text.message.data"/></b> ${mail.message}</li>
                            </ul>

                            <div class="menu-bar">

                                <form role="form" class="btn btn-success btn-sm" method="post"
                                      action="controller?action=delete">
                                    <input type="hidden" name="id" value="${mail.id}">
                                    <button type="submit" class="btn btn-success btn-sm"><fmt:message
                                            key="text.delete.message"/></button>
                                </form>
                                <form role="form" class="btn btn-success btn-sm" method="post"
                                      action="controller?action=reportedspam">
                                    <input type="hidden" name="id" value="${mail.id}">
                                    <button type="submit" class="btn btn-success btn-sm"><fmt:message
                                            key="text.report.spam"/></button>
                                </form>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>


    </c:when>
    <c:otherwise>
        <div class="menu-bar">
            <a class="btn btn-info" href="controller?action=registerredir" role="button"><fmt:message key="text.log.in"/></a>
            <a class="btn btn-info" href="controller?action=loginredir" role="button"><fmt:message key="text.registration"/></a></div>
    </c:otherwise>
</c:choose>

</body>
