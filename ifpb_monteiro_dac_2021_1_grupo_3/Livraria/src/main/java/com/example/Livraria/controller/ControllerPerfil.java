package com.example.Livraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/livraria/publico")
public class ControllerPerfil {
	
	@GetMapping("/perfil")
	public String solicitarPerfil() {
		
		return "/public/perfil_usuario";
		
	}

}
