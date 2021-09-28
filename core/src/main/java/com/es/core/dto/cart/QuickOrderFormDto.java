package com.es.core.dto.cart;

import javax.validation.Valid;
import java.util.ArrayList;

public class QuickOrderFormDto {
    @Valid
    private ArrayList<CartAdditionDto> cartAdditions;

    public ArrayList<CartAdditionDto> getCartAdditions() {
        return cartAdditions;
    }

    public void setCartAdditions(ArrayList<CartAdditionDto> cartAdditions) {
        this.cartAdditions = cartAdditions;
    }
}
