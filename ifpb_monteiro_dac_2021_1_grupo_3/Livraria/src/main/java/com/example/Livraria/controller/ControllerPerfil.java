package com.example.Livraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/livraria")
public class ControllerPerfil {
	
	@GetMapping("/publico/perfil")
	public String solicitarPerfil() {
		
		return "/public/perfil_usuario";
		
	}
	
	@GetMapping("/protegido/pedidos")
	public String socilitarPedidos() {
		
		return "/protected/pedidos";
		
	}
	
	@GetMapping("protegido/editar_usuario")
	public String solicitarEditarUsuario() {
		return "/protected/editar_usuario";
	}

}
