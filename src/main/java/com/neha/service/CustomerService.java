package com.neha.service;

import java.util.List;

import com.neha.model.Customer;

public interface CustomerService {
	
	Customer findOne(Long customerId);
	
	Customer save(Customer customer);

	Customer findByEmail(String email);
	
	boolean hasRole(String role, Customer customer);
	
	List<Customer> getAllCustomer();
	
	void delete(Long customerId);
}
