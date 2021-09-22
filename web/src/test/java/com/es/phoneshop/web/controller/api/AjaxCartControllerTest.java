package com.es.phoneshop.web.controller.api;

import com.es.core.services.cart.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AjaxCartControllerTest {

    @Mock
    private AjaxCartController ajaxCartController;
    @Mock
    private CartService cartService;

    @Test
    public void addToCartTest() {
        ajaxCartController.addToCart(-1L, -1);
        verify(cartService, never()).addToCart(any(), any());
    }

    @Test
    public void updateCartTest() {
        Map<Long, Integer> update = Collections.singletonMap(1L, -1);
        ajaxCartController.updateCart(update);
        verify(cartService, never()).updateCart(update);
    }

    @Test
    public void deleteFromCartTest() {
        ajaxCartController.deleteFromCart(-1L);
        verify(cartService, never()).deleteFromCart(-1L);
    }
}
