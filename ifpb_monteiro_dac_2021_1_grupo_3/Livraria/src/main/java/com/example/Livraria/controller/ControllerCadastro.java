package com.example.Livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Livraria.dto.UsuarioDTO;
import com.example.Livraria.services.UsuarioService;
@Controller
public class ControllerCadastro {
	@Autowired
	private UsuarioService fachadaUsuario;
	
	@GetMapping("/cadastro")
	public String cadastro () {
		return "cadastro";
	}
	
	
	@PostMapping("/cadastro")
	public String cadastrar(UsuarioDTO usuarioDTO){
		try {
			fachadaUsuario.cadastrarUsuario(usuarioDTO);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		return "login";
		
	}

}
