package com.bolshak.vendingmachine.repo;

import com.bolshak.vendingmachine.model.VendingMachineHasProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendingMachineHasProductRepo  extends JpaRepository<VendingMachineHasProduct, Long> {
	@Query("from VendingMachineHasProduct pvm where pvm.vendingMachine.id = :id")
	List<VendingMachineHasProduct> findAllByVendingMachineId(@Param("id") Long id);

	@Query("from VendingMachineHasProduct pvm where pvm.vendingMachine.id = :vmId and pvm.product.id = :productId")
	VendingMachineHasProduct findAllByVendingMachineIdAndProductId(@Param("vmId") Long vmId, @Param("productId") Long productId);

	@Query("delete from VendingMachineHasProduct where product.id =:id")
	void deleteByProductId(@Param("id") Long id);
}
