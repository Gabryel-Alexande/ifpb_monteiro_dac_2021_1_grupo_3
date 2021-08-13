package com.example.Livraria.controller;

import javax.validation.Valid;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Livraria.dto.EnderecoDTO;
import com.example.Livraria.model.Usuario;
import com.example.Livraria.services.UsuarioService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/livraria/protegido")
public class ControlerEndereco {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/endereco")
	public String criarOuAtualizarEndereco(@Valid EnderecoDTO enderecoDTO , BindingResult result) {
		
		if(result.hasErrors()) {
			return "/protected/endereco";
		}
		
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = null;
		try {
			 usuario = usuarioService.consultarUsuarioPorEmail(autenticado.getName());
		} catch (NotFoundException e) {}
		if (usuario.getEndereco()==null) {
			usuarioService.adcionarEndereco(enderecoDTO, autenticado.getName());
		}
		else {
			usuarioService.editarEndereco(enderecoDTO,autenticado.getName());
		}
		
		
		
		
		return "redirect:/livraria/protegido/finalizarPedido";
	}
	@GetMapping("/endereco")
	public String endereco (EnderecoDTO enderecoDTO, Model modelo) {
		
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		modelo.addAttribute("enderecoDTO", usuarioService.consultarEndereco(autenticado.getName()));
		
		
		return "/protected/endereco";
	}
}
