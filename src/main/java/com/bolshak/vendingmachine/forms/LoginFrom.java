package com.bolshak.vendingmachine.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class LoginFrom {
	@Pattern(regexp = "[A-Z0-9]{0,12}")
	private String login;
	@Pattern(regexp = "[A-Z0-9]{0,12}")
	private String password;
}
