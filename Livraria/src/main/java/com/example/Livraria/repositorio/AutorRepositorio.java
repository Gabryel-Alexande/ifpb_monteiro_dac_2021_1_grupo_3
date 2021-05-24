package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Livraria.model.Autor;

public interface AutorRepositorio  extends JpaRepository<Autor, String>{
	public Autor findById(Long id);
}
