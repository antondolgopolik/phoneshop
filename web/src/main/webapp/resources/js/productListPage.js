function addToCartClickHandler(id) {
    const quantity = $("#quantity-" + id).val();
    addToCart(id, quantity, addToCartSuccess, addToCartError);
}

function addToCartSuccess(data) {
    updateMiniCart(data.totalQuantity, data.totalCost);
    alert("success");
}

function addToCartError(jqHXR, status) {
    alert(jqHXR.responseText);
}