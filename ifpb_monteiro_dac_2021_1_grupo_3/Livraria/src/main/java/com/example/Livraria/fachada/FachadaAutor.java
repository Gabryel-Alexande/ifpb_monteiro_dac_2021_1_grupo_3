package com.example.Livraria.fachada;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.model.Autor;
import com.example.Livraria.repositorio.AutorRepositorio;
import com.example.Livraria.utilitarios.AutenticacaoLogin;

@Service
public class FachadaAutor {
	@Autowired
	private AutorRepositorio autorRepositorio;

	public void cadastrarAutor(String nomeAutor, String email, String senha) throws LoginException {
		Autor autor = new Autor(nomeAutor, email, senha);
		if (!AutenticacaoLogin.validarLogin(email)) {
			throw new LoginException("Email invalido!");
		} else if (!AutenticacaoLogin.validarrSenha(senha)) {
			throw new LoginException("Senha fraca!\nPor favor digite uma senha melhor!");
		}
//		EnviadorDeEmail.enviarEmail(email, "Sua conta foi criada com sucesso!", "Seja bem vindo a nossa loja "
//				+ nomeAutor
//				+ "\nAqui você terá liberdade de publicar e vender seus livros.\nSinta-se avontade para nos catactar.\nObrigado Por nos escolher.");
		autorRepositorio.save(autor);
	}

	// Aqui decidimos que o usuario não podera alterar o email, visto que isso pode
	// trazer problemas para
	// sua conta
	public void alterarAutor(Long id, String nomeAutor, String senha) throws LoginException, NoSuchElementException {
		Autor autor = null;
		try {
			autor = autorRepositorio.findById(id).get();
		} catch (Exception e) {
			throw new NoSuchElementException("ID invalido 1");
		}
		autor.setNomeAutor(nomeAutor);
		if (!AutenticacaoLogin.validarrSenha(senha)) {
			throw new LoginException("Senha fraca!\nPor favor digite uma senha melhor!");
		}
		autor.setSenha(senha);
		autorRepositorio.save(autor);
	
	}

	public List<Autor> listarAutores() {
		return autorRepositorio.findAll();
	}
}
