package com.bolshak.vendingmachine.service.impl;

import com.bolshak.vendingmachine.forms.ProductForm;
import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.repo.ProductRepo;
import com.bolshak.vendingmachine.service.ProductService;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepoImpl;

	@Override
	public void create(ProductForm form) {
		Product product = Product.builder()
				.name(form.getName())
				.price(form.getPrice())
				.build();

		productRepoImpl.save(product);
	}

	@Override
	public void update(ProductForm form) {
		Optional<Product> productFromUpdating = productRepoImpl.findById(form.getId());
		if (productFromUpdating.isPresent()) {
			Product product = productFromUpdating.get();
			product.setName(form.getName());
			product.setPrice(form.getPrice());
			productRepoImpl.save(product);
		}

	}

	@Override
	public void delete(Long id) {
		productRepoImpl.deleteById(id);
	}

	@Override
	public List<Product> findAll() {
		return productRepoImpl.findAll();
	}

	@Nullable
	@Override
	public Product findById(Long id) {
		Optional<Product> result = productRepoImpl.findById(id);
		return result.orElse(null);
	}

	@Override
	public List<Product> findAll(List<Long> ids) {
		return productRepoImpl.findAllById(ids);
	}
}
