package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.forms.VendingMachineForm;
import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.model.VendingMachine;
import com.bolshak.vendingmachine.service.ProductService;
import com.bolshak.vendingmachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller("/vending-machine")
public class VendingMachineController {
	@Autowired
	private VendingMachineService vendingMachineServiceImpl;

	@Autowired
	private ProductService productServiceImpl;

	@GetMapping({"vendingMachine"})
	public String get(@PathVariable VendingMachine vendingMachine, Model model) {
		model.addAttribute(vendingMachine);
		return "vendingMachine";
	}

	@GetMapping("/create")
	public String getCreatePage(Model model) {
		List<Product> allProducts = productServiceImpl.findAll();
		model.addAttribute(allProducts);
		return "createVendingMachine";
	}

	@PostMapping("/create")
	public String create(VendingMachineForm vendingMachineForm) {
		vendingMachineServiceImpl.save(vendingMachineForm);
		return "createVendingMachine";
	}
}
