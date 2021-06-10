package com.example.Livraria.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.model.Categoria;
import com.example.Livraria.repositorio.CategoriaRepositorio;
import com.example.Livraria.utilitarios.ValidadorNome;

@Service
public class FachadaCategoria {
	
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	
	public void criarCategoria(String nome) {
		if(!ValidadorNome.validarNome(nome)) {
			throw new IllegalArgumentException("[ERRO] Nome invalido!");
		}
		Categoria categoria= new Categoria(nome);
		categoriaRepositorio.save(categoria);
	}
	public void editarCategoria(Long id,String nome) {
		Categoria categoria=categoriaRepositorio.findById(id).get();
		if(!ValidadorNome.validarNome(nome)) {
			throw new IllegalArgumentException("[ERRO] Nome invalido!");
		}
		categoria.setNomeCategoria(nome);
		categoriaRepositorio.save(categoria);
	}
	public List<Categoria> listarCategoria() {
		return categoriaRepositorio.findAll();
	}
	public void excluirCategoria(Long id) {
		categoriaRepositorio.deleteById(id);
	}
}
