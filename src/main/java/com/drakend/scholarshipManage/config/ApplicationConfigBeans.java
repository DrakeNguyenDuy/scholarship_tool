package com.drakend.scholarshipManage.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfigBeans {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
