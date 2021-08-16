package com.example.Livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Livraria.dto.PagamentoDTO;
import com.example.Livraria.dto.PesquisaDTO;
import com.example.Livraria.model.Endereco;
import com.example.Livraria.model.Pedido;
import com.example.Livraria.repositorio.MetodoPagamentoRepositorio;
import com.example.Livraria.services.UsuarioService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/livraria/protegido")
public class ControllerCarrinho {
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	MetodoPagamentoRepositorio metodoPagamentoRepositorio;
	
	private String excecao = "";
	
	@GetMapping("/carrinho")
	public String solicitarCarrinho(PesquisaDTO pesquisa ,Model modelo ) {
		
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		Pedido p = usuarioService.listarCarrinhoUsuario(autenticado.getName());
		
		modelo.addAttribute("QuantItens",p.getItemPedido().size());
		modelo.addAttribute("pedido",p);
		
		return "/protected/carrinho";
		
	}
	
//	@PostMapping("/carrinho")
//	public String adicionarNoCarrinho(@RequestParam(name = "id") Long idLivro, Model modelo) {
//		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
//		
//		usuarioService.adcionarAoCarinho(idLivro,1,autenticado.getName());
//		
//		return "redirect:/livraria/publico/home";
//		
//		
//	}
	
	@PostMapping("/carrinho/del")
	public String removerDoCarrinho(@RequestParam(name = "id") Long idItemPedido, Model modelo) {
		
		
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		usuarioService.removerDoCarinho(idItemPedido,autenticado.getName());
		
		return "redirect:/livraria/protegido/carrinho";
	}
	
	
	
	@GetMapping("/finalizarPedido")
	public String socilicitarFinalizarPedido( Model modelo,PagamentoDTO pagamentoDTO) {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		
		Pedido p =  usuarioService.listarCarrinhoUsuario(autenticado.getName());
		
		modelo.addAttribute("Pedido",p);
		
		modelo.addAttribute("QuantItens",p.getItemPedido().size());
		
		modelo.addAttribute("MetodosPag",metodoPagamentoRepositorio.findAll());
		
		Endereco ender =  usuarioService.consultarEndereco(autenticado.getName());
		
		modelo.addAttribute("endereco", ender);
		if(ender.getBairro()==null) {
			modelo.addAttribute("enderecoDisponivel",0);
		}
		else {
			modelo.addAttribute("enderecoDisponivel",1);
		}
		
		if(ender.getBairro()==null || metodoPagamentoRepositorio.findAll().size()==0) {
			modelo.addAttribute("comprar",0);
		}
		else {
			modelo.addAttribute("comprar",1);
		}
		
		modelo.addAttribute("excecao",excecao);
		
		excecao="";
		
		
		
		return "/protected/pedido";
		
		
	}
	
	@PostMapping("/finalizarPedido")
	public String comprarLivros(@Valid PagamentoDTO pagamentoDTO ,BindingResult result) {
		if(result.hasErrors()) {
			return "/protected/pedido";
		}
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		
		try {
			usuarioService.comprarLivro(autenticado.getName(), pagamentoDTO.getNomeDoPagamento());
		} catch (NotFoundException e) {
			excecao = e.getMessage();
			return "redirect:/livraria/protegido/finalizarPedido";
			
		}
		
		
		return "redirect:/livraria/protegido/pedidos";
		
		
		
	}
	

}
