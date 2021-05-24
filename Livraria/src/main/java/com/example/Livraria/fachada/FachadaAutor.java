package com.example.Livraria.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.autenticacao.AutenticacaoLogin;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.model.Autor;
import com.example.Livraria.model.Livro;
import com.example.Livraria.repositorio.AutorRepositorio;

@Service
public class FachadaAutor {
	@Autowired
	private AutorRepositorio autorRepositorio;

	public void cadastrarAutor(String nomeAutor, String email, String senha, List<Livro> lirvosPublicados)throws LoginException {
		Autor autor = new Autor(nomeAutor, email, senha, lirvosPublicados);
		if (!AutenticacaoLogin.validarLogin(email)) {
			throw new LoginException("Email invalido!");
		} else if (!AutenticacaoLogin.validarrSenha(senha)) {
			throw new LoginException("Senha invalido!");
		}
		autorRepositorio.save(autor);
	}

	public void alterarAutor(Long id, String nomeAutor, String email, String senha, List<Livro> lirvosPublicados) {
		Autor autor= autorRepositorio.findById(id);
		if(nomeAutor!= null) {
			autor.setNomeAutor(nomeAutor);
		}if(!AutenticacaoLogin.validarLogin(email)) {
			autor.setEmail(email);
		}if (!AutenticacaoLogin.validarrSenha(senha)) {
			autor.setSenha(senha);
		}
		autorRepositorio.save(autor);
	}
	public void adcionarLivro(Long id,Livro livro) {
		Autor autor= autorRepositorio.findById(id);
		List<Livro> livros=autor.getLirvosPublicados();
		livros.add(livro);
		autor.setLirvosPublicados(livros);
		autorRepositorio.save(autor);
	}
}
