<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%--@elvariable id="cart" type="com.es.core.model.cart.Cart"--%>
<%--@elvariable id="authenticated" type="java.lang.Boolean"--%>
<%--@elvariable id="products" type="java.util.List"--%>
<%--@elvariable id="currentPage" type="java.lang.Integer"--%>
<%--@elvariable id="productsNumber" type="java.lang.Integer"--%>

<%
    final int currentPage = (int) request.getAttribute("currentPage");
    final int productsNumber = (int) request.getAttribute("productsNumber");
    final int productsPerPage = (int) request.getAttribute("productsPerPage");
    final int lastPageNumber = (int) Math.ceil((double) productsNumber / productsPerPage);
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product list</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/api/cartApi.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/navBar.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/productListPage.js"/>"></script>
</head>
<body>
<tags:navBar cart="${cart}" authenticated="${authenticated}"/>

<table class="table table-bordered">
    <thead>
    <tr>
        <td>Image</td>
        <td>
            Brand
            <button class="btn btn-light" onclick="sortByBrandAsc()">
                ↑
            </button>
            <button class="btn btn-light" onclick="sortByBrandDesc()">
                ↓
            </button>
        </td>
        <td>
            Model
            <button class="btn btn-light" onclick="sortByModelAsc()">
                ↑
            </button>
            <button class="btn btn-light" onclick="sortByModelDesc()">
                ↓
            </button>
        </td>
        <td>Color</td>
        <td>Display size</td>
        <td>
            Price
            <button class="btn btn-light" onclick="sortByPriceAsc()">
                ↑
            </button>
            <button class="btn btn-light" onclick="sortByPriceDesc()">
                ↓
            </button>
        </td>
        <td>Quantity</td>
        <td>Action</td>
    </tr>
    </thead>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <a href="/phoneshop-web/productDetails/${product.id}">
                    <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}"
                         alt="phone image">
                </a>
            </td>
            <td>${product.brand}</td>
            <td>${product.model}</td>
            <td>
                <tags:colors colors="${product.colors}"/>
            </td>
            <td>
                    ${product.displaySize}``
            </td>
            <td>
                <tags:price price="${product.price}"/>
            </td>
            <td>
                <label>
                    <input id="quantity-${product.id}" type="text" class="form-control" placeholder="Quantity">
                </label>
            </td>
            <td>
                <button class="btn btn-primary" onclick="addToCartClickHandler(${product.id})">
                    Add to cart
                </button>
            </td>
        </tr>
    </c:forEach>
</table>

<div class="container">
    <nav>
        <ul class="pagination justify-content-center">
            <li class="page-item <%if (currentPage == 1) out.print("disabled");%>">
                <a class="page-link" href="?page=<%out.print(currentPage - 1);%>">Previous</a>
            </li>
            <%
                int limit = Math.min(currentPage + productsPerPage - 1, lastPageNumber);
                // Display page buttons
                out.print(String.format("<li class=\"page-item active\"><a class=\"page-link\" href=\"?page=%d\">%d</a></li>", currentPage, currentPage));
                for (int i = currentPage + 1; i <= limit; i++) {
                    out.print(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"?page=%d\">%d</a></li>", i, i));
                }
            %>
            <li class="page-item <%if (currentPage == lastPageNumber) out.print("disabled");%>">
                <a class="page-link" href="?page=<%out.print(currentPage + 1);%>">Next</a>
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
