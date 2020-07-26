package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.service.ProductService;
import com.bolshak.vendingmachine.service.UserService;
import com.bolshak.vendingmachine.util.PageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.bolshak.vendingmachine.util.ModelAttributesConstants.ALL_PRODUCTS;
import static com.bolshak.vendingmachine.util.ModelAttributesConstants.IS_PRODUCT_PAGE;
import static com.bolshak.vendingmachine.util.ModelAttributesConstants.IS_USER_PAGE;
import static java.lang.String.format;

@Controller
public class UserController {
	public static final String USER_HAS_PRODUCT_ERROR =
			"Sorry but, you have this product. Use this product for buying again.";
	public static final String BUYING_PRPDUCT_ERROR =
			"Sorry but, you haven't enough  money or product is absent.";

	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;

	@GetMapping("/users-product")
	public String getUserPage(Model model) {
		model.addAttribute(ALL_PRODUCTS, userService.getCurrentUser().getProducts());
		model.addAttribute(IS_USER_PAGE, true);
		return PageConstants.INDEX;
	}

	@GetMapping("/users-product/use")
	public String use(Model model, @RequestParam String id) {
		userService.useProduct(Long.parseLong(id));
		return PageConstants.REDIRECT_TO_USERS_PRODUCT;
	}

	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	@GetMapping("/users-product/buy")
	public String buy(@RequestParam String productId, @RequestParam String vmId, Model model) {

		Long idByProduct = Long.parseLong(productId);
		Long idByVendingMachine = Long.parseLong(vmId);

		if (userService.hasUserTheProduct(idByProduct)) {
			return format(PageConstants.REDIRECT_TO_SELECT_VM_WITH_MESSAGE, vmId, USER_HAS_PRODUCT_ERROR);
		}

		boolean isBuyingProductSuccessful = userService
				.isBuyingProductSuccessful(idByVendingMachine, idByProduct);

		if (!isBuyingProductSuccessful) {
			return format(PageConstants.REDIRECT_TO_SELECT_VM_WITH_MESSAGE, vmId, BUYING_PRPDUCT_ERROR);
		}

		List<Product> allProducts = productService.findAll();
		model.addAttribute(ALL_PRODUCTS, allProducts);
		model.addAttribute(IS_PRODUCT_PAGE, true);
		return format(PageConstants.REDIRECT_TO_SELECT_VM_WITHOUT_MESSAGE, vmId);
	}
}
