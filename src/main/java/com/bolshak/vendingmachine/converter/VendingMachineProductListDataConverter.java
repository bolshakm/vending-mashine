package com.bolshak.vendingmachine.converter;

import com.bolshak.vendingmachine.data.VendingMachineProductListData;
import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.model.VendingMachineHasProduct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VendingMachineProductListDataConverter {

	public VendingMachineProductListData convert(VendingMachineHasProduct vendingMachineHasProduct){
		Product product = vendingMachineHasProduct.getProduct();
		return VendingMachineProductListData.builder()
				.id(product.getId())
				.name(product.getName())
				.price(product.getPrice())
				.countInMachine(vendingMachineHasProduct.getCount())
				.build();
	}

	public List<VendingMachineProductListData> convertAll(List<VendingMachineHasProduct> vendingMachineHasProducts){
		return vendingMachineHasProducts.stream()
				.map(this::convert)
				.collect(Collectors.toList());

	}
}
