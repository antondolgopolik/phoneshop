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
    <script type="text/javascript" src="<c:url value="/resources/js/quickOrderPage.js"/>"></script>
</head>
<body>
<tags:navBar cart="${cart}" authenticated="${authenticated}"/>

<table id="product-table" class="table table-bordered">
    <thead>
    <tr>
        <td>Model</td>
        <td>Quantity</td>
    </tr>
    </thead>
    <% for (int i = 0; i < 10; i++) { %>
    <tr>
        <td>
            <label>
                <input id="model-<%=i%>" type="text" class="form-control"
                       placeholder="Model">
            </label>
            <div id="model-feedback-<%=i%>" class="invalid-feedback"></div>
        </td>
        <td>
            <label>
                <input id="quantity-<%=i%>" type="text" class="form-control"
                       placeholder="Quantity">
            </label>
            <div id="quantity-feedback-<%=i%>" class="invalid-feedback"></div>
        </td>
    </tr>
    <% }%>
</table>

<div class="container-fluid">
    <div class="row row-cols-auto justify-content-end gx-4">
        <button id="add-to-cart-button" class="btn btn-primary mx-2" onclick="addAllToCartClickHandler()">
            Add to cart
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
