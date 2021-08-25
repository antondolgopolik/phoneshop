<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--@elvariable id="phones" type="java.util.List"--%>
<%--@elvariable id="currentPage" type="java.lang.Integer"--%>
<%--@elvariable id="phonesInStockNumber" type="java.lang.Integer"--%>

<%
    final int currentPage = (Integer) request.getAttribute("currentPage");
    final int phonesInStockNumber = (Integer) request.getAttribute("phonesInStockNumber");
    final int phonesPerPage = (Integer) request.getAttribute("phonesPerPage");
    final int maxPage = (int) Math.ceil((double) phonesInStockNumber / phonesPerPage);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product list</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/productList.js"/>"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row justify-content-between">
        <div class="col-auto d-flex align-items-center">
            <h1 class="display-3">
                Phoneshop
            </h1>
        </div>
        <div class="col-auto">
            <div>
                <a href="#">
                    Login
                </a>
            </div>
            <div>
                <button class="btn btn-primary">
                    My cart: 0 items 0$
                </button>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid py-4">
    <div class="row justify-content-end">
        <div class="col-auto">
            <label>
                <input id="search-text-field" type="text" class="form-control" placeholder="Search...">
            </label>
        </div>
    </div>
</div>

<table class="table table-bordered">
    <thead>
    <tr>
        <td>Image</td>
        <td>
            Brand
            <a href="?sortType=brand&sortDir=asc">↑</a>
            <a href="?sortType=brand&sortDir=desc">↓</a>
        </td>
        <td>
            Model
            <a href="?sortType=model&sortDir=asc">↑</a>
            <a href="?sortType=model&sortDir=desc">↓</a>
        </td>
        <td>Color</td>
        <td>Display size</td>
        <td>
            Price
            <a href="?sortType=price&sortDir=asc">↑</a>
            <a href="?sortType=price&sortDir=desc">↓</a>
        </td>
        <td>Quantity</td>
        <td>Action</td>
    </tr>
    </thead>
    <c:forEach var="phone" items="${phones}">
        <tr>
            <td>
                <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}"
                     alt="phone image">
            </td>
            <td>${phone.brand}</td>
            <td>${phone.model}</td>
            <td>
                <c:forEach var="color" items="${phone.colors}" varStatus="colorStatus">
                    ${color}<c:if test="${not colorStatus.last}">, </c:if>
                </c:forEach>
            </td>
            <td>
                    ${phone.displaySizeInches}``
            </td>
            <td>
                <c:if test="${empty phone.price}">
                    Unknown
                </c:if>
                <c:if test="${not empty phone.price}">
                    ${phone.price}$
                </c:if>
            </td>
            <td>
                <label>
                    <input id="quantity-${phone.id}" type="text" class="form-control" placeholder="Quantity">
                </label>
            </td>
            <td>
                <button class="btn btn-primary" onclick="addToCartClickHandler(${phone.id})">Add to</button>
            </td>
        </tr>
    </c:forEach>
</table>

<div class="container">
    <nav>
        <ul class="pagination justify-content-center">
            <li class="page-item <%if (currentPage == 1) out.print("disabled");%>">
                <a class="page-link" href="?page=<%out.print(currentPage-1);%>">Previous</a>
            </li>
            <%
                int limit = Math.min(currentPage + phonesPerPage - 1, maxPage);
                // Display page buttons
                out.print(String.format("<li class=\"page-item active\"><a class=\"page-link\" href=\"?page=%d\">%d</a></li>", currentPage, currentPage));
                for (int i = currentPage + 1; i <= limit; i++) {
                    out.print(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"?page=%d\">%d</a></li>", i, i));
                }
            %>
            <li class="page-item <%if (currentPage == maxPage) out.print("disabled");%>">
                <a class="page-link" href="?page=<%out.print(currentPage+1);%>">Next</a>
            </li>
        </ul>
    </nav>
</div>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
