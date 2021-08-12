package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Livraria.model.Autor;

@Repository
public interface AutorRepositorio  extends JpaRepository<Autor, Long>{
	
	public Autor findByNomeAutor(String nomeAutor);
}
