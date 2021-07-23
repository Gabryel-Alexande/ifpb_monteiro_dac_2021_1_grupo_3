package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Livraria.model.Autor;

public interface AutoridadesRepositorio extends JpaRepository<Autor, Long>  {

}
