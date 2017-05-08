package com.neha.service;

import java.util.List;

import com.neha.model.Category;

public interface CategoryService {
	
	List<Category> getAllCategory();
	
	List<String> getAllMainCategory();

	List<String> getAllSubCategory();
	
	Category save(Category category);
	
	void delete(Long categoryId);
	
	Category getCategoryById(Long categoryId);
}
