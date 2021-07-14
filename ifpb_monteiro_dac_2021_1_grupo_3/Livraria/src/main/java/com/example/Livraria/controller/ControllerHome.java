package com.example.Livraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/livraria/public")
public class ControllerHome {
	
	@GetMapping("/home")
	public String solicitarHome(Model modelo) {
		
		
		return "/public/home";
	}
}
