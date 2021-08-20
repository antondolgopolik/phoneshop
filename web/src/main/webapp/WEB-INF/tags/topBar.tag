<%@ attribute name="cart" type="com.es.core.model.cart.Cart" required="true" %>

<%--@elvariable id="cart" type="com.es.core.model.cart.Cart"--%>

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
                <a href="${pageContext.request.contextPath}/cart" id="cart-button" class="btn btn-primary">
                    My cart: ${cart.totalQuantity} items ${cart.totalCost}$
                </a>
            </div>
        </div>
    </div>
</div>