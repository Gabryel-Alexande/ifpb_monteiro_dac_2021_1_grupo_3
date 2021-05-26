package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Livraria.model.Editora;

public interface EditoraRepositorio extends JpaRepository<Editora, Long> {
	public Editora findByIdEditora(Long id);
}
