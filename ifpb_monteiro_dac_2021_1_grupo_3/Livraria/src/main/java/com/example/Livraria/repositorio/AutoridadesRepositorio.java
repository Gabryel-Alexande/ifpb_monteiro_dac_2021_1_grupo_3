package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Livraria.model.Autor;

public interface AutoridadesRepositorio extends JpaRepository<Autor, Long>  {
	
	@Query(value = "Insert into autoridades (id,nome) values (1,'CLIENTE') ",nativeQuery = true)
	public void insertAutoridadesCliente();
	
	
	@Query(value = "Insert into autoridades (id,nome) values (2,'ADMINISTRADOR') ",nativeQuery = true)
	public void insertAutoridadesADM();
	
	
	
}
