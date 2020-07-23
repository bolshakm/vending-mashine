package com.bolshak.vendingmachine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/vending-machine")
public class VendingMachineController {
	@GetMapping
	public String get(){
		return "index";
	}
}
