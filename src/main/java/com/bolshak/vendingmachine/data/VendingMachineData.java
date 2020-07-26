package com.bolshak.vendingmachine.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VendingMachineData {
	private Long id;
	private String name;
	private String description;
	private BigDecimal money;
	private int defaultProductCount;
	private List<VendingMachineProductListData> products;
}
