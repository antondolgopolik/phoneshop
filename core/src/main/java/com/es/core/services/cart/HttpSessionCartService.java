package com.es.core.services.cart;

import com.es.core.dao.phone.PhoneDao;
import com.es.core.dao.stock.StockDao;
import com.es.core.dto.cart.CartAdditionDto;
import com.es.core.dto.cart.CartItemDto;
import com.es.core.exceptions.MultiErrorException;
import com.es.core.model.cart.Cart;
import com.es.core.model.cart.CartItem;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.Stock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@SessionScope
public class HttpSessionCartService implements CartService {
    private static final String CART_ATTR = HttpSessionCartService.class.getName() + ".cart";
    private static final String CART_ITEMS_MAP_ATTR = HttpSessionCartService.class.getName() + ".cartItemsMap";

    private final HttpSessionCartServiceContext mainContext;

    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource
    private PhoneDao phoneDao;
    @Resource
    private StockDao stockDao;

    public HttpSessionCartService(HttpSession httpSession) {
        var cart = (Cart) httpSession.getAttribute(CART_ATTR);
        if (cart == null) {
            cart = new Cart();
            httpSession.setAttribute(CART_ATTR, cart);
        }
        //noinspection unchecked
        var cartItemsMap = (HashMap<Long, CartItem>) httpSession.getAttribute(CART_ITEMS_MAP_ATTR);
        if (cartItemsMap == null) {
            cartItemsMap = new HashMap<>();
            httpSession.setAttribute(CART_ITEMS_MAP_ATTR, cartItemsMap);
        }
        // Create main context
        mainContext = new HttpSessionCartServiceContext(cart, cartItemsMap);
    }

    @Override
    public Cart getCart() {
        return mainContext.getCart();
    }

