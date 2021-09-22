function addToCart(id, quantity, onSuccess, onError) {
    const url = "http://localhost:8080/phoneshop-web/api/cart/cartItems/" + id;
    $.ajax(url, {
        type: "POST",
        dataType: "json",
        data: {
            "quantity": quantity
        },
        success: onSuccess,
        error: onError
    });
}

function updateCart(updates, onSuccess, onError) {
    const url = "http://localhost:8080/phoneshop-web/api/cart/cartItems";
    $.ajax(url, {
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(updates),
        success: onSuccess,
        error: onError
    });
}

function deleteFromCart(id, onSuccess, onError) {
    const url = "http://localhost:8080/phoneshop-web/api/cart/cartItems/" + id;
    $.ajax(url, {
        type: "DELETE",
        dataType: "json",
        success: onSuccess,
        error: onError
    });
}