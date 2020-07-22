package com.bolshak.vendingmachine.repo;

import com.bolshak.vendingmachine.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
