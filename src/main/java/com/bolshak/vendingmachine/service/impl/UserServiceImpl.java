package com.bolshak.vendingmachine.service.impl;

import com.bolshak.vendingmachine.repo.UserRepo;
import com.bolshak.vendingmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepoImpl;

	@Override
	public boolean isExist() {

		return false;
	}
}
