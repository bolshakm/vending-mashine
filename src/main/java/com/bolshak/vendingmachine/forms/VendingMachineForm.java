package com.bolshak.vendingmachine.forms;

import java.math.BigDecimal;

public class VendingMachineForm {
	private String name;
	private BigDecimal monay;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getMonay() {
		return monay;
	}

	public void setMonay(BigDecimal monay) {
		this.monay = monay;
	}
}
