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
import com.bolshak.vendingmachine.util.PageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.bolshak.vendingmachine.util.ModelAttributesConstants.ALL_PRODUCTS;
import static com.bolshak.vendingmachine.util.ModelAttributesConstants.IS_SELECTED_VM_PAGE;
import static com.bolshak.vendingmachine.util.ModelAttributesConstants.IS_VENDING_MACHINE_PAGE;
import static com.bolshak.vendingmachine.util.ModelAttributesConstants.MESSAGE;
import static com.bolshak.vendingmachine.util.ModelAttributesConstants.USER;
import static com.bolshak.vendingmachine.util.ModelAttributesConstants.VENDING_MACHINE;
import static com.bolshak.vendingmachine.util.ModelAttributesConstants.VENDING_MACHINES;
import static com.bolshak.vendingmachine.util.ModelAttributesConstants.VENDING_MACHINE_PRODUCTS;
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
		initVendingMachinePage(model);
		return PageConstants.INDEX;
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping("/vending-machine/delete")
	public String delete(@RequestParam String id) {
		vendingMachineServiceImpl.delete(Long.parseLong(id));
		return PageConstants.REDIRECT_TO_VENDING_MACHINE;
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping("/vending-machine/edit")
	public String update(@RequestParam String id, Model model) {
		VendingMachine vendingMachine = vendingMachineServiceImpl.find(Long.parseLong(id));
		model.addAttribute(VENDING_MACHINE, vendingMachine);
		initVendingMachinePage(model);
		return PageConstants.INDEX;
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@PostMapping("/vending-machine/save")
	public String save(VendingMachineForm vendingMachineForm) {

		if (nonNull(vendingMachineForm.getId())) {
			vendingMachineServiceImpl.update(vendingMachineForm);
		} else {
			vendingMachineServiceImpl.save(vendingMachineForm);
		}
		return PageConstants.REDIRECT_TO_VENDING_MACHINE;
	}

	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	@GetMapping("/vending-machine/select")
	public String select(@RequestParam String id, @RequestParam(required = false) String message,
			Model model) {
		//todo add validation check
		Long vendingMachineId = Long.parseLong(id);
		VendingMachine vendingMachine = vendingMachineServiceImpl.find(vendingMachineId);
		List<VendingMachineHasProduct> vendingMachineProducts = vendingMachineHasProductService.findAllByVendingMachine(vendingMachineId);
		User user = userService.getCurrentUser();

		model.addAttribute(USER, user);
		model.addAttribute(MESSAGE, message);
		model.addAttribute(VENDING_MACHINE, vendingMachine);
		model.addAttribute(VENDING_MACHINE_PRODUCTS, vendingMachineProducts);
		model.addAttribute(IS_SELECTED_VM_PAGE, true);
		return PageConstants.INDEX;
	}

	private void initVendingMachinePage(Model model) {
		List<VendingMachine> vendingMachines = vendingMachineServiceImpl.findAll();
		List<Product> allProducts = productServiceImpl.findAll();

		model.addAttribute(ALL_PRODUCTS, allProducts);
		model.addAttribute(VENDING_MACHINES, vendingMachines);
		model.addAttribute(IS_VENDING_MACHINE_PAGE, true);
	}
}
