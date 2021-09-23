function orderClickHandler() {
    // Reset errors display
    resetError("first-name");
    resetError("last-name");
    resetError("address");
    resetError("phone-number");
    // Read values
    const firstName = readField("first-name");
    const lastName = readField("last-name");
    const address = readField("address");
    const phoneNumber = readField("phone-number");
    const additionalInfo = readField("additional-info");
    // Send request
    createOrder(firstName, lastName, address, phoneNumber, additionalInfo,
        function (data) {
            window.location.replace(window.location.origin + "/phoneshop-web/orderOverview/" + data.orderId);
        },
        function (jqHXR, status) {
            const errorsObj = JSON.parse(jqHXR.responseText);
            const errors = new Map(Object.entries(errorsObj));
            setError("first-name", errors.get("firstName"));
            setError("last-name", errors.get("lastName"));
            setError("address", errors.get("address"));
            setError("phone-number", errors.get("phoneNumber"));
        });
}

function resetError(fieldName) {
    $("#" + fieldName).removeClass("is-invalid");
    $("#" + fieldName + "-feedback").hide();
}

function readField(fieldName) {
    return $("#" + fieldName).val();
}

function setError(fieldName, msg) {
    if (msg !== undefined) {
        $("#" + fieldName).addClass("is-invalid")
        const feedbackDiv = $("#" + fieldName + "-feedback");
        feedbackDiv.html(msg);
        feedbackDiv.show();
    }
}
