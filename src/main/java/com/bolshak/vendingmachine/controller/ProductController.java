package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public String createProduct(Model model){
//		productService.create
		return null;

	}
}
