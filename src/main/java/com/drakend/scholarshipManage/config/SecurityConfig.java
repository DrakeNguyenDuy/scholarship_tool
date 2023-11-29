package com.drakend.scholarshipManage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.BeanIds;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.drakend.scholarshipManage.common.URLCommon;
import com.drakend.scholarshipManage.service.impl.UserDetailServiceImpl;

import lombok.RequiredArgsConstructor;

/**
 * <p>
 * This class have configuration for application
 * </p>
 * 
 * @author Nguyen Duy Long
 * 
 */
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailServiceImpl userDetailServiceImpl;

	@Bean
	public JWTAuthenticationFilter jwtAuthenticationFilter() {
		return new JWTAuthenticationFilter();
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailServiceImpl).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()// block access from domain not allow
				.authorizeRequests()
//				.antMatchers(HttpMethod.POST, URLCommon.createSubAdmin).permitAll()
				.antMatchers(HttpMethod.POST, URLCommon.login).permitAll()
				.anyRequest().authenticated();// all routes must check role
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
