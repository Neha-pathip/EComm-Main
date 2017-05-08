package com.neha.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.neha.model.Product;
import com.neha.model.ProductComment;

public interface ProductCommentRepository extends CrudRepository<ProductComment, Long>{
	List<ProductComment> findByProduct(Product product);
}
