package com.neha.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.neha.model.CartItem;

@Transactional
public interface CartItemRepository extends CrudRepository<CartItem, Long>{

}
