<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 28.02.2019
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <title>Title</title>
</head>
<body>

<jstl:choose>
<jstl:when test="${not empty sessionScope.user}">

<p class="text-center">Hello ${sessionScope.user.firstName} ${sessionScope.user.lastName}</p>

<div class="menu-bar">
    <a class="btn btn-info" href="controller?action=send" role="button">Send email</a>
    <a class="btn btn-info" href="controller?action=logout" role="button">Logout</a></div>
<br/>
<br/>

<div class="menu-bar">
    <jstl:forEach items="${requestScope.categories}" var="category">
        <form role="form" class="btn btn-success btn-sm" method="post" action="controller?action=getmailbycategory">
            <button type="submit" class="btn btn-success btn-sm" name="categoryName"
                    value="${category.categoryName}">${category.categoryName}</button>
        </form>
    </jstl:forEach>
</div>
<div class="menu-bar">
    <jstl:forEach items="${requestScope.custom_categories}" var="custom_category">
    <form role="form" class="btn btn-success btn-sm" method="post" action="controller?action=getmailbycategory">
        <button type="submit" class="btn btn-success btn-sm" name="categoryName"
                value="${custom_category.categoryName}">${custom_category.categoryName}</button>
    </form>
    </jstl:forEach>


    <jstl:if test="${not empty requestScope.wrongCategory}">
    <div class="alert alert-danger" role="alert">Incorrect name for category or this category exists</div>
    </jstl:if>


    <form role="form" method="post" action="controller?action=addcategory">
        <div class="form-inline">
            <input type="text" class="form-control" name="newcategory" placeholder="Category name">

            <button type="submit" class="btn btn-info btn-sm">Add new category</button>
        </div>
    </form>

    <hr/>
    <form role="form" method="post" action="controller?action=findbytitle">
        <div class="form-group">
            <label for="title" class="h5">find mail by title</label>
            <input type="text" class="form-control" name="title" id="title" placeholder="Input title for search">
        </div>
        <button type="submit" class="btn btn-success btn-sm">Find</button>
    </form>

    <hr/>
    <form role="form" method="post" action="controller?action=findbytag">
        <div class="form-group">
            <label for="tag" class="h5">find mail by tag</label>
            <input type="text" class="form-control" name="tag" id="tag" placeholder="Input tag for search">
        </div>
        <button type="submit" class="btn btn-success btn-sm">Find</button>
    </form>

    <hr/>
    <form role="form" method="post" action="controller?action=findbysenderorrecipient">
        <div class="form-group">
            <label for="email" class="h5">find mail by sender or recipient</label>
            <input type="email" class="form-control" name="email" id="email" placeholder="Input email for search">
        </div>
        <button type="submit" class="btn btn-success btn-sm">Find</button>
    </form>

    <hr/>
    <div class="h5"> find mail by time period</div>
    <form role="form" method="post" action="controller?action=findbytimeperiod">
        <div class="form-inline">
            <label for="start" class="h7">Start</label>
            <input type="datetime-local" class="form-control" name="start" id="start">
            <label for="finish" class="h7">Finish</label>
            <input type="datetime-local" class="form-control" name="finish" id="finish">
        </div>
        <button type="submit" class="btn btn-success btn-sm">Find</button>
    </form>

    <hr/>
    <div class="h3">Your mails</div>
    <hr/>
    <jstl:forEach items="${requestScope.mails}" var="mail">
    <ul class="menu">
        <li><b>Sender:</b> ${mail.sender}</li>
        <li><b>Recipient:</b> ${mail.recipient}</li>
        <li><b>Date and time:</b> ${mail.dateTime}</li>
        <li><b>Title:</b> ${mail.title}</li>
        <li><b>Tags: </b>${mail.tags}</li>
        <li><b>Category:</b> ${mail.category}</li>
        <li><b>Message:</b> ${mail.message}</li>
    </ul>

    <div class="menu-bar">

        <form role="form" class="btn btn-success btn-sm" method="post" action="controller?action=delete">
            <input type="hidden" name="id" value="${mail.id}">
            <button type="submit" class="btn btn-success btn-sm">Delete message</button>
        </form>
        <form role="form" class="btn btn-success btn-sm" method="post" action="controller?action=reportedspam">
            <input type="hidden" name="id" value="${mail.id}">
            <button type="submit" class="btn btn-success btn-sm">Reported spam</button>
        </form>
        <form role="form" class="btn btn-success btn-sm" method="post" action="controller?action=changecategory">
            <input type="hidden" name="id" value="${mail.id}">
            <select class="form-control" name="categoryForChange">
                <jstl:forEach items="${requestScope.custom_categories}" var="custom_category">
                    <option value="${custom_category.categoryName}">${custom_category.categoryName}</option>
                </jstl:forEach>
            </select>
            <button type="submit" class="btn btn-success btn-sm">Change category</button>
        </form>
        </div>
    </jstl:forEach>

    </jstl:when>
    <jstl:otherwise>
    <div class="menu-bar">
        <a class="btn btn-info" href="register.jsp" role="button">Register</a>
        <a class="btn btn-info" href="login.jsp" role="button">Login</a></div>
    </jstl:otherwise>
    </jstl:choose>

</body>
</html>

