package com.bolshak.vendingmachine.controller;

import com.bolshak.vendingmachine.converter.ProductDataConverter;
import com.bolshak.vendingmachine.converter.VendingMachineDataConverter;
import com.bolshak.vendingmachine.data.ProductData;
import com.bolshak.vendingmachine.data.VendingMachineData;
import com.bolshak.vendingmachine.forms.VendingMachineForm;
import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.model.VendingMachine;
import com.bolshak.vendingmachine.service.ProductService;
import com.bolshak.vendingmachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ExternalController {

	@Autowired
	private VendingMachineService vendingMachineService;
	@Autowired
	private VendingMachineDataConverter vendingMachineDataConverter;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductDataConverter productDataConverter;

	@GetMapping(value = "/get/all/vending-machines", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VendingMachineData>> getVendingMachineData() {
		List<VendingMachine> allVendingMachines = vendingMachineService.findAll();
		if (allVendingMachines.isEmpty()) {
			return ResponseEntity.badRequest().body(null);
		}

		List<VendingMachineData> allData =
				vendingMachineDataConverter.convertAll(allVendingMachines);
		return ResponseEntity.ok(allData);
	}

	@GetMapping(value = "/get/all/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductData>> getProductData() {
		List<Product> allProducts = productService.findAll();
		if (allProducts.isEmpty()) {
			return ResponseEntity.badRequest().body(null);
		}

		List<ProductData> allData = productDataConverter.convertAll(allProducts);
		return ResponseEntity.ok(allData);
	}

	//todo remove after demonstrate
	@GetMapping(value = "/token")
	public ResponseEntity<Object> save(HttpServletRequest request) {
		return ResponseEntity.ok(request.getAttribute("_csrf"));
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VendingMachineData>> save(@RequestBody VendingMachineForm form) {

		vendingMachineService.save(form);

		List<VendingMachine> allVendingMachines = vendingMachineService.findAll();
		if (allVendingMachines.isEmpty()) {
			return ResponseEntity.badRequest().body(null);
		}

		List<VendingMachineData> allData = vendingMachineDataConverter.convertAll(allVendingMachines);
		return ResponseEntity.ok(allData);
	}

}
