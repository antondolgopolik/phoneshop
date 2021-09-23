function backClickHandler() {
    window.location.replace(document.referrer);
}

function deliveredClickHandler() {
    const location = window.location.href;
    const orderId = location.substr(location.lastIndexOf("/") + 1);
    orderDelivered(orderId,
        function () {
            $("#order-status-div").html("Order status: Delivered");
        },
        function (jqHXR, status) {
            alert(jqHXR.responseText);
        })
}

function rejectedClickHandler() {
    const location = window.location.href;
    const orderId = location.substr(location.lastIndexOf("/") + 1);
    orderRejected(orderId,
        function () {
            $("#order-status-div").html("Order status: Rejected");
        },
        function (jqHXR, status) {
            alert(jqHXR.responseText);
        })
}