function deleteFromCartClickHandler(id) {
    const quantity = $("#quantity-" + id).val();
    $.ajax("http://localhost:8080/phoneshop-web/ajaxCart", {
        type: "POST",
        dataType: "json",
        data: {
            "phoneId": id,
            "quantity": quantity
        },
        success: function (data) {
            $("#cart-button").html("My cart: " + data.totalQuantity + " items " + data.totalCost + "$");
            alert("success");
        },
        error: function (jqHXR, status) {
            alert(jqHXR.responseText);
        }
    });
}