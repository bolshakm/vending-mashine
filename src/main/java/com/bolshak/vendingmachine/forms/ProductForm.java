package com.bolshak.vendingmachine.forms;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductForm {
	private long id;
	private String name;
	private BigDecimal price;
}
