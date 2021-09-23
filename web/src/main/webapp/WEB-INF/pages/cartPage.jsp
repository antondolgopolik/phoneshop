<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%--@elvariable id="cart" type="com.es.core.model.cart.Cart"--%>
<%--@elvariable id="authenticated" type="java.lang.Boolean"--%>
<%--@elvariable id="cartItems" type="java.util.Collection"--%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/api/cartApi.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/navBar.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/cartPage.js"/>"></script>
</head>
<body>
<tags:navBar cart="${cart}" authenticated="${authenticated}"/>

<table id="cart-table" class="table table-bordered">
    <thead>
    <tr>
        <td>Brand</td>
        <td>Model</td>
        <td>Color</td>
        <td>Display size</td>
        <td>Price</td>
        <td>Quantity</td>
        <td>Action</td>
    </tr>
    </thead>
    <c:forEach var="cartItem" items="${cartItems}">
        <tr id="tr-${cartItem.phone.id}" data-id="${cartItem.phone.id}">
            <td>${cartItem.phone.brand}</td>
            <td>${cartItem.phone.model}</td>
            <td>
                <tags:colors colors="${cartItem.phone.colors}"/>
            </td>
            <td>
                    ${cartItem.phone.displaySize}``
            </td>
            <td>
                <tags:price price="${cartItem.phone.price}"/>
            </td>
            <td>
                <label>
                    <input id="quantity-${cartItem.phone.id}" type="text" class="form-control"
                           value="${cartItem.quantity}" placeholder="Quantity">
                </label>
                <div id="quantity-feedback-${cartItem.phone.id}" class="invalid-feedback"></div>
            </td>
            <td>
                <button class="btn btn-primary" onclick="deleteFromCartClickHandler(${cartItem.phone.id})">
                    Delete
                </button>
            </td>
        </tr>
    </c:forEach>
</table>

<div class="container-fluid">
    <div class="row row-cols-auto justify-content-end gx-4">
        <button id="update-button" class="btn btn-primary mx-2" onclick="updateCartClickHandler()">
            Update
        </button>
        <a class="btn btn-primary mx-2" href="${pageContext.servletContext.contextPath}/order">
            Order
        </a>
    </div>
</div>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
