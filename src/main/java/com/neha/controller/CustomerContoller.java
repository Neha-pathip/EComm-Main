package com.neha.controller;

import com.neha.model.Customer;
import com.neha.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerContoller {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Customer getCustomer(@RequestHeader(value = "custId_") String custId) {
        if (custId != null) {
            return customerService.findOne(Long.parseLong(custId, 10));
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Customer> listCustomers() {
        return customerService.getAllCustomer();
    }

}
