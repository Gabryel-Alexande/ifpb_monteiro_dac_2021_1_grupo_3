package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Livraria.model.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long>{
	public Categoria findByIdCategoria(Long id);
}
