package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.service.UserService;
import com.bolshak.vendingmachine.util.PageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.bolshak.vendingmachine.util.ModelAttributesConstants.ALL_PRODUCTS;
import static com.bolshak.vendingmachine.util.ModelAttributesConstants.IS_PRODUCT_PAGE;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users-product")
	public String getUserPage(Model model) {
		model.addAttribute(ALL_PRODUCTS, userService.getCurrentUser().getProducts());
		model.addAttribute(IS_PRODUCT_PAGE, true);
		return PageConstants.INDEX;
	}

	@GetMapping("/users-product/use")
	public String use(Model model, @RequestParam String id) {
		userService.useProduct(Long.parseLong(id));
		return PageConstants.REDIRECT_TO_USER;
	}
}
