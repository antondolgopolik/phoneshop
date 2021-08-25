package com.es.phoneshop.web.controller.pages;

import javax.annotation.Resource;

import com.es.core.model.phone.PhoneSortType;
import com.es.core.model.phone.SortDirection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.es.core.model.phone.PhoneDao;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/productList")
public class ProductListPageController {
    private static final String PHONES_ATTR = "phones";
    private static final String CURRENT_PAGE_ATTR = "currentPage";
    private static final String PHONES_IN_STOCK_NUMBER_ATTR = "phonesInStockNumber";
    private static final String PHONES_PER_PAGE_ATTR = "phonesPerPage";

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

        model.addAttribute(PHONES_ATTR, phoneDao.findAllInStock(
                request, phoneSortType, sortDirection, (page - 1) * 10, 10)
        );
        model.addAttribute(CURRENT_PAGE_ATTR, page);
        model.addAttribute(PHONES_IN_STOCK_NUMBER_ATTR, phoneDao.getPhonesInStockNumber());
        model.addAttribute(PHONES_PER_PAGE_ATTR, 10);
        return "productList";
    }
}
