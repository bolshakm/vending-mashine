package com.bolshak.vendingmachine.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
public class ProductForm {
	@Pattern(regexp = "[0-9]")
	private Long id;
	@Pattern(regexp = "[A-Z0-9]{0,12}")
	private String name;
	@Pattern(regexp = "[0-9]{0,10}")
	private BigDecimal price;
}
