package com.bolshak.vendingmachine.service;

import com.bolshak.vendingmachine.model.VendingMachineHasProduct;

import java.util.List;

public interface VendingMachineHasProductService {

	void save(VendingMachineHasProduct vendingMachineHasProduct);

	void saveAll(List<VendingMachineHasProduct> vendingMachineHasProducts);

	void deleteAllByVendingMachineId(Long vendingMachineId);

	List<VendingMachineHasProduct> findAllByVendingMachine(Long id);

	VendingMachineHasProduct findByVendingMachineIdAndProductId(Long vendingMachineId,
			Long productId);
}
