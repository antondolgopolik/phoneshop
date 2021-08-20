<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%--@elvariable id="productDetails" type="com.es.core.dto.product.ProductDetailsDto"--%>
<%--@elvariable id="cart" type="com.es.core.model.cart.Cart"--%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product details</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/productDetails.css"/>">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/api/cartApi.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/navBar.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/productDetailsPage.js"/>"></script>
</head>
<body>
<tags:navBar cart="${cart}"/>

<div class="container-fluid p-4">
    <div class="row py-2">
        <h1 class="display-5">
            ${productDetails.phone.model}
        </h1>
        <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${productDetails.phone.imageUrl}"
             alt="phone image" class="phone-img">
        <p class="fs-4">
            ${productDetails.phone.description}
        </p>
    </div>

    <div class="row py-2">
        <table class="table table-bordered">
            <tr>
                <td>
                    Price
                </td>
                <td>
                    <tags:price phone="${productDetails.phone}"/>
                </td>
                <td>
                    <label>
                        <input id="quantity" type="text" class="form-control" placeholder="Quantity">
                    </label>
                </td>
                <td>
                    <button class="btn btn-primary" onclick="addToCartClickHandler(${productDetails.phone.id})">
                        Add to cart
                    </button>
                </td>
            </tr>
        </table>
    </div>

    <div class="row py-2">
        <h1 class="display-5">Display</h1>
        <table class="table table-bordered">
            <tr>
                <td>Size</td>
                <td>${productDetails.phone.displaySizeInches}</td>
            </tr>
            <tr>
                <td>Resolution</td>
                <td>${productDetails.phone.displayResolution}</td>
            </tr>
            <tr>
                <td>Technology</td>
                <td>${productDetails.phone.displayTechnology}</td>
            </tr>
            <tr>
                <td>Pixel density</td>
                <td>${productDetails.phone.pixelDensity}</td>
            </tr>
        </table>
    </div>

    <div class="row py-2">
        <h1 class="display-5">Dimensions & weight</h1>
        <table class="table table-bordered">
            <tr>
                <td>Length</td>
                <td>${productDetails.phone.lengthMm}mm</td>
            </tr>
            <tr>
                <td>Width</td>
                <td>${productDetails.phone.widthMm}mm</td>
            </tr>
            <tr>
                <td>Weight</td>
                <td>${productDetails.phone.weightGr}</td>
            </tr>
        </table>
    </div>

    <div class="row py-2">
        <h1 class="display-5">Camera</h1>
        <table class="table table-bordered">
            <tr>
                <td>Front</td>
                <td>${productDetails.phone.frontCameraMegapixels} megapixels</td>
            </tr>
            <tr>
                <td>Back</td>
                <td>${productDetails.phone.backCameraMegapixels} megapixels</td>
            </tr>
        </table>
    </div>

    <div class="row py-2">
        <h1 class="display-5">Battery</h1>
        <table class="table table-bordered">
            <tr>
                <td>Talk time</td>
                <td>${productDetails.phone.talkTimeHours} hours</td>
            </tr>
            <tr>
                <td>Stand by time</td>
                <td>${productDetails.phone.standByTimeHours} hours</td>
            </tr>
            <tr>
                <td>Battery capacity</td>
                <td>${productDetails.phone.batteryCapacityMah} mAh</td>
            </tr>
        </table>
    </div>

    <div class="row py-2">
        <h1 class="display-5">Other</h1>
        <table class="table table-bordered">
            <tr>
                <td>Colors</td>
                <td>
                    <c:forEach var="color" items="${productDetails.phone.colors}" varStatus="colorStatus">
                        ${color}<c:if test="${not colorStatus.last}">, </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>Device type</td>
                <td>${productDetails.phone.deviceType}</td>
            </tr>
            <tr>
                <td>Bluetooth</td>
                <td>${productDetails.phone.bluetooth}</td>
            </tr>
        </table>
    </div>
</div>


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
