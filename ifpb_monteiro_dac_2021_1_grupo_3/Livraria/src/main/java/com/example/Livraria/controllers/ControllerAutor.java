package com.example.Livraria.controllers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.fachada.FachadaAutor;
@Controller
public class ControllerAutor {
	@Autowired
	private FachadaAutor fachadaAutor;
	
	public void cadastrarAutor(String nomeAutor, String email, String senha)
			throws LoginException {
		fachadaAutor.cadastrarAutor(nomeAutor, email, senha);
	}

	public void alterarAutor(Long id, String nomeAutor, String senha) throws NoSuchElementException,
			LoginException {
		fachadaAutor.alterarAutor(id, nomeAutor, senha);
	}
	public Object[] listarAutores(){
		return fachadaAutor.listarAutores().toArray();
	}
}
