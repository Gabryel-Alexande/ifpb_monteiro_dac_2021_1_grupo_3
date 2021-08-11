package com.example.Livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Livraria.dto.EnderecoDTO;
import com.example.Livraria.services.UsuarioService;

@Controller
@RequestMapping("/livraria/protegido")
public class ControlerEnedereco {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/endereco")
	public String criarEnndereco(EnderecoDTO enderecoDTO) {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();

		usuarioService.adcionarEndereco(enderecoDTO, autenticado.getName());
		return "/endereco";
	}
	@GetMapping("/endereco")
	public String endereco (EnderecoDTO enderecoDTO) {
		return "/protected/endereco";
	}
}
