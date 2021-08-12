package com.example.Livraria.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Livraria.dto.PesquisaDTO;
import com.example.Livraria.dto.UsuarioDTO;
import com.example.Livraria.exeception.CPFException;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.model.Pedido;
import com.example.Livraria.services.UsuarioService;


import javassist.NotFoundException;

@Controller
@RequestMapping("/livraria")
public class ControllerPerfil {
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	
	
	@GetMapping("/publico/perfil")
	public String solicitarPerfil() {
		
		return "/public/perfil_usuario";
		
	}
	
	@GetMapping("/protegido/pedidos")
	public String socilitarPedidos(Model model ,PesquisaDTO pesquisa ) {
		
		
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		
		List<Pedido> p = usuarioService.listarPedidosUsuario(autenticado.getName());
		
		model.addAttribute("pedidos",p);
		model.addAttribute("Quant",p.size());
		
		return "/protected/pedidos";
		
	}
	
	@GetMapping("/protegido/editar_usuario")
	public String solicitarEditarUsuario(UsuarioDTO usuario, Model modelo) {
		
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		
		
		try {
			modelo.addAttribute("usuarioDTO",usuarioService.consultarUsuarioPorEmail(autenticado.getName()));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "/protected/editar_usuario";
	}
	
	@PostMapping("/protegido/editar_usuario")
	public String editarUsuario(@Valid UsuarioDTO usuarioDTO ,BindingResult result,Model modelo) {
		
		if(result.hasErrors()) {
			return"/protected/editar_usuario";
		}
		try {
			usuarioService.alteraUsuario(usuarioDTO);
		} catch (CPFException | LoginException e) {
			System.out.println("Erro aqui");
			
			return"/protected/editar_usuario";
		}
		
		return "redirect:/login";
		
	}
	
}
