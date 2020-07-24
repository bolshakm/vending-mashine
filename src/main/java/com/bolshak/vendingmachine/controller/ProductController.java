package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.forms.ProductForm;
import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.Objects.isNull;

@Controller()
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public String getProductPage(Model model){
		List<Product> allProducts = productService.findAll();
		model.addAttribute("allProducts",allProducts);
		model.addAttribute("isProduct", true);
		return "index";
	}

	@PostMapping("/products/save")
	public String save(ProductForm form){
		if (isNull(form.getId())){
			productService.create(form);
		} else {
			productService.update(form);
		}
		return "redirect:/products";
	}

	@GetMapping("/products/edit")
	public String edit(@RequestParam String id, Model model){
		Product product = productService.findById(Long.parseLong(id));
		if (isNull(product)){
			model.addAttribute("errorMessage", "Product does not exist");
		} else {
			List<Product> allProducts = productService.findAll();
			model.addAttribute("allProducts",allProducts);
			model.addAttribute("isProduct", true);
			model.addAttribute(product);
		}
		return "index";
	}

	@GetMapping("/products/delete")
	public String delete(@RequestParam String id){
		productService.delete(Long.parseLong(id));
		return "redirect:/products";
	}
}
