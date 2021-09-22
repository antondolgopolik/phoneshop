function deleteFromCartClickHandler(id) {
    deleteFromCart(id,
        function (data) {
            $("#cart-button").html("My cart: " + data.totalQuantity + " items " + data.totalCost + "$");
            $("#tr-" + id).remove();
            alert("success");
        },
        function (jqHXR, status) {
            alert(jqHXR.responseText);
        });
}

function updateCartClickHandler() {
    // Prepare updates
    const updates = new Map();
    const table = document.getElementById("cart-table");
    for (let i = 1; i < table.rows.length; i++) {
        const phoneId = table.rows[i].dataset.id;
        const quantityField = $("#quantity-" + phoneId);
        updates[phoneId] = quantityField.val();
        // Reset errors display
        quantityField.removeClass("is-invalid");
        $("#quantity-feedback-" + phoneId).hide();
    }
    // Send request
    updateCart(updates, updateCartSuccess, updateCartError);
}

function updateCartSuccess(data) {
    updateMiniCart(data.totalQuantity, data.totalCost);
    alert("success");
}

function updateCartError(jqHXR, status) {
    const errorsObj = JSON.parse(jqHXR.responseText);
    const errors = new Map(Object.entries(errorsObj));
    for (let [phoneId, msg] of errors) {
        $("#quantity-" + phoneId).addClass("is-invalid");
        const feedbackDiv = $("#quantity-feedback-" + phoneId);
        feedbackDiv.html(msg);
        feedbackDiv.show();
    }
}