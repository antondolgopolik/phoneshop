<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%--@elvariable id="cart" type="com.es.core.model.cart.Cart"--%>
<%--@elvariable id="authenticated" type="java.lang.Boolean"--%>
<%--@elvariable id="orders" type="java.util.List"--%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product list</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/navBar.js"/>"></script>
</head>
<body>
<tags:navBar cart="${cart}" authenticated="${authenticated}"/>

<table class="table table-bordered">
    <thead>
    <tr>
        <td>Order ID</td>
        <td>Customer</td>
        <td>Phone number</td>
        <td>Address</td>
        <td>Date</td>
        <td>Total price</td>
        <td>Status</td>
    </tr>
    </thead>
    <c:forEach var="order" items="${orders}">
        <%--@elvariable id="order" type="com.es.core.model.order.Order"--%>
        <tr>
            <td>
                <a href="${pageContext.servletContext.contextPath}/admin/orderOverview/${order.id}">
                        ${order.id}
                </a>
            </td>
            <td>${order.firstName.concat(" ").concat(order.lastName)}</td>
            <td>${order.phoneNumber}</td>
            <td>${order.address}</td>
            <td>${order.date}</td>
            <td>${order.totalPrice}$</td>
            <td>${order.status}</td>
        </tr>
    </c:forEach>
</table>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
