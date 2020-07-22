package com.bolshak.vendingmachine.repo;

import com.bolshak.vendingmachine.model.VendingMachine;
import org.springframework.data.repository.CrudRepository;

public interface VendingMachineRepo extends CrudRepository<VendingMachine, Long> {
}
