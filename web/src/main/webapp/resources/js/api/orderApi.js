function createOrder(firstName, lastName, address, phoneNumber, additionalInfo, onSuccess, onError) {
    const url = "http://localhost:8080/phoneshop-web/api/orders/create";
    $.ajax(url, {
        type: "POST",
        dataType: "json",
        data: {
            "firstName": firstName,
            "lastName": lastName,
            "address": address,
            "phoneNumber": phoneNumber,
            "additionalInfo": additionalInfo
        },
        success: onSuccess,
        error: onError
    });
}

function orderDelivered(orderId, onSuccess, onError) {
    const url = "http://localhost:8080/phoneshop-web/api/orders/" + orderId + "/delivered";
    $.ajax(url, {
        type: "PUT",
        dataType: "json",
        success: onSuccess,
        error: onError
    });
}

function orderRejected(orderId, onSuccess, onError) {
    const url = "http://localhost:8080/phoneshop-web/api/orders/" + orderId + "/rejected";
    $.ajax(url, {
        type: "PUT",
        dataType: "json",
        success: onSuccess,
        error: onError
    });
}
