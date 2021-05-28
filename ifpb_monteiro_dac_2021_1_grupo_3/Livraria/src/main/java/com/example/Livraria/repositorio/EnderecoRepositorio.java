package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Livraria.model.Endereco;

@Repository
public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {
}
