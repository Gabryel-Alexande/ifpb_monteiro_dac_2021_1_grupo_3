package com.example.Livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.example.Livraria.dto.CategoriaDTO;
import com.example.Livraria.model.Autor;
import com.example.Livraria.model.Categoria;
import com.example.Livraria.repositorio.CategoriaRepositorio;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepositorio categoriaRepositorio;

	// Aqui o cadastro so pode ser feito se não ouver outra categoria com o mesmo
	// nome

	public void criarCategoria(CategoriaDTO cat) {

		if (categoriaRepositorio.findByNomeCategoria(cat.getNomeCategoria()) != null) {
			throw new DuplicateKeyException("Já existe uma editora com esse nome");
		}

		Categoria categoria = cat.parser();
		categoriaRepositorio.save(categoria);
	}

	// Aqui a edição so pode ser feito se não ouver outra categoria com o mesmo nome
	public void editarCategoria(Long id, String nome) {
		Categoria categoria = categoriaRepositorio.findByNomeCategoria(nome);

		try {
			if (categoria.getNomeCategoria().equalsIgnoreCase(nome) && categoria.getIdCategortia() != id) {
				throw new DuplicateKeyException("Nome já existe");
			}
		} catch (NullPointerException e) {}

		categoria = categoriaRepositorio.findById(id).get();
		categoria.setNomeCategoria(nome);
		categoriaRepositorio.save(categoria);
	}

	public void removerCategoria(Long idCat) {
		Optional<Categoria> cat = categoriaRepositorio.findById(idCat);

		if (cat.isPresent()) {
			categoriaRepositorio.delete(cat.get());
		}

	}

	public Categoria encontarCategoria(Long idCat) {
		return categoriaRepositorio.findByIdCategortia(idCat);

	}

	public List<Categoria> listarCategoria() {
		return categoriaRepositorio.findAll();
	}

	public void excluirCategoria(Long id) {
		categoriaRepositorio.deleteById(id);
	}
}
