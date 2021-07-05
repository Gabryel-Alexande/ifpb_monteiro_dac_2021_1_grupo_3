package com.example.Livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Livraria.dto.UsuarioDTO;
import com.example.Livraria.services.UsuarioService;

import javassist.NotFoundException;

@Controller
public class ControllerLogin {
	@Autowired
	UsuarioService	usuarioService;
	
	@GetMapping("/login")
	public String login(Model modelo) {
		modelo.addAttribute("loginUser", new UsuarioDTO());
		
		return "login";
	}
	
	@PostMapping("/login")
	public String logar(@ModelAttribute UsuarioDTO usuario) {
		
		
		try {
			usuarioService.logarNoSistema(usuario);
		} catch (NotFoundException e) {
			return "/login";
		}
		
		
		
		return "home";
		
	}
	
	
	
	
	
	
	

}
