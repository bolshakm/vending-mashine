package com.bolshak.vendingmachine.service.impl;

import com.bolshak.vendingmachine.forms.UserForm;
import com.bolshak.vendingmachine.model.Role;
import com.bolshak.vendingmachine.model.User;
import com.bolshak.vendingmachine.repo.UserRepo;
import com.bolshak.vendingmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepoImpl;

	@Override
	public void save(UserForm form) {
		User user = User.builder()
				.login(form.getLogin())
				.password(form.getPassword())
				.active(true)
				.money(form.getMoney())
				.role(Collections.singleton(Role.USER)).build();
		userRepoImpl.save(user);
	}

	@Override
	public boolean isExist(String login) {
		return Objects.nonNull(userRepoImpl.findUserByLogin(login));
	}
}
