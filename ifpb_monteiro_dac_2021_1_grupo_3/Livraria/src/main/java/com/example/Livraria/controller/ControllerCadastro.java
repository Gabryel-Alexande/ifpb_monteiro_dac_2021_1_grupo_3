package com.example.Livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Livraria.dto.UsuarioDTO;
import com.example.Livraria.exeception.CPFException;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.services.UsuarioService;

@Controller
public class ControllerCadastro {
	@Autowired
	private UsuarioService usuarioService;

	private String excecao = "";

	@GetMapping("/cadastro")
	public String cadastro (UsuarioDTO usuario, Model modelo ) {
//		modelo.addAttribute("usuario", new UsuarioDTO());
		
		modelo.addAttribute("excecao", excecao);
		
		excecao="";
		return "/cadastro";
	}

	@PostMapping("/cadastro")
	public String cadastrar(@Valid  UsuarioDTO usuarioDTO,BindingResult result , Model modelo ){

		if(result.hasErrors()) {
			return "/cadastro";
		}
		
		try{
			usuarioService.cadastrarUsuario(usuarioDTO);
			return "redirect:/login";
		
		} catch (Exception e){
			excecao = e.getMessage();
		
		return"redirect:/cadastro";		
		
		
	}
	}
}
