package com.bolshak.vendingmachine.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Getter
@Builder
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private BigInteger price;
	@OneToMany
	private List<VendingMachine> vendingMachines;
	@OneToMany
	private List<User> users;
}
