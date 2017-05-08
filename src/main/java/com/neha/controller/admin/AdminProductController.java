package com.neha.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.neha.model.Product;
import com.neha.service.ProductService;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Product addProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Boolean deleteProductById(@RequestParam(value = "id", required = true) Long productId) {
        productService.delete(productId);
        return Boolean.TRUE;
    }
}
