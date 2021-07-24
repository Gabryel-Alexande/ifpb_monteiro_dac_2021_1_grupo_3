package com.example.Livraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/livraria/protegido")
public class ControllerCarrinho {
	
	
	@GetMapping("/carrinho")
	public String solicitarCarrinho() {
		return "/protected/carrinho";
		
	}
	

}
