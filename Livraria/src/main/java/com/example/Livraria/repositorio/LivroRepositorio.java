package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Livraria.model.Livro;

public interface LivroRepositorio extends JpaRepository<Livro, String>{
	
}
