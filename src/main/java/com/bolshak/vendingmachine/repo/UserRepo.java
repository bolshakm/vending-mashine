package com.bolshak.vendingmachine.repo;

import com.bolshak.vendingmachine.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
