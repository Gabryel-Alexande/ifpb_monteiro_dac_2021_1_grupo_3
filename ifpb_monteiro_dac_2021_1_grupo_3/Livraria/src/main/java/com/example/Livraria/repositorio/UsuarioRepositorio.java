package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Livraria.model.Usuario;

public interface UsuarioRepositorio  extends JpaRepository<Usuario, String>{
	//Buca por E-mail
	public Usuario findByEmail(String email);
	public Usuario findById(Long idUsuario);

}

