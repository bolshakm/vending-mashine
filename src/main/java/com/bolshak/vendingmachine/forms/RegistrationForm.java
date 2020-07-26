package com.bolshak.vendingmachine.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
public class RegistrationForm {
	@Pattern(regexp = "[A-Z0-9]{0,12}")
	private String login;
	@Pattern(regexp = "[A-Z0-9]{0,12}")
	private String password;
	@Pattern(regexp = "[A-Z0-9]{0,12}")
	private String confirmPassword;
	@Pattern(regexp = "[0-9]{0,6}")
	private BigDecimal money;
}
