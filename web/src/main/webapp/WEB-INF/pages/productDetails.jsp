<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%--@elvariable id="phone" type="com.es.core.model.phone.Phone"--%>
<%--@elvariable id="cart" type="com.es.core.model.cart.Cart"--%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product details</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
</head>
<body>
<tags:topBar cart="${cart}"/>

<h1 class="display-4">
    ${phone.model}
</h1>
<img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}"
     alt="phone image">
<h1 class="display-6">
    ${phone.description}
</h1>

<h1 class="display-4">Display</h1>
<table class="table table-bordered">
    <tr>
        <td>Size</td>
        <td>${phone.displaySizeInches}</td>
    </tr>
    <tr>
        <td>Resolution</td>
        <td>${phone.displayResolution}</td>
    </tr>
    <tr>
        <td>Technology</td>
        <td>${phone.displayTechnology}</td>
    </tr>
    <tr>
        <td>Pixel density</td>
        <td>${phone.pixelDensity}</td>
    </tr>
</table>

<h1 class="display-4">Dimensions & weight</h1>
<table class="table table-bordered">
    <tr>
        <td>Length</td>
        <td>${phone.lengthMm}mm</td>
    </tr>
    <tr>
        <td>Width</td>
        <td>${phone.widthMm}mm</td>
    </tr>
    <tr>
        <td>Weight</td>
        <td>${phone.weightGr}</td>
    </tr>
</table>

<h1 class="display-4">Camera</h1>
<table class="table table-bordered">
    <tr>
        <td>Front</td>
        <td>${phone.frontCameraMegapixels} megapixels</td>
    </tr>
    <tr>
        <td>Back</td>
        <td>${phone.backCameraMegapixels} megapixels</td>
    </tr>
</table>

<h1 class="display-4">Battery</h1>
<table class="table table-bordered">
    <tr>
        <td>Talk time</td>
        <td>${phone.talkTimeHours} hours</td>
    </tr>
    <tr>
        <td>Stand by time</td>
        <td>${phone.standByTimeHours} hours</td>
    </tr>
    <tr>
        <td>Battery capacity</td>
        <td>${phone.batteryCapacityMah} mAh</td>
    </tr>
</table>

<h1 class="display-4">Other</h1>
<table class="table table-bordered">
    <tr>
        <td>Colors</td>
        <td>
            <c:forEach var="color" items="${phone.colors}" varStatus="colorStatus">
                ${color}<c:if test="${not colorStatus.last}">, </c:if>
            </c:forEach>
        </td>
    </tr>
    <tr>
        <td>Device type</td>
        <td>${phone.deviceType}</td>
    </tr>
    <tr>
        <td>Bluetooth</td>
        <td>${phone.bluetooth}</td>
    </tr>
</table>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
