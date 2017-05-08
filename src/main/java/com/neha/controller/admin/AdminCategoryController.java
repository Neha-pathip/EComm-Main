package com.neha.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.neha.model.Category;
import com.neha.service.CategoryService;

@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Category addCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }


    @RequestMapping(method = RequestMethod.DELETE)
    public Boolean deleteCategoryById(@RequestParam(value = "id", required = true) Long categoryId) {
        categoryService.delete(categoryId);
        return Boolean.TRUE;
    }
}
