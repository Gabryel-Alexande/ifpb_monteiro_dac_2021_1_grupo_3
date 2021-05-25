package com.example.Livraria.controllers;

import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.fachada.FachadaAutor;

public class ControllerAutor {
	
	private FachadaAutor fachadaAutor = new FachadaAutor();
	
	public void cadastrarAutor(String nomeAutor, String email, String senha)
			throws LoginException {
		fachadaAutor.cadastrarAutor(nomeAutor, email, senha);
	}

	public void alterarAutor(Long id, String nomeAutor, String senha)
			throws LoginException {
		fachadaAutor.alterarAutor(id, nomeAutor, senha);
	}
	public Object[] listarAutores(){
		return fachadaAutor.listarAutores().toArray();
	}
}
