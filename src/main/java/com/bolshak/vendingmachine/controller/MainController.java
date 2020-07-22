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
	public String login() {
		userServiceImpl.isExist();
		return "login";
	}

	@GetMapping("main")
	public String main() {
		return "main";
	}

}
