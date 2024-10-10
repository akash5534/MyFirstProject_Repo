package com.example.demo.finalPractice.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WelcomeController {

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String gotoLogin(ModelMap model) {
		model.put("name", getLoggedinUserName());
		return "welcome";
	}
	private String getLoggedinUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	
	
	

}
