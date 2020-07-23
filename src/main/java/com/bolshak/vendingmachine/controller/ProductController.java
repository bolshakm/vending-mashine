package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public String getProductPage(Model model){
		List<Product> allProducts = productService.findAll();
		model.addAttribute(allProducts);
		return "createProduct";

	}
}
