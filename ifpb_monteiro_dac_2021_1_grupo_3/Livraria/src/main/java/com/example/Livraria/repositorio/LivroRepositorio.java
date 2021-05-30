package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Livraria.model.Livro;

@Repository
public interface LivroRepositorio extends JpaRepository<Livro, Long>{
	public Livro findByIsbn(String isbn);
}
