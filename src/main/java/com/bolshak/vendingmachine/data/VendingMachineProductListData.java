package com.bolshak.vendingmachine.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VendingMachineProductListData {
	private Long id;
	private String name;
	private BigDecimal price;
	private int countInMachine;
}
