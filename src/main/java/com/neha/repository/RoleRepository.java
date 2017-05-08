package com.neha.repository;

import org.springframework.data.repository.CrudRepository;

import com.neha.model.Customer;
import com.neha.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByAuthorityAndCustomer(String auth, Customer customer);
}
