package com.bolshak.vendingmachine.service;


import com.bolshak.vendingmachine.forms.UserForm;
import com.bolshak.vendingmachine.model.User;

public interface UserService {
	void save(UserForm form);

	boolean isExist(String login);

	boolean hasUserTheProduct(Long productId);

	boolean isBuyingProductSuccessful(Long vendingMachineId, Long productId);

	User getCurrentUser();
}
