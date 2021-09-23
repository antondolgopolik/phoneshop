function createOrder(firstName, lastName, address, phoneNumber, additionalInfo, onSuccess, onError) {
    const url = "http://localhost:8080/phoneshop-web/api/orders/create";
    const dataObj = {
        "firstName": firstName,
        "lastName": lastName,
        "address": address,
        "phoneNumber": phoneNumber,
        "additionalInfo": additionalInfo
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
