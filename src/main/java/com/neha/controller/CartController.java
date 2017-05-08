package com.neha.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.neha.service.CartService;
import com.neha.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.neha.model.Cart;
import com.neha.model.CartItem;
import com.neha.model.Product;
import com.neha.repository.CartItemRepository;
import com.neha.repository.CartRepository;
import com.neha.repository.CustomerRepository;
import com.neha.repository.ProductRepository;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    Cart getCart(@RequestHeader(value="cartId_") String cartId) {
        if (cartId != null) {
            return cartRepository.findOne(Long.parseLong(cartId, 10));
        }
        return null;
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    CartItem addCartItem(@RequestHeader(value="cartId_") String cartId,
    		@RequestParam(value = "q") int quantity,
    		@RequestParam(value = "productId") Long productId) {
    	Product product  = productService.getProductById(productId);
    	Cart cart = cartRepository.findOne(Long.parseLong(cartId, 10));
    	CartItem cartItem = new CartItem();
    	cartItem.setProduct(product);
    	cartItem.setQuantity(quantity);
    	cartItem.setTotalPriceDouble(product.getProductPrice() * quantity);
    	cartItem.setCart(cart);
    	cartItem = cartItemRepository.save(cartItem);
        return cartItem;
    }

    @RequestMapping(value = "/{cartItemId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable(value = "cartItemId") Long cartItemId) {
        cartItemRepository.delete(cartItemId);
    }

    @RequestMapping(value = "/{cartItemId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void editItem(@RequestParam(value = "q") int quantity,
                         @PathVariable(value = "cartItemId") Long cartItemId) {
        CartItem cartItem = cartItemRepository.findOne(cartItemId);
        cartItem.setTotalPriceDouble(cartItem.getProduct().getProductPrice() * quantity);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request.")
    public void handleClientErrors(Exception e) {
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Server error.")
    public void handleServertErrors(Exception e) {
    }
}
