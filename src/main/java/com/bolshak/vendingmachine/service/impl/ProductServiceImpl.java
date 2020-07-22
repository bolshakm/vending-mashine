package com.bolshak.vendingmachine.service.impl;

import com.bolshak.vendingmachine.forms.ProductForm;
import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.repo.ProductRepo;
import com.bolshak.vendingmachine.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Override
	public void create(ProductForm form) {
		Product product = Product.builder()
				.name(form.getName())
				.price(form.getPrice())
				.build();

		productRepo.save(product);
	}

	@Override
	public void update(ProductForm form) {
		Optional<Product> productFromUpdating = productRepo.findById(form.getId());
		if (productFromUpdating.isPresent()) {
			Product product = productFromUpdating.get();
			form.setName(product.getName());
			form.setPrice(product.getPrice());
			productRepo.save(product);
		}

	}

	@Override
	public void delete(ProductForm form) {
		productRepo.deleteById(form.getId());
	}

	@Override
	public List<Product> findAll() {
		return productRepo.findAll();
	}

	@Override
	public List<Product> findAll(List<Long> ids) {
		return productRepo.findAllById(ids);
	}
}
