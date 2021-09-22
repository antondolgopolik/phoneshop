function signUp(username, password, onSuccess, onError) {
    const url = "http://localhost:8080/phoneshop-web/api/users/signUp";
    $.ajax(url, {
        type: "POST",
        dataType: "json",
        data: {
            "username": username,
            "password": password
        },
        success: onSuccess,
        error: onError
    });
}