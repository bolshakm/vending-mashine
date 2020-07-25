package com.bolshak.vendingmachine.service;

import com.bolshak.vendingmachine.model.VendingMachineHasProduct;

import java.util.List;

public interface VendingMachineHasProductService {

	void saveAll(List<VendingMachineHasProduct> vendingMachineHasProducts);

	void deleteAllByVendingMachineId(Long vendingMachineId);
}
