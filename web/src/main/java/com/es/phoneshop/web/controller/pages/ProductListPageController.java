package com.es.phoneshop.web.controller.pages;

import com.es.core.services.cart.CartService;
import com.es.core.services.product.ProductService;
import com.es.core.services.product.SortDirection;
import com.es.core.services.product.SortType;
import com.es.core.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/productList")
public class ProductListPageController {
    private static final String CART_ATTR = "cart";
    private static final String AUTHENTICATED_ATR = "authenticated";
    private static final String PRODUCTS_ATTR = "products";
    private static final String CURRENT_PAGE_ATTR = "currentPage";
    private static final String PRODUCTS_NUMBER_ATTR = "productsNumber";
    private static final String PRODUCTS_PER_PAGE_ATTR = "productsPerPage";

    @Resource
    private CartService cartService;
    @Resource
    private UserService userService;
    @Resource
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String showProductListPage(Model model,
                                      @RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(required = false) String searchQuery,
                                      @RequestParam(required = false) String sort,
                                      @RequestParam(required = false) String sortDir) {
        SortType sortType = sort != null
                ? SortType.valueOf(sort.toUpperCase())
                : null;
        SortDirection sortDirection = sortDir != null
                ? SortDirection.valueOf(sortDir.toUpperCase())
                : null;
        // Set attributes
        model.addAttribute(CART_ATTR, cartService.getCart());
        model.addAttribute(AUTHENTICATED_ATR, userService.isAuthenticated());
        model.addAttribute(PRODUCTS_ATTR, productService.searchProducts(
                searchQuery, sortType, sortDirection, (page - 1) * 10, 10)
        );
        model.addAttribute(CURRENT_PAGE_ATTR, page);
        model.addAttribute(PRODUCTS_NUMBER_ATTR, productService.getProductsInStockNumber());
        model.addAttribute(PRODUCTS_PER_PAGE_ATTR, 10);
        return "productListPage";
    }
}
