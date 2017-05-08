package com.neha.service;

import java.util.List;

import com.neha.model.ShippingAddress;

public interface CustomerAddressService {
	
	List<ShippingAddress> getAllShippingAddressByCustomerId(Object customerId);
	
	void addShippingAddressObject(Object customerId, ShippingAddress shippingAddress);
}