    @Override
    public CartItemDto getCartItem(Long phoneId) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setPhone(phoneDao.get(phoneId).orElseThrow());
        cartItemDto.setQuantity(mainContext.getCartItemsMap().get(phoneId).getQuantity());
        return cartItemDto;
    }

    @Override
    public Collection<CartItemDto> getCartItems() {
        return mainContext.getCartItemsMap().values().stream().map(cartItem -> {
            CartItemDto cartItemDto = new CartItemDto();
            cartItemDto.setPhone(phoneDao.get(cartItem.getPhoneId()).orElseThrow());
            cartItemDto.setQuantity(cartItem.getQuantity());
            return cartItemDto;
        }).collect(Collectors.toList());
    }

    @Override
    public void addAllToCart(Map<Integer, CartAdditionDto> additions) {
        List<Phone> phones = phoneDao.get();
        additions.forEach((index, addition) -> {
            Optional<Phone> phoneOptional = phones.parallelStream()
                    .filter(phone -> phone.getModel().equalsIgnoreCase(addition.getModel()))
                    .findAny();
            addToCart(phoneOptional.get().getId(), addition.getQuantity());
        });
    }

    @Override
    public void addToCart(Long phoneId, Integer delta) {
        addToCart(mainContext, phoneId, delta, true);
    }

    @SuppressWarnings("ConstantConditions")
    private void addToCart(HttpSessionCartServiceContext context, Long phoneId, Integer delta, boolean update) {
        CartItem cartItem = context.getCartItemsMap().get(phoneId);
        // Prepare updates
        Stock newUpdatedStock = newUpdatedStock(readStock(phoneId), delta, update);
        CartItem newUpdatedCartItem = newUpdatedCartItem(
                cartItem != null ? cartItem : new CartItem(phoneId, 0),
                delta, update
        );
        Cart newUpdatedCart = newUpdatedCart(context.getCart(), readPhone(phoneId), delta, update);
        // Update
        if (update) {
            stockDao.update(newUpdatedStock);
            context.putCartItem(newUpdatedCartItem);
            context.updateCart(newUpdatedCart);
        }
    }

    @Override
    public void updateCart(Long phoneId, Integer quantity) {
        updateCart(mainContext, phoneId, quantity, true);
    }

    @SuppressWarnings("ConstantConditions")
    private void updateCart(HttpSessionCartServiceContext context, Long phoneId, Integer quantity, boolean update) {
        CartItem cartItem = context.getCartItemsMap().get(phoneId);
        // Check if cart item with given phone id exists
        if (cartItem != null) {
            int delta = quantity - cartItem.getQuantity();
            // Prepare updates
            Stock newUpdatedStock = newUpdatedStock(readStock(phoneId), delta, update);
            CartItem newUpdatedCartItem = newUpdatedCartItem(cartItem, delta, update);
            Cart newUpdatedCart = newUpdatedCart(context.getCart(), readPhone(phoneId), delta, update);
            // Update
            if (update) {
                stockDao.update(newUpdatedStock);
                context.putCartItem(newUpdatedCartItem);
                context.updateCart(newUpdatedCart);
            }
        } else {
            throw new IllegalArgumentException("Cart item with given phone id doesn't exist!");
        }
    }

    @Override
    public void updateCart(Map<Long, Integer> updates) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                // Temporary context
                var context = mainContext.clone();
                // Try updates
                Map<Long, String> errors = new HashMap<>();
                for (Map.Entry<Long, Integer> entry : updates.entrySet()) {
                    Long phoneId = entry.getKey();
                    Integer quantity = entry.getValue();
                    try {
                        updateCart(context, phoneId, quantity, errors.isEmpty());
                    } catch (RuntimeException e) {
                        errors.put(phoneId, e.getMessage());
                    }
                }
                // Update if no errors occurred
                if (errors.isEmpty()) {
                    mainContext.updateContext(context);
                } else {
                    throw new MultiErrorException(errors);
                }
            }
        });
    }

    @Override
    public void deleteFromCart(Long phoneId) {
        deleteFromCart(mainContext, phoneId, true);
    }

    @SuppressWarnings("ConstantConditions")
    private void deleteFromCart(HttpSessionCartServiceContext context, Long phoneId, boolean update) {
        CartItem cartItem = context.getCartItemsMap().get(phoneId);
        // Check if cart item with given phone id exists
        if (cartItem != null) {
            int delta = -cartItem.getQuantity();
            // Prepare updates
            Stock newUpdatedStock = newUpdatedStock(readStock(phoneId), delta, update);
            Cart newUpdatedCart = newUpdatedCart(context.getCart(), readPhone(phoneId), delta, update);
            // Update
            if (update) {
                stockDao.update(newUpdatedStock);
                context.removeCartItem(cartItem);
                context.updateCart(newUpdatedCart);
            }
        } else {
            throw new IllegalArgumentException("Cart item with given phone id doesn't exist!");
        }
    }

    /*
    Must be invoked only within transaction
     */
    @Override
    public void orderCart() {
        List<Stock> stocks = new LinkedList<>();
        Set<Map.Entry<Long, CartItem>> entries = mainContext.getCartItemsMap().entrySet();
        for (Map.Entry<Long, CartItem> entry : entries) {
            Long phoneId = entry.getKey();
            CartItem cartItem = entry.getValue();
            // Update stock
            Stock stock = readStock(phoneId);
            stock.setStock(stock.getStock() - cartItem.getQuantity());
            stock.setReserved(stock.getReserved() - cartItem.getQuantity());
            // Add to update list
            stocks.add(stock);
        }
        // Update stocks
        stockDao.update(stocks);
        // Reset context
        mainContext.resetContext();
    }

    private Phone readPhone(Long phoneId) {
        Optional<Phone> phoneOptional = phoneDao.get(phoneId);
        if (phoneOptional.isEmpty()) {
            throw new IllegalArgumentException("Phone with given id doesn't exist!");
        }
        return phoneOptional.get();
    }

    private Stock readStock(Long phoneId) {
        Optional<Stock> stockOptional = stockDao.get(phoneId);
        if (stockOptional.isEmpty()) {
            throw new IllegalArgumentException("Information on the stock of phones isn't available!");
        }
        return stockOptional.get();
    }

    private Stock newUpdatedStock(Stock stock, Integer delta, boolean returnUpdate) {
        // Check
        int newReserved = stock.getReserved() + delta;
        if ((newReserved < 0) || (newReserved > stock.getStock())) {
            throw new IllegalArgumentException("Invalid stock change!");
        }
        // Update
        if (returnUpdate) {
            Stock newStock = stock.clone();
            newStock.setReserved(newReserved);
            return newStock;
        } else {
            return null;
        }
    }

    private CartItem newUpdatedCartItem(CartItem cartItem, Integer delta, boolean returnUpdate) {
        // Check
        int newQuantity = cartItem.getQuantity() + delta;
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Invalid cart item quantity change!");
        }
        // Update
        if (returnUpdate) {
            CartItem newCartItem = cartItem.clone();
            newCartItem.setQuantity(newQuantity);
            return newCartItem;
        } else {
            return null;
        }
    }

    private Cart newUpdatedCart(Cart cart, Phone phone, Integer delta, boolean returnUpdate) {
        // Check
        int newTotalQuantity = cart.getTotalQuantity() + delta;
        if (newTotalQuantity < 0) {
            throw new IllegalArgumentException("Invalid cart total quantity change!");
        }
        BigDecimal newTotalCost = cart.getSubtotal().add(phone.getPrice().multiply(BigDecimal.valueOf(delta)));
        if (newTotalCost.signum() == -1) {
            throw new IllegalArgumentException("Invalid cart total cost change!");
        }
        // Update
        if (returnUpdate) {
            Cart newCart = cart.clone();
            newCart.setTotalQuantity(newTotalQuantity);
            newCart.setSubtotal(newTotalCost);
            return newCart;
        } else {
            return null;
        }
    }
}
