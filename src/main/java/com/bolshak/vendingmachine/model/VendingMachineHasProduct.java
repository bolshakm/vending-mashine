package com.bolshak.vendingmachine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VendingMachineHasProduct implements Serializable {
	@EmbeddedId
	private ProductVendingMachineCompositeId id;

	@ManyToOne()
	@MapsId("product_id")
	private Product product;

	@ManyToOne()
	@MapsId("vending_machines_id")
	private VendingMachine vendingMachine;

	private int count;
}
