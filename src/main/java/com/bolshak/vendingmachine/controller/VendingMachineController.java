package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.forms.VendingMachineForm;
import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.model.User;
import com.bolshak.vendingmachine.model.VendingMachine;
import com.bolshak.vendingmachine.model.VendingMachineHasProduct;
import com.bolshak.vendingmachine.service.ProductService;
import com.bolshak.vendingmachine.service.UserService;
import com.bolshak.vendingmachine.service.VendingMachineHasProductService;
import com.bolshak.vendingmachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static java.util.Objects.nonNull;

@Controller()
public class VendingMachineController {
	@Autowired
	private VendingMachineService vendingMachineServiceImpl;

	@Autowired
	private ProductService productServiceImpl;

	@Autowired
	private UserService userService;

	@Autowired
	private VendingMachineHasProductService vendingMachineHasProductService;

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping("/vending-machine")
	public String get(Model model) {
		List<VendingMachine> vendingMachines = vendingMachineServiceImpl.findAll();
		List<Product> allProducts = productServiceImpl.findAll();

		model.addAttribute("allProducts", allProducts);
		model.addAttribute("vendingMachines", vendingMachines);
		model.addAttribute("isVendingMachine", true); // todo chenge all to enable....element
		return "index";
	}

	@GetMapping("/vending-machine/delete")
	public String delete(@RequestParam String id) {
		vendingMachineServiceImpl.delete(Long.parseLong(id));
		return "redirect:/vending-machine";
	}

	@GetMapping("/vending-machine/edit")
	public String update(@RequestParam String id, Model model) {
		VendingMachine vendingMachine = vendingMachineServiceImpl.find(Long.parseLong(id));
		List<VendingMachine> vendingMachines = vendingMachineServiceImpl.findAll();
		List<Product> allProducts = productServiceImpl.findAll();

		model.addAttribute("vendingMachine", vendingMachine);
		model.addAttribute("allProducts", allProducts);
		model.addAttribute("vendingMachines", vendingMachines);
		model.addAttribute("isVendingMachine", true); // todo chenge all to enable....element
		return "index";
	}

	@PostMapping("/vending-machine/save")
	public String save(VendingMachineForm vendingMachineForm) {
		if (nonNull(vendingMachineForm.getId())) {
			vendingMachineServiceImpl.update(vendingMachineForm);
		} else {
			vendingMachineServiceImpl.save(vendingMachineForm);
		}
		return "redirect:/vending-machine";
	}

	@GetMapping("/vending-machine/select")
	public String select(@RequestParam String id,@RequestParam(required = false) String message,  Model model) {
		long vendingMachineId = Long.parseLong(id);
		VendingMachine vendingMachine = vendingMachineServiceImpl.find(vendingMachineId);
		List<VendingMachineHasProduct> vendingMachineProducts = vendingMachineHasProductService.findAllByVendingMachine(
				vendingMachineId);
		User user = userService.getCurrentUser();

		model.addAttribute("user", user);
		model.addAttribute("message", message);
		model.addAttribute("vendingMachine", vendingMachine);
		model.addAttribute("vendingMachineProducts", vendingMachineProducts);
		model.addAttribute("isSelectedVMPage", true);
		return "index";
	}
}
