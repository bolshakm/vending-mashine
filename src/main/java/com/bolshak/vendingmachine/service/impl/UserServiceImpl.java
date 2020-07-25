package com.bolshak.vendingmachine.service.impl;

import com.bolshak.vendingmachine.forms.UserForm;
import com.bolshak.vendingmachine.model.Product;
import com.bolshak.vendingmachine.model.Role;
import com.bolshak.vendingmachine.model.User;
import com.bolshak.vendingmachine.model.VendingMachine;
import com.bolshak.vendingmachine.model.VendingMachineHasProduct;
import com.bolshak.vendingmachine.repo.UserRepo;
import com.bolshak.vendingmachine.service.ProductService;
import com.bolshak.vendingmachine.service.UserService;
import com.bolshak.vendingmachine.service.VendingMachineHasProductService;
import com.bolshak.vendingmachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ProductService productService;

	@Autowired
	private VendingMachineHasProductService vendingMachineHasProductService;

	@Autowired
	private VendingMachineService vendingMachineService;

	@Override
	public void save(UserForm form) {
		User user = User.builder()
				.login(form.getLogin())
				.password(form.getPassword())
				.active(true)
				.money(form.getMoney())
				.role(Collections.singleton(Role.USER)).build();
		userRepo.save(user);
	}

	@Override
	public boolean isExist(String login) {
		return Objects.nonNull(userRepo.findUserByLogin(login));
	}

	@Override
	public boolean hasUserTheProduct(Long productId) {
		Product product = productService.findById(productId);
		return getCurrentUser().getProducts().contains(product);
	}

	@Override
//	@Transactional
	public boolean isBuyingProductSuccessful(Long vendingMachineId, Long productId) {
		User user = getCurrentUser();
		Product product = productService.findById(productId);
		VendingMachineHasProduct vendingMachineHasProduct = vendingMachineHasProductService
				.findByVendingMachineIdAndProductId(vendingMachineId, productId);
		VendingMachine vendingMachine = vendingMachineService.find(vendingMachineId);

		int count = vendingMachineHasProduct.getCount();
		if (count <= 0) {
			return false;
		}

		BigDecimal price = product.getPrice();
		BigDecimal remainingMoney = user.getMoney().subtract(price);
		if (remainingMoney.compareTo(BigDecimal.ZERO) != 1) {
			return false;
		}

		vendingMachine.setMoney(vendingMachine.getMoney().add(price));
		vendingMachineHasProduct.setCount(--count);
		user.getProducts().add(product);
		user.setMoney(remainingMoney);

		userRepo.save(user);
		vendingMachineHasProductService.save(vendingMachineHasProduct);
		vendingMachineService.save(vendingMachine);
		return true;

	}

	@Override
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userRepo.findUserByLogin(authentication.getName());
	}
}
