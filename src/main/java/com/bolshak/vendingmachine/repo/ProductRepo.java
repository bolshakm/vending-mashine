package com.bolshak.vendingmachine.repo;

import com.bolshak.vendingmachine.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {
}
