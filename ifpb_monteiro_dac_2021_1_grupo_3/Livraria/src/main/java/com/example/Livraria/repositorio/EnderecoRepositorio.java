package com.example.Livraria.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Livraria.model.Endereco;
import com.example.Livraria.model.Usuario;

@Repository
public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {
	public List<Endereco> findByUsuario(Usuario usuario);
}
