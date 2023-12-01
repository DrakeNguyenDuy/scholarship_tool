package com.drakend.scholarshipManage.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource({ "classpath:application-${envTarget:dev}.properties" })
public class ApplicationConfigBeans {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
