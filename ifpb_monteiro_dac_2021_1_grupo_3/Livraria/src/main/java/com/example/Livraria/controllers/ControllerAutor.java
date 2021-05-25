package com.example.Livraria.controllers;

import java.util.List;

import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.fachada.FachadaAutor;
import com.example.Livraria.model.Livro;

public class ControllerAutor {
	private FachadaAutor fachadaAutor;
	
	public void cadastrarAutor(String nomeAutor, String email, String senha, List<Livro> lirvosPublicados)
			throws LoginException {
		fachadaAutor.cadastrarAutor(nomeAutor, email, senha, lirvosPublicados);
	}

	public void alterarAutor(Long id, String nomeAutor, String senha, List<Livro> lirvosPublicados)
			throws LoginException {
		fachadaAutor.alterarAutor(id, nomeAutor, senha, lirvosPublicados);
	}
}
