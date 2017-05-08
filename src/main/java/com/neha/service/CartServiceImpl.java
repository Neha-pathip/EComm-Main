package com.neha.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neha.model.Cart;
import com.neha.model.CartItem;
import com.neha.repository.CartItemRepository;
import com.neha.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart getCartByCartId(Long cartId) {
        return cartRepository.findOne(cartId);
    }

    public void update(Cart cart) {
        double grandTotal = getCustomerOrderGrandTotalByCart(cart);
        Double truncatedDouble = new BigDecimal(grandTotal)
                .setScale(3, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
        cart.setGrandTotal(truncatedDouble);

        cartRepository.save(cart);
    }


    public double getCustomerOrderGrandTotalByCart(Cart cart) {
        double grandTotal = 0;
        List<CartItem> cartItems = cart.getCartItems();

        for (CartItem item : cartItems) {
            grandTotal += item.getTotalPriceDouble();
        }

        return grandTotal;
    }

    public Cart validate(Object cartId) throws IOException {
        if (cartId == null) {
            throw new IOException("Please Login.");
        }

        Cart cart = cartRepository.findOne((Long) cartId);
        if (cart == null || cart.getCartItems().size() == 0) {
            throw new IOException("cart null or cartItem size == 0.c");
        }
        update(cart);
        return cart;
    }

    public void emptyCart(Cart cart) {
        for (CartItem cartItem : cart.getCartItems()) {
            cartItemRepository.delete(cartItem);
        }
        cart.setGrandTotal(0);
        cartRepository.save(cart);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

}
