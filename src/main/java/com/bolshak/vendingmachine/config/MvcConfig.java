package com.bolshak.vendingmachine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/index").setViewName("hello");
//		registry.addViewController("/").setViewName("hello");
//		registry.addViewController("/index").setViewName("index");
//		registry.addViewController("/registration").setViewName("registration");
		registry.addViewController("/login").setViewName("login");
	}

}
