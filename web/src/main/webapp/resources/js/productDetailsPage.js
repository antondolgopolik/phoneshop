function addToCartClickHandler(id) {
    const quantity = $("#quantity").val();
    // Send request
    addToCart(id, quantity, addToCartSuccess, addToCartError);
}

function addToCartSuccess(data) {
    updateMiniCart(data.totalQuantity, data.totalCost);
    alert("success");
}

function addToCartError(jqHXR, status) {
    const errorsObj = JSON.parse(jqHXR.responseText);
    const errors = new Map(Object.entries(errorsObj));
    alert(errors.get("quantity"));
}