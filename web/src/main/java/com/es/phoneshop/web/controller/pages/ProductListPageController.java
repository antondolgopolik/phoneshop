package com.es.phoneshop.web.controller.pages;

import com.es.core.dao.phone.PhoneDao;
import com.es.core.dao.phone.PhoneSortType;
import com.es.core.dao.phone.SortDirection;
import com.es.core.services.cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/productList")
public class ProductListPageController {
    private static final String PHONES_ATTR = "phones";
    private static final String CART_ATTR = "cart";
    private static final String CURRENT_PAGE_ATTR = "currentPage";
    private static final String PHONES_IN_STOCK_NUMBER_ATTR = "phonesInStockNumber";
    private static final String PHONES_PER_PAGE_ATTR = "phonesPerPage";

    @Resource
    private CartService cartService;
    @Resource
    private PhoneDao phoneDao;

    @RequestMapping(method = RequestMethod.GET)
    public String showProductList(Model model,
                                  @RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(required = false) String request,
                                  @RequestParam(required = false) String sortType,
                                  @RequestParam(required = false) String sortDir) {
        PhoneSortType phoneSortType = sortType != null
                ? PhoneSortType.valueOf(sortType.toUpperCase())
                : null;
        SortDirection sortDirection = sortDir != null
                ? SortDirection.valueOf(sortDir.toUpperCase())
                : null;
        model.addAttribute(PHONES_ATTR, phoneDao.findInStock(
                request, phoneSortType, sortDirection, (page - 1) * 10, 10)
        );
        model.addAttribute(CART_ATTR, cartService.getCart());
        model.addAttribute(CURRENT_PAGE_ATTR, page);
        model.addAttribute(PHONES_IN_STOCK_NUMBER_ATTR, phoneDao.getPhonesInStockNumber());
        model.addAttribute(PHONES_PER_PAGE_ATTR, 10);
        return "productListPage";
    }
}
