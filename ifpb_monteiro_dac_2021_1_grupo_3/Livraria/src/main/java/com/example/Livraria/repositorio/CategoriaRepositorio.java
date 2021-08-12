package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Livraria.model.Categoria;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long>{
	public Categoria findByIdCategortia(Long idCategortia); 
	
	public Categoria findByNomeCategoria(String nomeCategoria);
}
