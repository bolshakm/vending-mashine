package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.forms.ProductForm;
import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.service.ProductService;
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
import static com.bolshak.vendingmachine.util.ModelAttributesConstants.ERROR;
import static com.bolshak.vendingmachine.util.ModelAttributesConstants.IS_PRODUCT_PAGE;
import static java.lang.String.format;
import static java.util.Objects.isNull;

@Controller()
public class ProductController {
	public static final String PRODUCT_DOES_NOT_EXIST = "Product does not exist";
	public static final String INVALID_DATA_ERROR = "You input invalid data.";

	@Autowired
	private ProductService productService;

	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	@GetMapping("/products")
	public String getProductPage(Model model) {
		initProductPage(model);
		return PageConstants.INDEX;
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@PostMapping("/products/save")
	public String save(ProductForm form, Errors errors, Model model) {
		if (errors.hasErrors()){
			model.addAttribute(ERROR, INVALID_DATA_ERROR);
			return PageConstants.REDIRECT_TO_PRODUCTS;
		}

		if (isNull(form.getId())) {
			productService.create(form);
		} else {
			productService.update(form);
		}
		return PageConstants.REDIRECT_TO_PRODUCTS;
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping("/products/edit")
	public String edit(@RequestParam String id, Model model) {
		Product product = productService.findById(Long.parseLong(id));
		if (isNull(product)) {
			model.addAttribute(ERROR, PRODUCT_DOES_NOT_EXIST);
			return PageConstants.INDEX;
		}
		initProductPage(model);
		model.addAttribute(product);
		return PageConstants.INDEX;
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping("/products/delete")
	public String delete(@RequestParam String id) {
		productService.delete(Long.parseLong(id));
		return PageConstants.REDIRECT_TO_PRODUCTS;
	}

	private void initProductPage(Model model) {
		List<Product> allProducts = productService.findAll();
		model.addAttribute(ALL_PRODUCTS, allProducts);
		model.addAttribute(IS_PRODUCT_PAGE, true);
	}
}
