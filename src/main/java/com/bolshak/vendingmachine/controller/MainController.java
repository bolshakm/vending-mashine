package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@Autowired
	private UserService userServiceImpl;

	@GetMapping("/")
	public String main() {
		userServiceImpl.isExist();
		return "main";
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}


}
