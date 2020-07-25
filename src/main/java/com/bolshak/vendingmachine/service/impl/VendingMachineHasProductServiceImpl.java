package com.bolshak.vendingmachine.service.impl;

import com.bolshak.vendingmachine.model.VendingMachine;
import com.bolshak.vendingmachine.model.VendingMachineHasProduct;
import com.bolshak.vendingmachine.repo.VendingMachineHasProductRepo;
import com.bolshak.vendingmachine.service.VendingMachineHasProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendingMachineHasProductServiceImpl implements VendingMachineHasProductService {

	@Autowired
	private VendingMachineHasProductRepo vendingMachineHasProductRepo;

	@Override
	public void saveAll(List<VendingMachineHasProduct> vendingMachineHasProducts) {
		vendingMachineHasProductRepo.saveAll(vendingMachineHasProducts);
	}

	@Override
	public void deleteAllByVendingMachineId(Long vendingMachineId) {
		List<VendingMachineHasProduct> allByVendingMachineId =
				findAllByVendingMachineId(vendingMachineId);
		vendingMachineHasProductRepo.deleteAll(allByVendingMachineId);
	}

	private List<VendingMachineHasProduct> findAllByVendingMachineId(Long vendingMachineId) {
		return vendingMachineHasProductRepo.findAll()
				.stream()
				.filter(item -> item.getVendingMachine().getId().equals(vendingMachineId))
				.collect(Collectors.toList());
	}


}
