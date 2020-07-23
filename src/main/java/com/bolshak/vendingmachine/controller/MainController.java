package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.forms.UserForm;
import com.bolshak.vendingmachine.service.UserService;
import com.bolshak.vendingmachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class MainController {

	@Autowired
	private VendingMachineService vendingMachineServiceImpl;

	@Autowired
	private UserService userServiceImpl;


	@GetMapping("/index")
	public String getMainPage(Model model) {
//		List<VendingMachine> vendingMachines = vendingMachineService.findAll();
//		model.addAttribute(vendingMachines);
//		userServiceImpl.
		return "index";
	}

	@GetMapping("/create/vending-machine")
	public String createVendingMachine() {
		return "";
	}

	@GetMapping("/registration")
	public String getRegistrationPage() {
		System.out.println("test");
		return "registration";
	}

	@PostMapping(value = "/registration", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String registration(UserForm form, Model model){
		if (userServiceImpl.isExist(form.getLogin())){
			model.addAttribute("errorMessage", " Sorry this login is already used");
		} else {
			userServiceImpl.save(form);
		}
		return "login";
	}

	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

}
