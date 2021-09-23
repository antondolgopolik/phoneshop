<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%--@elvariable id="cart" type="com.es.core.model.cart.Cart"--%>
<%--@elvariable id="authenticated" type="java.lang.Boolean"--%>
<%--@elvariable id="deliveryPrice" type="java.math.BigDecimal"--%>
<%--@elvariable id="cartItems" type="java.util.Collection"--%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/api/orderApi.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/navBar.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/orderPage.js"/>"></script>
</head>
<body>
<tags:navBar cart="${cart}" authenticated="${authenticated}"/>

<div class="container-fluid p-4">
    <div class="row py-2">
        <h1 class="display-6">Order items</h1>
        <table id="cart-table" class="table table-bordered">
            <thead>
            <tr>
                <td>Brand</td>
                <td>Model</td>
                <td>Color</td>
                <td>Display size</td>
                <td>Price</td>
                <td>Quantity</td>
            </tr>
            </thead>
            <c:forEach var="cartItem" items="${cartItems}">
                <tr>
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
                            ${cartItem.quantity}
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="3">
                    Total quantity
                </td>
                <td colspan="3">
                    ${cart.totalQuantity}
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    Subtotal
                </td>
                <td colspan="3">
                    ${cart.subtotal}$
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    Delivery price
                </td>
                <td colspan="3">
                    ${deliveryPrice}$
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    Total price
                </td>
                <td colspan="3">
                    ${cart.subtotal.add(deliveryPrice)}$
                </td>
            </tr>
        </table>
    </div>

    <div class="row py-2">
        <h1 class="display-6">Order form</h1>
        <div class="row py-1">
            <div class="col-3">
                First name*
            </div>
            <div class="col-3">
                <label class="w-100">
                    <input id="first-name" class="form-control" type="text" placeholder="First name">
                </label>
            </div>
            <div id="first-name-feedback" class="invalid-feedback"></div>
        </div>
        <div class="row py-1">
            <div class="col-3">
                Last name*
            </div>
            <div class="col-3">
                <label class="w-100">
                    <input id="last-name" class="form-control" type="text" placeholder="Last name">
                </label>
            </div>
            <div id="last-name-feedback" class="invalid-feedback"></div>
        </div>
        <div class="row py-1">
            <div class="col-3">
                Address*
            </div>
            <div class="col-3">
                <label class="w-100">
                    <input id="address" class="form-control" type="text" placeholder="Address">
                </label>
            </div>
            <div id="address-feedback" class="invalid-feedback"></div>
        </div>
        <div class="row py-1">
            <div class="col-3">
                Phone number*
            </div>
            <div class="col-3">
                <label class="w-100">
                    <input id="phone-number" class="form-control" type="text" placeholder="Phone number">
                </label>
            </div>
            <div id="phone-number-feedback" class="invalid-feedback"></div>
        </div>
        <div class="row py-1">
            <div class="col-6">
                <label class="w-100">
                    <textarea id="additional-info" class="form-control w-100"
                              placeholder="Additional information">
                    </textarea>
                </label>
            </div>
        </div>
        <div class="row py-1">
            <div class="col-auto">
                <button class="btn btn-primary" onclick="orderClickHandler()">
                    Order
                </button>
            </div>
        </div>
    </div>
</div>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
