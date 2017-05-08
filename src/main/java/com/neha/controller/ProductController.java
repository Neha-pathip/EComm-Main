package com.neha.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.neha.model.Category;
import com.neha.model.Product;
import com.neha.model.ProductComment;
import com.neha.repository.ProductCommentRepository;
import com.neha.service.CustomerService;
import com.neha.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductCommentRepository productCommentRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getProducts(@RequestParam(value = "name", required = false) String productName,
                                     @RequestParam(value = "mainCat", required = false) String mainCategoryName,
                                     @RequestParam(value = "subCategory", required = false) String subCategoryName,
                                     @RequestParam(value = "sortType", required = false) String sortType,
                                     @RequestParam(value = "lp", required = false) String lowerPrice,
                                     @RequestParam(value = "hp", required = false) String higherPrice)
            throws IOException {
        // filter initiate
        boolean nameFilter = false;
        boolean mainCategoryFilter = false;
        boolean subCategoryFilter = false;
        boolean needSort = false;
        boolean priceFilter = false;

        int lowerPrice_i = 0;
        int higherPrice_i = 0;
        
        /* All the required filter check here */
        if (productName != null) {
            nameFilter = true;
        }
        if (mainCategoryName != null) {
            mainCategoryFilter = true;
        }
        if (subCategoryName != null) {
            subCategoryFilter = true;
        }
        if (sortType != null) {
            needSort = true;
        }
        if (lowerPrice != null && higherPrice != null) {
            lowerPrice_i = Integer.parseInt(lowerPrice);
            higherPrice_i = Integer.parseInt(higherPrice);
            priceFilter = true;
        }

        // get all product
        List<Product> products = productService.getAllProducts();

        // Filter by product name
        Iterator<Product> iter = products.iterator();

        if (nameFilter) {
            while (iter.hasNext()) {
                Product product = iter.next();
                if (!product.getProductName().toLowerCase().contains(productName.toLowerCase())) {
                    iter.remove();
                }
            }
        }
        // Filter by product mainCategory
        iter = products.listIterator();
        if (mainCategoryFilter) {
            while (iter.hasNext()) {
                Product product = iter.next();
                Category category = product.getProductCategory();
                if (!category.getMainCategoryName().equalsIgnoreCase(mainCategoryName)) {
                    iter.remove();
                }
            }
        }
        // Filter by product subCategory
        iter = products.listIterator();
        if (subCategoryFilter) {
            while (iter.hasNext()) {
                Product product = iter.next();
                Category category = product.getProductCategory();
                if (!category.getSubCategoryName().equalsIgnoreCase(subCategoryName)) {
                    iter.remove();
                }
            }
        }

        // Filter by product price
        iter = products.listIterator();
        if (priceFilter) {
            while (iter.hasNext()) {
                Product product = iter.next();
                if (product.getProductPrice() <= lowerPrice_i || product.getProductPrice() >= higherPrice_i) {
                    iter.remove();
                }
            }
        }

        // sort product
        if (needSort) {
            products = productService.sort(products, sortType);
        }

        return products;
    }

    @RequestMapping(value = "id/{val}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Product viewProduct(@PathVariable(value = "val", required = true) Long productId) throws IOException {
        Product product = productService.getProductById(productId);
        productService.getVisited(productId);
        return product;
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductComment addProductComment(@RequestParam(value = "id", required = true) Long productId,
    		@RequestBody ProductComment productComment) {
        productComment.setCommentDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        Product product = productService.getProductById(productId);

        productComment.setProduct(product);
        productComment = productCommentRepository.save(productComment);
        return productComment;
    }
}
