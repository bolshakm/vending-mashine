package com.bolshak.vendingmachine.service.impl;

import com.bolshak.vendingmachine.forms.VendingMachineForm;
import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.model.ProductVendingMachineCompositeId;
import com.bolshak.vendingmachine.model.VendingMachine;
import com.bolshak.vendingmachine.model.VendingMachineHasProduct;
import com.bolshak.vendingmachine.repo.VendingMachineRepo;
import com.bolshak.vendingmachine.service.ProductService;
import com.bolshak.vendingmachine.service.VendingMachineHasProductService;
import com.bolshak.vendingmachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;


@Service
public class VendingMachineServiceImpl implements VendingMachineService {

	@Autowired
	private VendingMachineRepo vendingMachineRepo;

	@Autowired
	private VendingMachineHasProductService vendingMachineHasProductService;

	@Autowired
	private ProductService productServiceImpl;

	@Override
	@Transactional
	public void save(VendingMachineForm form) {
		VendingMachine vendingMachine = vendingMachineRepo.save(buildVendingMachine(form));

		saveProductsForVendingMachine(form, vendingMachine);
	}

	@Override
	public void save(VendingMachine vendingMachine) {
		vendingMachineRepo.save(vendingMachine);
	}

	@Override
	@Transactional
	public void update(VendingMachineForm form) {
		Optional<VendingMachine> vendingMachineForUpdate = vendingMachineRepo.findById(form.getId());

		if (vendingMachineForUpdate.isPresent()) {
			VendingMachine vendingMachine = vendingMachineForUpdate.get();
			vendingMachine.setName(form.getName());
			vendingMachine.setMoney(form.getMoney());
			vendingMachine.setDescription(form.getDescription());
			vendingMachine.setDefaultProductCount(form.getProductCount());
			vendingMachineRepo.save(vendingMachine);
			updateProductsForVendingMachine(form, vendingMachine);
		}
	}

	@Override
	@Transactional
	public void delete(Long id) {
		vendingMachineHasProductService.deleteAllByVendingMachineId(id);
		vendingMachineRepo.deleteById(id);
	}

	@Override
	public List<VendingMachine> findAll() {
		return vendingMachineRepo.findAll();
	}

	@Override
	public VendingMachine find(Long id) {
		return vendingMachineRepo.getOne(id);
	}

	private void updateProductsForVendingMachine(VendingMachineForm form,
			VendingMachine vendingMachine) {
		vendingMachineHasProductService.deleteAllByVendingMachineId(form.getId());
		saveProductsForVendingMachine(form, vendingMachine);
	}

	private void saveProductsForVendingMachine(VendingMachineForm form,
			VendingMachine vendingMachine) {
		List<Product> products = productServiceImpl.findAll(convertProductsId(form.getProductIds()));

		List<VendingMachineHasProduct> vendingMachineHasProductList = products
				.stream()
				.map(product -> VendingMachineHasProduct.builder()
						.id(buildCompositeId(vendingMachine, product))
						.product(product)
						.vendingMachine(vendingMachine)
						.count(form.getProductCount())
						.build())
				.collect(Collectors.toList());

		vendingMachineHasProductService.saveAll(vendingMachineHasProductList);
	}

	private ProductVendingMachineCompositeId buildCompositeId(VendingMachine vendingMachine, Product product) {
		return ProductVendingMachineCompositeId.builder()
				.productId(product.getId())
				.vendingMachineId(vendingMachine.getId())
				.build();
	}

	private VendingMachine buildVendingMachine(VendingMachineForm form) {
		return VendingMachine.builder()
				.name(form.getName())
				.money(form.getMoney())
				.description(form.getDescription())
				.defaultProductCount(form.getProductCount())
				.build();
	}

	private List<Long> convertProductsId(String[] ids) {
		if (isNull(ids)) {
			return Collections.emptyList();
		}
		return Stream.of(ids)
				.map(Long::parseLong)
				.collect(Collectors.toList());
	}
}
