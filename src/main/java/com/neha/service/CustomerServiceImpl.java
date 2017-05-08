package com.neha.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.neha.model.Cart;
import com.neha.model.Customer;
import com.neha.model.Role;
import com.neha.repository.CartRepository;
import com.neha.repository.CustomerRepository;
import com.neha.repository.RoleRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CartRepository cartRepository;

    public Customer save(Customer customer) {
//        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        // save or update
        if (customer.getCustomerId() == null) {
            Cart cart = new Cart();
            Role role = new Role();
            role.setEmail(customer.getEmail());
            role.setAuthority("ROLE_UNAUTH");
            // create customer first
            customerRepository.save(customer);
            // save cart
            cart.setCustomer(customer);
            cartRepository.save(cart);
            // update cartId in Customer
            customer.setCart(cart);
            customer.setEnabled(true);
            customer = customerRepository.save(customer);
            // save role
            role.setCustomer(customer);
            roleRepository.save(role);
            return customer;
        } else {
           return customerRepository.save(customer);
        }
    }

    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer findOne(Long customerId) {
        return customerRepository.findOne(customerId);
    }

    public boolean hasRole(String role, Customer customer) {
        return (roleRepository.findByAuthorityAndCustomer(role, customer) != null);
    }

    public List<Customer> getAllCustomer() {
        return (List<Customer>) customerRepository.findAll();
    }

    public void delete(Long customerId) {
        customerRepository.delete(customerId);
    }
}
