package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.forms.ProductForm;
import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.service.ProductService;
import com.bolshak.vendingmachine.service.UserService;
import org.apache.naming.EjbRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static java.lang.String.format;
import static java.util.Objects.isNull;

@Controller()
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@GetMapping("/products")
	public String getProductPage(Model model) {
		List<Product> allProducts = productService.findAll();
		model.addAttribute("allProducts", allProducts);
		model.addAttribute("isProduct", true);
		return "index";
	}

	@GetMapping("/product/buy")
	public String buy(@RequestParam String productId, @RequestParam String vmId, Model model) {

		if (userService.hasUserTheProduct(Long.parseLong(productId))) {
			String error = "Sorry but, you have this product. Use this product for buying again.";
			return format("redirect:/vending-machine/select?id=%s&message=%s", vmId, error);
		}

		boolean isBuyingProductSuccessful = userService
				.isBuyingProductSuccessful(Long.parseLong(vmId), Long.parseLong(productId));

		if (!isBuyingProductSuccessful) {
			String error = "Sorry but, you haven't enough  money or product is absent.";
			return format("redirect:/vending-machine/select?id=%s&message=%s", vmId, error);

		}

		List<Product> allProducts = productService.findAll();
		model.addAttribute("allProducts", allProducts);
		model.addAttribute("isProduct", true);

		return format("redirect:/vending-machine/select?id=%s", vmId);
	}

	@PostMapping("/products/save")
	public String save(ProductForm form) {
		if (isNull(form.getId())) {
			productService.create(form);
		} else {
			productService.update(form);
		}
		return "redirect:/products";
	}

	@GetMapping("/products/edit")
	public String edit(@RequestParam String id, Model model) {
		Product product = productService.findById(Long.parseLong(id));
		if (isNull(product)) {
			model.addAttribute("errorMessage", "Product does not exist");
		} else {
			List<Product> allProducts = productService.findAll();
			model.addAttribute("allProducts", allProducts);
			model.addAttribute("isProduct", true);
			model.addAttribute(product);
		}
		return "index";
	}

	@GetMapping("/products/delete")
	public String delete(@RequestParam String id) {
		productService.delete(Long.parseLong(id));
		return "redirect:/products";
	}
}
