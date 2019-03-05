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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <title>SendMail</title>
</head>
<body>
<%--<jstl:if test="${not empty requestScope.invalidRecipient}">
    <div class="alert alert-danger" role="alert">Incorrect recipient data. Try again</div>
</jstl:if>
--%>
<jstl:if test="${not empty requestScope.invalidTitle}">
    <div class="alert alert-danger" role="alert">Incorrect title. Try again</div>
</jstl:if>
<jstl:if test="${not empty requestScope.invalidTags}">
    <div class="alert alert-danger" role="alert">Incorrect tag. Try again</div>
</jstl:if>
<jstl:if test="${not empty requestScope.invalidMessage}">
    <div class="alert alert-danger" role="alert">Incorrect message. Try again</div>
</jstl:if>
<jstl:if test="${not empty requestScope.problem}">
    <div class="alert alert-danger" role="alert">Message hasn't been send. Try later</div>
</jstl:if>

<form role="form" method="post" action="controller?action=send">
    <div class="form-group">
        <label for="recipient">Recipient</label>
        <input type="email" class="form-control" name="recipient" id="recipient" placeholder="*Recipient email">
    </div>
    <div class="form-group">
        <label for="title">Title</label>
        <input type="text" class="form-control" name="title" id="title" placeholder="*Title">
    </div>
    <div class="form-group">
        <label for="tags">Tags</label>
        <input type="text" class="form-control" name="tags" id="tags" placeholder="Tags">
        <p class="help-block">Input tags with ","</p>
    </div>
    <div class="form-group">
        <label for="message">Message</label>
        <input type="text" class="form-control" name="message" id="message" placeholder="Your message">
    </div>
    <button type="submit" class="btn btn-success">Send</button>
</form>

</body>
</html>
