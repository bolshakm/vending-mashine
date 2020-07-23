package com.bolshak.vendingmachine.service;


import com.bolshak.vendingmachine.forms.UserForm;

public interface UserService {
	void save(UserForm form);
	boolean isExist(String login);
}
