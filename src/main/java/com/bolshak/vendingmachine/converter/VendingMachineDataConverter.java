package com.bolshak.vendingmachine.converter;

import com.bolshak.vendingmachine.data.VendingMachineData;
import com.bolshak.vendingmachine.model.VendingMachine;
import com.bolshak.vendingmachine.service.VendingMachineHasProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VendingMachineDataConverter {

	@Autowired
	private VendingMachineProductListDataConverter vendingMachineProductListDataConverter;
	@Autowired
	private VendingMachineHasProductService vendingMachineHasProductService;

	public VendingMachineData convert(VendingMachine vendingMachine){
		Long id = vendingMachine.getId();

		return VendingMachineData.builder()
				.id(id)
				.name(vendingMachine.getName())
				.description(vendingMachine.getDescription())
				.money(vendingMachine.getMoney())
				.defaultProductCount(vendingMachine.getDefaultProductCount())
				.products(vendingMachineProductListDataConverter.convertAll(vendingMachineHasProductService.findAllByVendingMachine(id)))
				.build();
	}

	public List<VendingMachineData> convertAll(List<VendingMachine> vendingMachineList){
		return vendingMachineList.stream()
				.map(this::convert)
				.collect(Collectors.toList());
	}
}
