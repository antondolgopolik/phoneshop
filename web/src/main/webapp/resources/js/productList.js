$(window).on("load", function () {
    $("#search-text-field").keyup(function (event) {
        if (event.keyCode === 13) {
            const url = new URL(window.location.href);
            const search_params = url.searchParams;
            // Update request param
            search_params.set("request", $("#search-text-field").val());
            url.search = search_params.toString();
            // Reload page
            window.location.replace(url.toString());
        }
    });
})

function addToCartClickHandler(id) {
    const quantity = $("#quantity-" + id).val();
    $.ajax("http://localhost:8080/phoneshop-web/ajaxCart", {
        type: "POST",
        data: "phoneId=" + id + "&quantity=" + quantity
    });
}