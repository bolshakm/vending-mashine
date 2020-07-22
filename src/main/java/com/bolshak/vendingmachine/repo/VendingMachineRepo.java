package com.bolshak.vendingmachine.repo;

import com.bolshak.vendingmachine.model.VendingMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendingMachineRepo extends JpaRepository<VendingMachine, Long> {
}
