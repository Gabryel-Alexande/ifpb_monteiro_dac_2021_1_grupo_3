package com.example.Livraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Livraria.dto.LivroDTO;

@Controller
@RequestMapping("/livraria/public")
public class ControllerLivro {
	
	@GetMapping("/livro")
	public String solicitarLivro(LivroDTO livro) {
		
		return "/public/livro";
		
	}

}
