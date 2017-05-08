package com.neha.service;

import java.util.List;

import com.neha.model.Category;
import com.neha.model.Product;

public interface ProductService {

    void getVisited(Long productId);

    List<Product> getProductsByMainCategory(String mainCategoryName);

    List<Product> getProductsByCategory(Category category);

    List<Product> getAllProducts();

    Product getProductById(Long productId);

    Product save(Product product);

    void delete(Long productId);

    // 0: Price ASC, 1: Price DESC
    List<Product> sort(List<Product> products, String sortType);
}
