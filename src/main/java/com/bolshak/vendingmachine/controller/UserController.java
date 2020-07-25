package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public String getUserPage(Model model){
		model.addAttribute("allProducts", userService.getCurrentUser().getProducts());
		model.addAttribute("isUserPage", true);
		return "index";
	}

	@GetMapping("/user/use")
	public String use(Model model, @RequestParam String id){
		userService.useProduct(Long.parseLong(id));
		return "redirect:/user";
	}
}
