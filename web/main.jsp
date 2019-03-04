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

        <p>Hello ${sessionScope.user.firstName} ${sessionScope.user.lastName}</p>

        <a href="controller?action=send">Send email</a>
        <p><a href="controller?action=logout">Logout</a></p>

        <hr/>
                <jstl:forEach items="${requestScope.categories}" var="category">
                        <form method="post" action="controller?action=getmailbycategory">
                            <input type="hidden" name="categoryName" value="${category.categoryName}"/>
                            <input type="submit" value="${category.categoryName}"/>
                        </form>
                </jstl:forEach>

                <jstl:forEach items="${requestScope.custom_categories}" var="custom_category">
                        <form method="post" action="controller?action=getmailbycategory">
                            <input type="hidden" name="categoryName" value="${custom_category.categoryName}"/>
                            <input type="submit" value="${custom_category.categoryName}"/>
                        </form>
                </jstl:forEach>

            <form method="post" action="controller?action=addcategory">
                <label>
                    <input type="text" name="newcategory" size="20"/>
                </label>category name
                <input type="submit" value="Add new category"/>
            </form>


        <hr/>
        <div class="h5">find mail by title</div>
        <form method="post" action="controller?action=findbytitle">
            <p>
                <input type="text" name="title" size="30"/>
                <input type="submit" value="find"/></p>
        </form>

        <hr/>
        <div class="h5">find mail by tag</div>
        <form method="post" action="controller?action=findbytag">
            <p>
                <input type="text" name="tag" size="30"/>
                <input type="submit" value="find"/></p>
        </form>
        <hr/>
        <div class="h5">find mail by sender or recipient</div>
        <form method="post" action="controller?action=findbysenderorrecipient">
            <p>
                <input type="text" name="email" size="30"/>
                <input type="submit" value="find"/></p>
        </form>
        <div class="h5"> find mail by time period</div>
        <form method="post" action="controller?action=findbytimeperiod">
            <p>
                <input type="datetime-local" name="start" size="30"/>
                <input type="datetime-local" name="finish" size="30"/>
                <input type="submit" value="find"/></p>
        </form>
        <hr/>


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

            <form method="post" action="controller?action=delete">
                <input type="hidden" name="id" value="${mail.id}">
                <input type="hidden" name="sender" value="${mail.sender}">
                <input type="hidden" name="recipient" value="${mail.recipient}"/>
                <input type="hidden" name="dateTime" value="${mail.dateTime}"/>
                <input type="hidden" name="title" value="${mail.title}"/>
                <input type="hidden" name="tags" value="${mail.tags}"/>
                <input type="hidden" name="category" value="${mail.category}"/>
                <input type="hidden" name="message" value="${mail.message}"/>
                <input type="hidden" name="related_user" value="${mail.relatedUser}"/>

                <input type="submit" value="Delete message"/>
            </form>

            <form method="post" action="controller?action=changecategory">
                <input type="hidden" name="id" value="${mail.id}">
                <input type="hidden" name="sender" value="${mail.sender}">
                <input type="hidden" name="recipient" value="${mail.recipient}"/>
                <input type="hidden" name="dateTime" value="${mail.dateTime}"/>
                <input type="hidden" name="title" value="${mail.title}"/>
                <input type="hidden" name="tags" value="${mail.tags}"/>
                <input type="hidden" name="category" value="${mail.category}"/>
                <input type="hidden" name="message" value="${mail.message}"/>
                <input type="hidden" name="related_user" value="${mail.relatedUser}"/>

                <p>
                    <label>
                        <select size="1" name="categoryForChange">
                            <jstl:forEach items="${requestScope.custom_categories}" var="custom_category">
                                <option value="${custom_category.categoryName}">${custom_category.categoryName}</option>
                            </jstl:forEach>
                        </select>
                    </label><input type="submit" value="Change category"/>
                </p>

            </form>

            <form method="post" action="controller?action=reportedspam">
                <input type="hidden" name="id" value="${mail.id}">
                <input type="hidden" name="sender" value="${mail.sender}">
                <input type="hidden" name="recipient" value="${mail.recipient}"/>
                <input type="hidden" name="dateTime" value="${mail.dateTime}"/>
                <input type="hidden" name="title" value="${mail.title}"/>
                <input type="hidden" name="tags" value="${mail.tags}"/>
                <input type="hidden" name="category" value="${mail.category}"/>
                <input type="hidden" name="message" value="${mail.message}"/>
                <input type="hidden" name="related_user" value="${mail.relatedUser}"/>
                <input type="hidden" name="spam" value="spam"/>
                <input type="submit" value="Reported spam"/>
            </form>
            <hr/>
        </jstl:forEach>

    </jstl:when>
    <jstl:otherwise>
            <li><a href="register.jsp">Register</a></li>
            <li><a href="login.jsp">Login</a></li>
    </jstl:otherwise>

</jstl:choose>

</body>
</html>

