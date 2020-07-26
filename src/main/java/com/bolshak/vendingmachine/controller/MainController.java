package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.forms.UserForm;
import com.bolshak.vendingmachine.model.VendingMachine;
import com.bolshak.vendingmachine.service.UserService;
import com.bolshak.vendingmachine.service.VendingMachineService;
import com.bolshak.vendingmachine.util.PageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.bolshak.vendingmachine.util.ModelAttributesConstants.ERROR;
import static com.bolshak.vendingmachine.util.ModelAttributesConstants.IS_INDEX_PAGE;
import static com.bolshak.vendingmachine.util.ModelAttributesConstants.VENDING_MACHINES;

@Controller()
public class MainController {

	@Autowired
	private VendingMachineService vendingMachineServiceImpl;

	@Autowired
	private UserService userServiceImpl;

	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	@GetMapping("/index")
	public String getMainPage(Model model) {
		List<VendingMachine> vendingMachines = vendingMachineServiceImpl.findAll();
		model.addAttribute(VENDING_MACHINES, vendingMachines);
		model.addAttribute(IS_INDEX_PAGE, true);
		return PageConstants.INDEX;
	}

	@GetMapping("/registration")
	public String getRegistrationPage() {
		return PageConstants.REGISTRATION;
	}

	@PostMapping(value = "/registration", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String registration(UserForm form, Model model) {
		if (userServiceImpl.isExist(form.getLogin())) {
			model.addAttribute(ERROR, true);
			return PageConstants.REGISTRATION;
		}
		userServiceImpl.save(form);
		return PageConstants.LOGIN;
	}
}
