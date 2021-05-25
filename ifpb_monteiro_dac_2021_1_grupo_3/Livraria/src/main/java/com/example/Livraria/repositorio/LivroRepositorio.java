package com.example.Livraria.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Livraria.model.Livro;

public interface LivroRepositorio extends JpaRepository<Livro, String>{
	public Livro findByISBN(String isbn);
	public Page<Livro>findByPreco(Pageable pagina);
	public Page<Livro> findAll(Pageable pagina);

}
