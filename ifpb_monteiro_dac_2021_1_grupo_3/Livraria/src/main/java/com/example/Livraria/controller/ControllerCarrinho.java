package com.example.Livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Livraria.dto.PesquisaDTO;
import com.example.Livraria.services.UsuarioService;

@Controller
@RequestMapping("/livraria/protegido")
public class ControllerCarrinho {
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/carrinho")
	public String solicitarCarrinho(PesquisaDTO pesquisa ,Model modelo ) {
		
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		
		
		
		modelo.addAttribute("pedido",usuarioService.listarCarrinhoUsuario(autenticado.getName()));
		
		return "/protected/carrinho";
		
	}
	
	@PostMapping("/carrinho")
	public String adicionarNoCarrinho(@RequestParam(name = "id") Long idLivro, Model modelo) {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		
		usuarioService.adcionarAoCarinho(idLivro,1,autenticado.getName());
		
		return "redirect:/livraria/publico/home";
		
		
	}
	
	@PostMapping("/carrinho/del")
	public String removerDoCarrinho(@RequestParam(name = "id") Long idItemPedido, Model modelo) {
		
		
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		usuarioService.removerDoCarinho(idItemPedido,autenticado.getName());
		
		return "redirect:/livraria/protegido/carrinho";
	}
	
	
//	
//	@GetMapping("/finalizarPedido")
//	public String socilicitarFinalizarPedido() {
//		
//	}
	

}
