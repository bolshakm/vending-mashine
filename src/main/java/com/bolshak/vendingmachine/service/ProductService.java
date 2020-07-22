package com.bolshak.vendingmachine.service;

import com.bolshak.vendingmachine.model.Product;

public interface ProductService {

	void create(Product product);

	void update(Product product);

	void delete(Product product);
}
