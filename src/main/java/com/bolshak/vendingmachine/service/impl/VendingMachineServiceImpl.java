package com.bolshak.vendingmachine.service.impl;

import com.bolshak.vendingmachine.forms.VendingMachineForm;
import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.model.ProductVendingMachineCompositeId;
import com.bolshak.vendingmachine.model.VendingMachine;
import com.bolshak.vendingmachine.model.VendingMachineHasProduct;
import com.bolshak.vendingmachine.repo.VendingMachineHasProductRepo;
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

	private static final int DEFAULT_PRODUCT_COUNT = 10;
	@Autowired
	private VendingMachineRepo vendingMachineRepo;

	@Autowired
	private VendingMachineHasProductService vendingMachineHasProductService;

	@Autowired
	private ProductService productServiceImpl;

	@Override
//	@Transactional
	public void save(VendingMachineForm form) {
		List<Product> products =
				productServiceImpl.findAll(convertProductsId(form.getProductIds()));

		VendingMachine vendingMachine = vendingMachineRepo.save(buildVendingMachine(form));
		System.out.println(vendingMachine.getId());
		List<VendingMachineHasProduct> vendingMachineHasProductList = products
				.stream()
				.map(product -> VendingMachineHasProduct.builder()
						.id(ProductVendingMachineCompositeId.builder()
								.productId(product.getId())
								.vendingMachineId(vendingMachine.getId())
								.build())
						.product(product)
						.vendingMachine(vendingMachine)
						.count(form.getProductCount())
						.build())
				.collect(Collectors.toList());

		vendingMachineHasProductService.saveAll(vendingMachineHasProductList);
	}

	private VendingMachine buildVendingMachine(VendingMachineForm form) {
		return VendingMachine.builder()
					.name(form.getName())
					.money(form.getMoney())
					.description(form.getDescription())
					.build();
	}

	@Override
	public void update(VendingMachineForm form) {
		Optional<VendingMachine> vendingMachineForUpdate =
				vendingMachineRepo.findById(form.getId());

		if (vendingMachineForUpdate.isPresent()) {
			VendingMachine vendingMachine = vendingMachineForUpdate.get();
			vendingMachine.setName(form.getName());
			vendingMachine.setMoney(form.getMoney());
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

	private List<Long> convertProductsId(String[] ids) {
		if (isNull(ids)){
			return Collections.emptyList();
		}
		return Stream.of(ids)
				.map(Long::parseLong)
				.collect(Collectors.toList());
	}
}
