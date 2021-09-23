function signUp(username, password, onSuccess, onError) {
    const url = "http://localhost:8080/phoneshop-web/api/users/signUp";
    const dataObj = {
        "username": username,
        "password": password
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