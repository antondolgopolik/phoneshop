package com.es.core.services.cart;

import com.es.core.dao.cart.CartDao;
import com.es.core.dao.cartitem.CartItemDao;
import com.es.core.dao.phone.PhoneDao;
import com.es.core.dao.stock.StockDao;
import com.es.core.model.cart.Cart;
import com.es.core.model.cart.CartItem;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.Stock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private PhoneDao phoneDao;
    @Resource
    private StockDao stockDao;
    @Resource
    private CartDao cartDao;
    @Resource
    private CartItemDao cartItemDao;

    @Override
    public void add(Long phoneId, Integer delta) {
        Phone phone = readPhone(phoneId);
        Optional<CartItem> cartItemOptional = cartItemDao.get(phoneId);
        CartItem cartItem = cartItemOptional.orElse(new CartItem(phoneId, 0));
        // Prepare updated
        Stock newUpdatedStock = newUpdatedStock(phone, delta);
        CartItem newUpdatedCartItem = newUpdatedCartItem(cartItem, delta);
        // Update
        stockDao.update(newUpdatedStock);
        if (cartItemOptional.isPresent()) {
            cartItemDao.update(newUpdatedCartItem);
        } else {
            cartItemDao.save(newUpdatedCartItem);
        }
        updateCart(phone, delta);
    }

    @Override
    public void update(Long phoneId, Integer quantity) {
        Optional<CartItem> cartItemOptional = cartItemDao.get(phoneId);
        // Check if cart item with given phone id exists
        if (cartItemOptional.isPresent()) {
            Phone phone = readPhone(phoneId);
            CartItem cartItem = cartItemOptional.get();
            int delta = quantity - cartItem.getQuantity();
            // Prepare updates
            Stock newUpdatedStock = newUpdatedStock(phone, delta);
            CartItem newUpdatedCartItem = newUpdatedCartItem(cartItem, delta);
            // Update
            stockDao.update(newUpdatedStock);
            cartItemDao.update(newUpdatedCartItem);
            updateCart(phone, delta);
        } else {
            throw new IllegalArgumentException("Cart item with given phone id doesn't exist!");
        }
    }

    @Override
    public void remove(Long phoneId) {
        throw new UnsupportedOperationException("TODO");
    }

    private Phone readPhone(Long phoneId) {
        Optional<Phone> phoneOptional = phoneDao.get(phoneId);
        if (phoneOptional.isEmpty()) {
            throw new IllegalArgumentException("Phone with given id doesn't exist!");
        }
        return phoneOptional.get();
    }

    private Stock newUpdatedStock(Phone phone, Integer delta) {
        Stock newStock = readStock(phone).clone();
        // Check
        int newReserved = newStock.getReserved() + delta;
        if ((newReserved < 0) || (newReserved > newStock.getStock())) {
            throw new IllegalArgumentException("Invalid quantity change!");
        }
        // Update
        newStock.setReserved(newReserved);
        return newStock;
    }

    private Stock readStock(Phone phone) {
        Optional<Stock> stockOptional = stockDao.getByPhone(phone);
        if (stockOptional.isEmpty()) {
            throw new IllegalArgumentException("Information on the stock of phones isn't available!");
        }
        return stockOptional.get();
    }

    private CartItem newUpdatedCartItem(CartItem cartItem, Integer delta) {
        CartItem newCartItem = cartItem.clone();
        // Check
        int newQuantity = cartItem.getQuantity() + delta;
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Invalid quantity change!");
        }
        // Update
        newCartItem.setQuantity(newQuantity);
        return newCartItem;
    }

    private void updateCart(Phone phone, Integer delta) {
        Cart cart = cartDao.get();
        cart.setTotalQuantity(cart.getTotalQuantity() + delta);
        cart.setTotalCost(cart.getTotalCost().add(phone.getPrice().multiply(BigDecimal.valueOf(delta))));
        cartDao.update(cart);
    }
}
