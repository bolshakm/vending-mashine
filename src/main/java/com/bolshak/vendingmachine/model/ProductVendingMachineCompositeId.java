package com.bolshak.vendingmachine.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ProductVendingMachineCompositeId implements Serializable {
	@Column(name = "product_id")
	private Long productId;

	@Column(name = "vending_machines_id")
	private Long vendingMachinesId;

}
