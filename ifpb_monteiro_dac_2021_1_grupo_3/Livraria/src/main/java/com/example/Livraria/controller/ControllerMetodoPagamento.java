package com.example.Livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Livraria.dto.PagamentoDTO;
import com.example.Livraria.model.MetodoPagamento;
import com.example.Livraria.repositorio.MetodoPagamentoRepositorio;
import com.example.Livraria.services.PagamentoService;
import com.example.Livraria.services.UsuarioService;

@RestController
@RequestMapping("/pagamentos")
public class ControllerMetodoPagamento {

	@Autowired
	MetodoPagamentoRepositorio metodoPagamentoRepositorio;
	@Autowired
	UsuarioService usuarioService;

	@Autowired
	PagamentoService pagamentoService;

	@GetMapping("/metodo")
	public List<MetodoPagamento> solicitarCriarMetodoPagamento() {

		return metodoPagamentoRepositorio.findAll();

	}

	@PostMapping("/criar_metodo")
	@ResponseStatus(code = HttpStatus.CREATED, reason = "Metodo Criado com Sucesso !!")
	public void criarMetodo(@RequestBody MetodoPagamento metodoPagamento) {

		try {
			pagamentoService.criarMetodoPagamento(metodoPagamento.getNomeDoPagamento());
			System.out.println("METODO CRIADO COM SUCESSO !!!");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

		}

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK, reason = "METODO DELETADO COM SUCESSO !!")
	public void deletarMetodo(@PathVariable Long id) {
		try {
			pagamentoService.deletarMetodoPagamento(id);
		} catch (DuplicateKeyException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@PutMapping("/editar-metodo/{id}")
	@ResponseStatus(code = HttpStatus.OK,reason = "METODO EDITADO COM SUCESSO !!")
	public void solicitarEditar(@PathVariable(name = "id") Long idMetodo ,@RequestBody MetodoPagamento metodoPagamento) {
		
		try {
			
			pagamentoService.editarMetodoPagamento(idMetodo, metodoPagamento);
		}catch (DuplicateKeyException e) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST,e.getMessage()
					);
		}
		
		
		
	
		
	}
	
	@GetMapping("/editar-metodo/{id}")
	public MetodoPagamento resgatarMetodo(@PathVariable(name = "id") Long idMetodo) {
		return pagamentoService.encontarMetodo(idMetodo);
	}



}
