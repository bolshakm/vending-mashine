package com.bolshak.vendingmachine.forms;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VendingMachineForm {
	private Long id;
	private String name;
	private String description;
	private BigDecimal money;
	private int productCount;
	private String [] productIds;
}
