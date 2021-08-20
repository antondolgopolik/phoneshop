function addToCart(id, quantity, onSuccess, onError) {
    const url = "http://localhost:8080/phoneshop-web/ajaxCart/cartItems/" + id;
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