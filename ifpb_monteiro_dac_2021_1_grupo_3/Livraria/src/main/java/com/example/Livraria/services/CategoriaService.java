package com.example.Livraria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.model.Categoria;
import com.example.Livraria.repositorio.CategoriaRepositorio;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	
	//Aqui o cadastro so pode ser feito se não ouver outra categoria com o mesmo nome 
	public void criarCategoria(String nome) {
		Categoria categoria= new Categoria(nome);
		categoriaRepositorio.save(categoria);
	}
	
	//Aqui a edição so pode ser feito se não ouver outra categoria com o mesmo nome 
	public void editarCategoria(Long id,String nome) {
		Categoria categoria=categoriaRepositorio.findById(id).get();
		
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
