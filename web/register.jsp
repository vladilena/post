<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 28.02.2019
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
<jstl:if test="${not empty requestScope.notAdd}">
    <div class="alert alert-danger" role="alert">This user is exists</div>
</jstl:if>



<form role="form" method="post" action="controller?action=register">
    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" name ="email" id="email" placeholder="Your email">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" name="password" id="password" placeholder="Password">
        <p class="help-block">Password must have 6-10 letters, you can use letters, numbers and "_"</p>
    </div>
    <div class="form-group">
        <label for="firstName">First name</label>
        <input type="text" class="form-control" name="firstName" id="firstName" placeholder="First name">
    </div>
    <div class="form-group">
        <label for="lastName">Last name</label>
        <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Last name">
    </div>
    <button type="submit" class="btn btn-success">Registration</button>
</form>

<%--
<form method="post" action="controller?action=register">
    <p><label>
        <input type="text" name="email" size="40"/>
    </label>email*</p>
    <p><label>
        <input type="password" name="password" size="20" />
    </label>password*</p>
    <p><label>
        <input type="text" name="first_name" size="30" />
    </label>first name</p>
    <p><label>
        <input type="text" name="last_name" size="30" />
    </label>last name</p>

    <p><input type="submit" value="Register" /></p>
    <p></p>
</form>
--%>

</body>
</html>

