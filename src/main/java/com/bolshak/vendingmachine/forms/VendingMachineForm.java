package com.bolshak.vendingmachine.forms;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VendingMachineForm {
	private long id;
	private String name;
	private String description;
	private BigDecimal money;
	private List<Long> productIds;
}
