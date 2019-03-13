<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 28.02.2019
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>--%>

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
    <title><fmt:message key="text.title.main"/></title>
</head>

<body>

<c:choose>
    <c:when test="${not empty sessionScope.user}">
        <div class="row">
            <div class="col-md text-center">
                <p class="text-center"><fmt:message
                        key="text.hello"/> ${sessionScope.user.firstName} ${sessionScope.user.lastName}</p>

                <div class="menu-bar">
                    <a class="btn btn-info" href="sendmail.jsp" role="button"><fmt:message
                            key="text.send.email"/></a>
                    <a class="btn btn-info" href="controller?action=logout" role="button"><fmt:message
                            key="text.logout"/></a>
                </div>
                <br/>
            </div>
        </div>


        <div class="row">
            <div class="col-2">
                <div class="menu-bar">
                    <c:forEach items="${requestScope.categories}" var="category">
                        <form role="form" class="btn btn-success btn-sm" method="post"
                              action="controller?action=getmailbycategory">
                            <button type="submit" class="btn btn-success btn-sm" name="categoryName"
                                    value="${category.categoryName}">${category.categoryName}</button>
                        </form>
                    </c:forEach>
                </div>
                <div class="menu-bar">
                    <c:forEach items="${requestScope.custom_categories}" var="custom_category">
                        <form role="form" class="btn btn-success btn-sm" method="post"
                              action="controller?action=getmailbycustomcategory">
                            <button type="submit" class="btn btn-success btn-sm" name="categoryName"
                                    value="${custom_category.categoryName}">${custom_category.categoryName}</button>
                        </form>
                    </c:forEach>


                    <c:if test="${not empty requestScope.wrongCategory}">
                        <div class="alert alert-danger" role="alert"><fmt:message
                                key="text.error.wrong.category.input"/></div>
                    </c:if>


                    <form role="form" method="post" action="controller?action=addcategory">
                        <div class="form-inline">
                            <input type="text" class="form-control" name="newcategory"
                                   placeholder="<fmt:message key="text.category.name"/>" required>
                            <button type="submit" class="btn btn-info btn-sm"><fmt:message
                                    key="text.add.new.category"/></button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-7">
                <form role="form" method="post" action="controller?action=findbytitle">
                    <div class="form-group">
                        <label for="title" class="h5"><fmt:message key="text.find.mail.by.title"/></label>
                        <input type="text" class="form-control" name="title" id="title"
                               placeholder="<fmt:message key="text.input.title.for.search"/>" required>
                    </div>
                    <button type="submit" class="btn btn-success btn-sm"><fmt:message key="text.find"/></button>
                </form>

                <hr/>
                <form role="form" method="post" action="controller?action=findbytag">
                    <div class="form-group">
                        <label for="tag" class="h5"><fmt:message key="text.find.mail.by.tag"/></label>
                        <input type="text" class="form-control" name="tag" id="tag"
                               placeholder="<fmt:message key="text.input.tag.for.search"/>" required>
                    </div>
                    <button type="submit" class="btn btn-success btn-sm"><fmt:message key="text.find"/></button>
                </form>

                <hr/>
                <form role="form" method="post" action="controller?action=findbysenderorrecipient">
                    <div class="form-group">
                        <label for="email" class="h5"><fmt:message key="text.find.mail.by.recipient"/></label>
                        <input type="email" class="form-control" name="email" id="email"
                               placeholder="<fmt:message key="text.input.email.for.search"/>" required>
                    </div>
                    <button type="submit" class="btn btn-success btn-sm"><fmt:message key="text.find"/></button>
                </form>

                <hr/>
                <div class="h5"><fmt:message key="text.find.mail.by.time"/></div>
                <form role="form" method="post" action="controller?action=findbytimeperiod">
                    <div class="form-inline">
                        <label for="start" class="h7"><fmt:message key="text.input.start"/> </label>
                        <input type="datetime-local" class="form-control" name="start" id="start">
                        <label for="finish" class="h7"><fmt:message key="text.input.finish"/> </label>
                        <input type="datetime-local" class="form-control" name="finish" id="finish">
                    </div>
                    <button type="submit" class="btn btn-success btn-sm"><fmt:message key="text.find"/></button>
                </form>

                <hr/>
                <div class="h3"><fmt:message key="text.your.mail"/></div>
                <hr/>
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
                        <form role="form" class="btn btn-success btn-sm" method="post"
                              action="controller?action=changecategory">
                            <input type="hidden" name="id" value="${mail.id}">
                            <select class="form-control" name="categoryForChange">
                                <c:forEach items="${requestScope.custom_categories}" var="custom_category">
                                    <option value="${custom_category.categoryName}">${custom_category.categoryName}</option>
                                </c:forEach>
                            </select>
                            <button type="submit" class="btn btn-success btn-sm"><fmt:message
                                    key="text.change.category"/></button>
                        </form>
                    </div>
                </c:forEach>
            </div>
            <div class="col-3">
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="row">
            <div class="col-md text-center">
                <div class="menu-bar">
                    <br/>
                    <br/>
                    <a class="btn btn-info" href="controller?action=registrredir" role="button"><fmt:message key="text.registration"/></a>
                    <a class="btn btn-info" href="controller?action=loginredir" role="button"><fmt:message key="text.log.in"/></a></div>
            </div>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>
