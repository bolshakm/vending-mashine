package com.bolshak.vendingmachine.service;

import com.bolshak.vendingmachine.forms.ProductForm;
import com.bolshak.vendingmachine.model.Product;

import java.util.List;

public interface ProductService {

	void create(ProductForm form);

	void update(ProductForm form);

	void delete(Long id);

	List<Product> findAll();

	Product findById(Long id);

	List<Product> findAll(List<Long> ids);

}
