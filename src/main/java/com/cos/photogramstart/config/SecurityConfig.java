package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); 
		http.authorizeRequests()
	         .antMatchers("/", "/user/**", "/subscribe/**", "/comment/**", "/image/**").authenticated()
	         .anyRequest().permitAll()
	         .and()
	    .formLogin()
			.loginPage("/auth/signin")
			.loginProcessingUrl("/auth/signin")//이런 URL 요청이 들어오면 로그인 진행
			.defaultSuccessUrl("/")
		;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}