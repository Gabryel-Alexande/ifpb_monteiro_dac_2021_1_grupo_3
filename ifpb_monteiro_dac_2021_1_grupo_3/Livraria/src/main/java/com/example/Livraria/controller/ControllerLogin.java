package com.example.Livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Livraria.dto.UsuarioDTO;
import com.example.Livraria.services.UsuarioService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/livraria/public")
public class ControllerLogin {
	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/login")
	public String login(UsuarioDTO usuario, Model modelo) {

		return "/public/login";
	}

	@PostMapping("/login")
	public String logar(@ModelAttribute UsuarioDTO usuario) {

		try {
			usuarioService.logarNoSistema(usuario);
		} catch (NotFoundException e) {
			return "livraria/public/login";
		}

		return "/public/home";

	}

}
