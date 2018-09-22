package com.mcm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mcm.entities.service.MyAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyAuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/device/**").permitAll()
		.antMatchers("/customer.json").access("hasRole('ROLE_CUSTOMER')")
		.antMatchers("/customer/**").access("hasRole('ROLE_CUSTOMER')")
		.antMatchers("/developer.json").access("hasRole('ROLE_DEVELOPER')")
		.antMatchers("/developer/**").access("hasRole('ROLE_DEVELOPER')")
		.antMatchers("/admin.json").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/manufacturer.json").access("hasRole('ROLE_MANUFACTURER')")
		.antMatchers("/manufacturer/**").access("hasRole('ROLE_MANUFACTURER')")
		.and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").successHandler(authenticationSuccessHandler)
		.and().logout().logoutSuccessUrl("/login?logout")
		.and().exceptionHandling().accessDeniedPage("/403")
		.and().csrf().disable();

	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
}