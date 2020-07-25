package com.bolshak.vendingmachine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductVendingMachineCompositeId implements Serializable {
	@Column(name = "product_id")
	private Long productId;

	@Column(name = "vending_machine_id")
	private Long vendingMachineId;

}
