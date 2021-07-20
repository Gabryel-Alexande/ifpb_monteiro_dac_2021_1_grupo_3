package com.example.Livraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/livraria")
public class ControllerADMHome {
	
	@GetMapping("/homeADM")
	public String solicitarHomeADM(Model modelo) {
		
		return "/protected/homeADM";
		
	}

}
