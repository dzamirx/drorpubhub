//package com.warehouseservice.Service;
//
//import org.aspectj.weaver.ast.And;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter
//{
//
//	/***Enabling authentication with also creating users and roles***/
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception 
//	{
//
//		auth.inMemoryAuthentication()
//		.withUser("dr").password("dr").roles("ADMIN").and()
//		.withUser("nat").password("nat").roles("USER");
//	}
//	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {return NoOpPasswordEncoder.getInstance();}//to hide the password not to show as clear string but encoded form
//
//	
//	/***Enabling authorization with defining which path is necessary for each role login***/
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		http.authorizeRequests()
//		.antMatchers("/warehouse/admin").hasAnyRole("ADMIN")
//		.antMatchers("/warehouse/user").hasAnyRole("USER","ADMIN")
//		.antMatchers("/").permitAll()
//		.and().formLogin();
//	}
//		
//}
