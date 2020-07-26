package com.bolshak.vendingmachine.service;

import com.bolshak.vendingmachine.forms.VendingMachineForm;
import com.bolshak.vendingmachine.model.VendingMachine;

import java.util.List;

public interface VendingMachineService {

	void save(VendingMachineForm form);

	void save(VendingMachine vendingMachine);

	void update(VendingMachineForm form);

	void delete(Long id);

	List<VendingMachine> findAll();

	VendingMachine find(Long id);

}
