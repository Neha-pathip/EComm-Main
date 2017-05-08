package com.neha.service;

import java.io.IOException;

import com.neha.model.Cart;

public interface CartService {
	
	Cart validate(Object customerId) throws IOException;
	
	void emptyCart(Cart cart);
	
	Cart save(Cart cart);
}
