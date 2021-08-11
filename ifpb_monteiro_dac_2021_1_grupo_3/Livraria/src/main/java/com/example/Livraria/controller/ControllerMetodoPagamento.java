package com.example.Livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Livraria.dto.PagamentoDTO;
import com.example.Livraria.model.MetodoPagamento;
import com.example.Livraria.repositorio.MetodoPagamentoRepositorio;
import com.example.Livraria.services.UsuarioService;

@Controller
@RequestMapping("/livraria/adm")
public class ControllerMetodoPagamento {
	@Autowired
	MetodoPagamentoRepositorio metodoPagamentoRepositorio;
	@Autowired
	UsuarioService usuarioService;
	private Long idMetodo;
	
	@GetMapping("/metodo")
	public String solicitarCriarMetodoPagamento(Model modelo ,PagamentoDTO pagamentoDTO) {
		
		 
		 modelo.addAttribute("metodo",metodoPagamentoRepositorio.findAll());
		 
		 return "/adm/metodo_pagamento";
		 
	}
	
	
	@PostMapping("/criar_metodo")
	public String criarMetodo(PagamentoDTO pagamentoDTO) {
		MetodoPagamento metodo = metodoPagamentoRepositorio.findByNomeDoPagamento(pagamentoDTO.getNomeDoPagamento());
		
		if(metodo!= null) {
			return "/adm/metodo_pagamento";
		}
		MetodoPagamento metodoNovo = new MetodoPagamento();
		metodoNovo.setNomeDoPagamento(pagamentoDTO.getNomeDoPagamento());
		metodoPagamentoRepositorio.save(metodoNovo);
		
		return"redirect:/livraria/adm/metodo";
		
	}
	
	@PostMapping("/deletar_metodo")
	public String deletarMetodo(@RequestParam(name = "id") Long idMetodo) {
		
		if(usuarioService.isPagamentoInPedido(idMetodo)) {
			metodoPagamentoRepositorio.deleteById(idMetodo);
			return "redirect:/livraria/adm/metodo";
		}
		
		return"/adm/metodo_pagamento"; 
		
	}
	
	
	@GetMapping("/editar_metodo")
	public String solicitarEditar(@RequestParam(name = "id") Long idMetodo ,PagamentoDTO pagamentoDTO, Model modelo) {
		
		 this.idMetodo = idMetodo;
		 MetodoPagamento metodo=metodoPagamentoRepositorio.findById(idMetodo).get();
		 
		 pagamentoDTO.setNomeDoPagamento(metodo.getNomeDoPagamento());
		modelo.addAttribute("pagamentoDTO",pagamentoDTO);
		
		return "/adm/editar_metodo_pagamento";
		
	}
	
	
	@PostMapping("/editar_metodo")
	public String solicitarEditar(PagamentoDTO pagamentoDTO, Model modelo) {
		
		MetodoPagamento metodo=metodoPagamentoRepositorio.findByNomeDoPagamento(pagamentoDTO.getNomeDoPagamento());
		
		if (metodo.getNomeDoPagamento().equals(pagamentoDTO.getNomeDoPagamento()) && metodo.getIdMetodoPagamento() != idMetodo) {
            return"/adm/editar_metodo_pagamento";
        }
		
		MetodoPagamento metodoPag=metodoPagamentoRepositorio.findById(idMetodo).get();
		
		metodoPag.setNomeDoPagamento(pagamentoDTO.getNomeDoPagamento());
		metodoPagamentoRepositorio.save(metodoPag);
		
		return "redirect:/livraria/adm/metodo";
		
	}
	
	
}
