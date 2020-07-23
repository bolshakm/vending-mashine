package com.bolshak.vendingmachine.forms;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserForm {
	private String login;
	private String password;
	private BigDecimal money;
}
