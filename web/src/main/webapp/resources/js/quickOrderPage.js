function addAllToCartClickHandler() {
    // Prepare additions
    const additions = new Map();
    const table = document.getElementById("product-table");
    for (let i = 1; i < table.rows.length; i++) {
        const modelField = $("#model-" + (i - 1));
        const quantityField = $("#quantity-" + (i - 1));
        if (modelField.val()) {
            additions[i - 1] = {
                "model": modelField.val(),
                "quantity": quantityField.val()
            };
        }
        // Reset errors display
        modelField.removeClass("is-invalid");
        quantityField.removeClass("is-invalid");
        $("#moel-feedback-" + i - 1).hide();
        $("#quantity-feedback-" + i - 1).hide();
    }
    // Send request
    addAllToCart(additions, addAllToCartSuccess, addAllToCartError);
}

function addAllToCartSuccess(data) {
    updateMiniCart(data.totalQuantity, data.totalCost);
    alert("success");
}

function addAllToCartError(jqHXR, status) {
    alert("Some error occurred, check the form");
}