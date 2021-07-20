package com.example.Livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Livraria.services.LivroService;



@Controller
@RequestMapping("/livraria")
public class ControllerHome {
	
	@Autowired
	private LivroService livroService;
	
	@GetMapping("/home")
	public String solicitarHome(Model modelo) {	
		   
		modelo.addAttribute("livros",livroService.listarLivros());
		
		
		
		return "/public/home";
	
	}
	
	
	
	
	
}
