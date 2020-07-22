package com.bolshak.vendingmachine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/login")
public class LoginController {

	@GetMapping()
	public String getLoginPage() {
		return "login";
	}

	@PostMapping()
	public String login() {
		return "login";
	}

}
