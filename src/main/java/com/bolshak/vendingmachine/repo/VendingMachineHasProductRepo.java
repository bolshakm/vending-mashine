package com.bolshak.vendingmachine.repo;

import com.bolshak.vendingmachine.model.VendingMachine;
import com.bolshak.vendingmachine.model.VendingMachineHasProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendingMachineHasProductRepo  extends JpaRepository<VendingMachineHasProduct, Long> {
}
