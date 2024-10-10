package com.example.demo.finalPractice.security;

import java.util.function.Function;

import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	
	//(UserDetails... users) 
	@Bean 
	public InMemoryUserDetailsManager createUserDetailsManager(){
		//BCryptPasswordEncoder passcodeEncoder1 = null;

		UserDetails userDetails1 = createNewUserName("Akash", "Akash");
		UserDetails userDetails2 = createNewUserName("Shubham", "Shubham");
		UserDetails userDetails3 = createNewUserName("Sai", "Sai");
		return  new InMemoryUserDetailsManager(userDetails1, userDetails2, userDetails3);
		
	}

	private UserDetails createNewUserName(String username, String password) {
		Function<String, String> passwordEncoder = (input) -> passwordEncoder1().encode(input);
		UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder)
				.username(username).password(password)
				.roles("USER", "ADMIN").build();
		return userDetails;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder1() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		http.formLogin(Customizer.withDefaults());
		http.csrf().disable();
		http.headers().frameOptions().disable();
		return http.build();
	}
	public static void main(String[] args) {
		System.out.println("Spring Boot Version: " + SpringBootVersion.getVersion());
	}

}
