package com.bolshak.vendingmachine.service;

import com.bolshak.vendingmachine.model.VendingMachine;

public interface VendingMachineService {

	void create(VendingMachine vendingMachine);

	void update(VendingMachine vendingMachine);

	void delete(VendingMachine vendingMachine);
}
