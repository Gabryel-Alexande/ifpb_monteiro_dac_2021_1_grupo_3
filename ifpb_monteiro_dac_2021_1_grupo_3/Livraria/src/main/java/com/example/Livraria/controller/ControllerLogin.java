package com.example.Livraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerLogin {
	
	
	@GetMapping("/login")
	public String Login() {
		return "login";
	}
	
	
	
	

}
