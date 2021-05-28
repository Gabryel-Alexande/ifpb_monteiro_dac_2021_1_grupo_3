package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Livraria.model.Usuario;
@Repository
public interface UsuarioRepositorio  extends JpaRepository<Usuario, String>{
	//Buca por E-mail
	public Usuario findByEmail(String email);
	public Usuario findByCpf(String cpf);
}

