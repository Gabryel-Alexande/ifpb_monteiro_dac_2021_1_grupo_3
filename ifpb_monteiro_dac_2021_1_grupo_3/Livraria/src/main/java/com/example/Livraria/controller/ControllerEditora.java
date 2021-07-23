package com.example.Livraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerEditora {
	
	@GetMapping("/editora")
	public String solicitarEdidora() {
		return "/protected/cadastroeditora";
	}

}
