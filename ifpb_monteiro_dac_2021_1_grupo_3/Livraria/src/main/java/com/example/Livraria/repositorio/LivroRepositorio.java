package com.example.Livraria.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Livraria.model.Categoria;
import com.example.Livraria.model.Livro;

@Repository
public interface LivroRepositorio extends JpaRepository<Livro, Long>{
	
	public Livro findByIsbn(String isbn);
	
	@Query("SELECT l FROM Livro l WHERE l.quantidadeEstoque > 0 ORDER BY preco")
	public Page<Livro> livrosEmEstoque(Pageable  pageRequest);
	
	public List<Livro> findByTituloLivro(String tituloLivro);

	public List<Livro> findByCategorias(Categoria categorias);
}
