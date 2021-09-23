<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%--@elvariable id="cart" type="com.es.core.model.cart.Cart"--%>
<%--@elvariable id="cartItems" type="java.util.Collection"--%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/navBar.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/cartPage.js"/>"></script>
</head>
<body>
<tags:navBar cart="${cart}"/>

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
                <c:forEach var="color" items="${cartItem.phone.colors}" varStatus="colorStatus">
                    ${color}<c:if test="${not colorStatus.last}">, </c:if>
                </c:forEach>
            </td>
            <td>
                    ${cartItem.phone.displaySizeInches}``
            </td>
            <td>
                <c:if test="${empty cartItem.phone.price}">
                    Unknown
                </c:if>
                <c:if test="${not empty cartItem.phone.price}">
                    ${cartItem.phone.price}$
                </c:if>
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
        <button id="order-button" class="btn btn-primary mx-2" onclick="orderCartClickHandler()">
            Order
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
