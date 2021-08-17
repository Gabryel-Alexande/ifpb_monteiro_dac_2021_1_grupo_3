package com.example.Livraria.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.Livraria.dto.AutorDTO;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.model.Autor;
import com.example.Livraria.model.Livro;
import com.example.Livraria.repositorio.AutorRepositorio;
import com.example.Livraria.utilitarios.AutenticacaoLogin;
import com.example.Livraria.utilitarios.EnviadorDeEmail;

@Service
public class AutorService {
	@Autowired
	private AutorRepositorio autorRepositorio;

	// Cadastro de Autor só pode acontecer se não ouver outro autor com o mesmo nome
	// ou mesmo email
	public void cadastrarAutor(AutorDTO autorDto) throws LoginException {

		if (autorRepositorio.findByNomeAutor(autorDto.getNomeAutor()) != null) {
			throw new DuplicateKeyException("Já existe um Autor com esse nome");
		}

		Autor autor = autorDto.parser();

		autorRepositorio.save(autor);
	}

	// Aqui decidimos que o usuario não podera alterar o email, visto que isso pode
	// trazer problemas para
	// sua conta
	public void alterarAutor(Long id, String nomeAutor) throws NoSuchElementException {
		Autor autor = null;

		autor = autorRepositorio.findByNomeAutor(nomeAutor);

		try {
			if (autor.getNomeAutor().equalsIgnoreCase(nomeAutor) && autor.getIdAutor() != id) {
				throw new DuplicateKeyException("Nome já existe ");
			}
		} catch (NullPointerException e) {}

		autor = autorRepositorio.findById(id).get();
		autor.setNomeAutor(nomeAutor);

		autorRepositorio.save(autor);

	}

	public Autor encontarAutor(Long idAutor) {
		return autorRepositorio.findById(idAutor).get();
	}

	public void removerAutor(Long idAutor) {
		Optional<Autor> autor = autorRepositorio.findById(idAutor);

		if (autor.isPresent()) {
			autorRepositorio.delete(autor.get());
		}

	}

	public List<Autor> listarAutores() {
		return autorRepositorio.findAll();
	}

	public Page<Autor> listarAutores(Integer numeroPagina) {
		Direction sortDirection = Sort.Direction.ASC;
		Sort sort = Sort.by(sortDirection, "nomeAutor");
		Page<Autor> page = autorRepositorio.findAll(PageRequest.of(--numeroPagina, 5, sort));
		return page;
	}
}
