package com.example.Livraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ControllerHome {
	
	@GetMapping("/home")
	public String homear(Model modelo) {
		
		
		return "home";
	}
}
