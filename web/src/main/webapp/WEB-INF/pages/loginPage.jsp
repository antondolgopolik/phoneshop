<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/api/usersApi.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/loginPage.js"/>"></script>
</head>
<body class="d-flex justify-content-center align-items-center h-100 w-100">

<div class="container w-25">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="row my-4">
            <label for="username">
                Username
            </label>
            <input id="username" name="username" class="form-control" type="text" placeholder="Username">
        </div>
        <div class="row my-4">
            <label for="password">
                Password
            </label>
            <input id="password" name="password" class="form-control" type="password" placeholder="Password">
        </div>
        <div class="d-flex justify-content-between">
            <a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/signup">
                Signup
            </a>
            <input class="btn btn-primary" type="submit" value="Log in">
        </div>
        <c:if test="${param.containsKey('error')}">
            Invalid username or password
        </c:if>
    </form>
</div>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
