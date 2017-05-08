package com.neha.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.neha.model.Cart;

@Transactional
public interface CartRepository extends CrudRepository<Cart, Long>{
	
}
