function signUpClickHandler() {
    const password = $("#password").val();
    const repeatedPassword = $("#repeated-password").val();
    // Check passwords
    if (password === repeatedPassword) {
        const username = $("#username").val();
        // Send request
        signUp(username, password, signUpSuccess, signUpError);
    } else {
        alert("Password mismatch!")
    }
}

function signUpSuccess(data) {
    window.location.replace(window.location.origin + "/phoneshop-web/login");
}

function signUpError(jqHXR, status) {
    const errorsObj = JSON.parse(jqHXR.responseText);
    const errors = new Map(Object.entries(errorsObj));
    if (errors.has("username")) {
        alert(errors.get("username"));
    }
    if (errors.has("password")) {
        alert(errors.get("password"));
    }
}