package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Livraria.model.Endereco;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {
	public Endereco findByIdEndereco(Long idEndereco);
}
