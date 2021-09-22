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
    window.location.replace(window.location.origin + "/phoneshop-web");
}

function signUpError(jqHXR, status) {
    alert(jqHXR.responseText);
}