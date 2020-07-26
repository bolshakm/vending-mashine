package com.bolshak.vendingmachine.converter;

import com.bolshak.vendingmachine.data.ProductData;
import com.bolshak.vendingmachine.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDataConverter {

	public ProductData convert(Product product){

		return ProductData.builder()
				.id(product.getId())
				.name(product.getName())
				.price(product.getPrice())
				.build();
	}

	public List<ProductData> convertAll(List<Product> productList){
		return productList.stream()
				.map(this::convert)
				.collect(Collectors.toList());
	}
}
