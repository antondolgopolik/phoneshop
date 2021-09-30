package com.es.phoneshop.web.controller.pages;

import com.es.core.dto.cart.CartAdditionDto;
import com.es.core.dto.cart.QuickOrderFormDto;
import com.es.core.exceptions.MultiErrorException;
import com.es.core.services.cart.CartService;
import com.es.core.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/quickOrder")
public class QuickOrderPageController {
    private static final String CART_ATTR = "cart";
    private static final String AUTHENTICATED_ATR = "authenticated";

    @Resource
    private CartService cartService;
    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showQuickOrderPage(Model model,
                                           @Valid @ModelAttribute("quickOrderForm") QuickOrderFormDto quickOrderFormDto,
                                           BindingResult bindingResult) {
        // Set attributes
        model.addAttribute(CART_ATTR, cartService.getCart());
        model.addAttribute(AUTHENTICATED_ATR, userService.isAuthenticated());
        return new ModelAndView("quickOrderPage", "quickOrderForm", quickOrderFormDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public RedirectView addAllToCart(Model model,
                                     RedirectAttributes redirectAttributes,
                                     @Validated @ModelAttribute("quickOrderForm") QuickOrderFormDto quickOrderFormDto,
                                     BindingResult bindingResult) {
        // Filter input
        List<CartAdditionDto> filtered = filterInput(quickOrderFormDto, bindingResult);
        // Add filtered input to cart
        try {
            cartService.addAllToCart(filtered);
        } catch (MultiErrorException e) {
            handleMultiErrorException(e, filtered, bindingResult);
        }
        // Reset applied input
        resetAppliedInput(filtered);
        // Set attributes
        model.addAttribute(CART_ATTR, cartService.getCart());
        model.addAttribute(AUTHENTICATED_ATR, userService.isAuthenticated());
        redirectAttributes.addFlashAttribute("quickOrderForm", quickOrderFormDto);
        return new RedirectView("/quickOrder", true);
    }

    private void handleMultiErrorException(MultiErrorException e,
                                           List<CartAdditionDto> filtered,
                                           BindingResult bindingResult) {
        //noinspection unchecked
        Map<Integer, String> errors = (Map<Integer, String>) e.getData();
        ListIterator<CartAdditionDto> listIterator = filtered.listIterator();
        int i = -1;
        while (listIterator.hasNext()) {
            listIterator.next();
            i++;
            String message = errors.get(i);
            if (message != null) {
                // Expose information about error
                bindingResult.rejectValue(
                        "cartAdditions[" + i + "].model",
                        "validation",
                        message
                );
                // Delete from filtered
                listIterator.remove();
            }
        }
    }

    private List<CartAdditionDto> filterInput(QuickOrderFormDto quickOrderFormDto,
                                              BindingResult bindingResult) {
        ArrayList<CartAdditionDto> cartAdditions = quickOrderFormDto.getCartAdditions();
        List<CartAdditionDto> filtered = new LinkedList<>();
        for (int i = 0; i < cartAdditions.size(); i++) {
            if (!bindingResult.hasFieldErrors("cartAdditions[" + i + "].*")) {
                filtered.add(cartAdditions.get(i));
            }
        }
        return filtered;
    }

    private void resetAppliedInput(List<CartAdditionDto> filtered) {
        for (CartAdditionDto cartAdditionDto : filtered) {
            cartAdditionDto.setModel(null);
            cartAdditionDto.setQuantity(null);
        }
    }
}
