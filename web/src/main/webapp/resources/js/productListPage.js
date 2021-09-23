function sortByBrandAsc() {
    sort("brand", "asc");
}

function sortByBrandDesc() {
    sort("brand", "desc");
}

function sortByModelAsc() {
    sort("model", "asc");
}

function sortByModelDesc() {
    sort("model", "desc");
}

function sortByPriceAsc() {
    sort("price", "asc");
}

function sortByPriceDesc() {
    sort("price", "desc");
}

function sort(sort, sortDir) {
    const url = new URL(window.location.href);
    const search_params = url.searchParams;
    // Update request param
    search_params.set("sort", sort);
    search_params.set("sortDir", sortDir)
    url.search = search_params.toString();
    // Reload page
    window.location.replace(url.toString());
}

function addToCartClickHandler(id) {
    const quantity = $("#quantity-" + id).val();
    // Send request
    addToCart(id, quantity, addToCartSuccess, addToCartError);
}

function addToCartSuccess(data) {
    updateMiniCart(data.totalQuantity, data.totalCost);
}

function addToCartError(jqHXR, status) {
    const errorsObj = JSON.parse(jqHXR.responseText);
    const errors = new Map(Object.entries(errorsObj));
    alert(errors.get("quantity"));
}