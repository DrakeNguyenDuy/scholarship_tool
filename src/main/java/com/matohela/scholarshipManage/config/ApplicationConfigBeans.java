package com.matohela.scholarshipManage.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>This class contain all bean configuration</p>
 * @author NguyenDuyLong2810
 * 
 */
@Configuration
//@PropertySource({ "classpath:application-${envTarget:dev}.properties" })
public class ApplicationConfigBeans {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
