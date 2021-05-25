package com.example.Livraria.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.model.Categoria;
import com.example.Livraria.model.Livro;
import com.example.Livraria.repositorio.CategoriaRepositorio;
import com.example.Livraria.repositorio.LivroRepositorio;

@Service
public class FachadaCategoria {
	
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	@Autowired
	private LivroRepositorio livroRepositorio;
	
	public void criarCategoria(String nome) {
		Categoria categoria= new Categoria(nome);
		categoriaRepositorio.save(categoria);
	}
	public void editarCategoria(Long id,String nome) {
		Categoria categoria=categoriaRepositorio.findByIdCategoria(id);
		categoria.setNomeCategoria(nome);
		categoriaRepositorio.save(categoria);
	}
	public List<Categoria> listarCategoria() {
		return categoriaRepositorio.findAll();
	}
	public void excluirCategoria(Long id) {
		Categoria categoria=categoriaRepositorio.findByIdCategoria(id);
		for (Livro livro : categoria.getLivros()) {
			livro.removerCategoria(categoria);
			livroRepositorio.save(livro);
		}
		categoriaRepositorio.delete(categoria);
	}
}
