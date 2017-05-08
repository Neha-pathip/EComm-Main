package com.neha.controller;


import com.neha.model.Category;
import com.neha.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Category> categoryList() {
        return categoryService.getAllCategory();
    }

    @RequestMapping(value = "id/{val}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Category categoryById(@PathVariable(name = "val", required = true) Long id) {
        return categoryService.getCategoryById(id);
    }

    @RequestMapping(value = "main", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<String> mainCategoryList() {
        return categoryService.getAllMainCategory();
    }

    @RequestMapping(value = "sub", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<String> subCategoryList() {
        return categoryService.getAllSubCategory();
    }
}
