package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Livraria.model.Editora;

@Repository
public interface EditoraRepositorio extends JpaRepository<Editora, Long> {
	
	public Editora findByNomeEditora(String nomeEditora);
}
