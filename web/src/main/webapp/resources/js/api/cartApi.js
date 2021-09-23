function addToCart(id, quantity, onSuccess, onError) {
    const url = "http://localhost:8080/phoneshop-web/api/cart/cartItems/" + id;
    const dataObj = {
        "quantity": quantity
    };
    $.ajax(url, {
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(dataObj),
        success: onSuccess,
        error: onError
    });
}

function updateCart(updates, onSuccess, onError) {
    const url = "http://localhost:8080/phoneshop-web/api/cart/cartItems";
    const dataObj = {
        "updates": updates
    }
    $.ajax(url, {
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(dataObj),
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