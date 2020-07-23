package com.bolshak.vendingmachine.service.impl;

import com.bolshak.vendingmachine.forms.VendingMachineForm;
import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.model.VendingMachine;
import com.bolshak.vendingmachine.repo.VendingMachineRepo;
import com.bolshak.vendingmachine.service.ProductService;
import com.bolshak.vendingmachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VendingMachineServiceImpl implements VendingMachineService {

	@Autowired
	private VendingMachineRepo vendingMachineRepoImpl;

	@Autowired
	private ProductService productServiceImpl;

	@Override
	public void save(VendingMachineForm form) {
		List<Product> products = productServiceImpl.findAll(form.getProductIds());
		VendingMachine vendingMachine = VendingMachine.builder()
				.name(form.getName())
				.money(form.getMoney())
				.description(form.getDescription())
				.products(products).build();
		vendingMachineRepoImpl.save(vendingMachine);
	}

	@Override
	public void update(VendingMachineForm form) {
		Optional<VendingMachine> vendingMachineForUpdate = vendingMachineRepoImpl.findById(form.getId());
		if (vendingMachineForUpdate.isPresent()){
			VendingMachine vendingMachine = vendingMachineForUpdate.get();
			vendingMachine.setName(form.getName());
			vendingMachine.setMoney(form.getMoney());
			vendingMachine.setProducts(productServiceImpl.findAll(form.getProductIds()));
		}
	}

	@Override
	public void delete(VendingMachineForm form) {
		vendingMachineRepoImpl.deleteById(form.getId());
	}

	@Override
	public List<VendingMachine> findAll() {
		return vendingMachineRepoImpl.findAll();
	}
}
