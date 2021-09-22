<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Signup</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/api/usersApi.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/signupPage.js"/>"></script>
</head>
<body class="d-flex justify-content-center align-items-center h-100 w-100">

<div class="container w-25">
    <div class="row my-4">
        <label for="username">
            Username
        </label>
        <input id="username" class="form-control" type="text">
    </div>
    <div class="row my-4">
        <label for="password">
            Enter password
        </label>
        <input id="password" class="form-control" type="password">
    </div>
    <div class="row my-4">
        <label for="repeated-password">
            Repeat password
        </label>
        <input id="repeated-password" class="form-control" type="password">
    </div>
    <div class="d-flex justify-content-between">
        <a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/login">
            Login
        </a>
        <button class="btn btn-primary" onclick="signUpClickHandler()">
            Sign up
        </button>
    </div>
</div>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
