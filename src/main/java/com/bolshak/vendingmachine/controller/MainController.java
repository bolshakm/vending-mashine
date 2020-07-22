package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.model.VendingMachine;
import com.bolshak.vendingmachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller()
public class MainController {
	@Autowired
	private VendingMachineService vendingMachineService;

	@GetMapping("/index")
	public String getMainPage(Model model) {
		List<VendingMachine> vendingMachines = vendingMachineService.findAll();
		model.addAttribute(vendingMachines);
		return "index";
	}

	@GetMapping("/create/vending-machine")
	public String createVendingMachine() {
		return "";
	}

	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

}
