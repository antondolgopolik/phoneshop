package com.es.phoneshop.web.controller.api;

import com.es.core.dto.cart.CartUpdatesDto;
import com.es.core.dto.cart.QuantityDto;
import com.es.core.services.cart.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AjaxCartControllerTest {

    @Mock
    private AjaxCartController ajaxCartController;
    @Mock
    private CartService cartService;
    @Mock
    private BindingResult bindingResult;

    @Test
    public void addToCartTest() {
        QuantityDto quantityDto = new QuantityDto();
        quantityDto.setQuantity(-1);

        ajaxCartController.addToCart(-1L, quantityDto, bindingResult);
        verify(cartService, never()).addToCart(any(), any());
    }

    @Test
    public void updateCartTest() {
        Map<Long, Integer> update = Collections.singletonMap(1L, -1);
        CartUpdatesDto cartUpdatesDto = new CartUpdatesDto();
        cartUpdatesDto.setUpdates(update);

        ajaxCartController.updateCart(cartUpdatesDto, bindingResult);
        verify(cartService, never()).updateCart(update);
    }

    @Test
    public void deleteFromCartTest() {
        ajaxCartController.deleteFromCart(-1L);
        verify(cartService, never()).deleteFromCart(-1L);
    }
}
