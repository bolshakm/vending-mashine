package com.bolshak.vendingmachine.repo;

import com.bolshak.vendingmachine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
	User findUserByLogin(String login);
}
