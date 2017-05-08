package com.neha.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.neha.model.Customer;
import com.neha.model.ShippingAddress;

public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, Long>{
	
	List<ShippingAddress> findAllByCustomer(Customer customer);
	
	
}
