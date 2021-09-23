<%@ attribute name="cart" type="com.es.core.model.cart.Cart" required="true" %>

<%--@elvariable id="cart" type="com.es.core.model.cart.Cart"--%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <div>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-toggler"
                    aria-controls="navbar-toggler" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.servletContext.contextPath}">
                <h1 class="display-6">Phoneshop</h1>
            </a>
        </div>
        <div class="navbar-collapse collapse" id="navbar-toggler">
            <ul class="navbar-nav ms-auto">
                <li>
                    <input id="search-text-field" class="form-control" type="search" placeholder="Search"
                           aria-label="Search">
                </li>
                <li class="nav-item">
                    <a id="cart-button" class="nav-link" aria-current="page"
                       href="${pageContext.request.contextPath}/cart">
                        My cart: ${cart.totalQuantity} items ${cart.totalCost}$
                    </a>
                </li>
                <li class="nav-item">
                    <a id="login-button" class="nav-link" aria-current="page" href="#">
                        Login
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>