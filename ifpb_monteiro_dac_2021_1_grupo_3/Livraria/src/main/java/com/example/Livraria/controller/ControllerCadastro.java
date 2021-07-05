package com.example.Livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Livraria.dto.UsuarioDTO;
import com.example.Livraria.services.UsuarioService;
@Controller
public class ControllerCadastro {
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/cadastro")
	public String cadastro (Model modelo ) {
		modelo.addAttribute("usuario", new UsuarioDTO());
		
		
		return "cadastro";
	}
	
	
	@PostMapping("/cadastro")
	public String cadastrar(@ModelAttribute UsuarioDTO usuarioDTO){
		
		try {
			usuarioService.cadastrarUsuario(usuarioDTO);
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		
		
		
		return "redirect:/login";
		
	}

}
