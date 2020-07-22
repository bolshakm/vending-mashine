package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.forms.LoginFrom;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String login(LoginFrom loginFrom, Model model) {
		System.out.println(loginFrom.getLogin());
		System.out.println(loginFrom.getPassword());
		return "main";
	}

}
