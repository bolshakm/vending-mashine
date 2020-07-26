package com.bolshak.vendingmachine.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
public class VendingMachineForm {
	@Pattern(regexp = "[A-Z]{0,12}")
	private Long id;
	@Pattern(regexp = "[A-Z0-9]{0,12}")
	private String name;
	@Pattern(regexp = "[A-Z0-9.,!?:;_()/]{0,100}")
	private String description;
	@Pattern(regexp = "[0-9]{0,10}")
	private BigDecimal money;
	@Pattern(regexp = "[0-9]{0,12}")
	private int productCount;
	@Pattern(regexp = "[0-9]{0,12}")
	private String [] productIds;
}
