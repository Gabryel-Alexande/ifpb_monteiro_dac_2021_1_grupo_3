package com.example.Livraria.repositorio;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Livraria.model.Categoria;
import com.example.Livraria.model.Livro;

@Repository
public interface LivroRepositorio extends JpaRepository<Livro, Long> {

	public Livro findByIsbn(String isbn);

	@Query("SELECT l FROM Livro l WHERE l.quantidadeEstoque > 0 ORDER BY preco")
	public Page<Livro> livrosEmEstoque(Pageable pageRequest);

	public List<Livro> findByTituloLivro(String tituloLivro);

	// public List<Livro> findByCategoria(Categoria categorias);

	@Query("SELECT l FROM Livro l WHERE UPPER(l.tituloLivro) LIKE %?1%")
	public Page<Livro> conteinsTitulo(String titulo,Pageable page);

	@Query(value = "select distinct id_livro from livro join livro_categorias on id_livro = livro_id_livro where categorias_id_categoria in (:categorias)", nativeQuery = true)
	public List<Long> filtrarPorCategoria(@Param("categorias") List<Long> categorias);

	@Query(value = "select * from livro where id_livro in(:ids) order by titulo_livro", nativeQuery = true)
	public Page<Livro> buscarLivrosPeloId(@Param("ids") List<Long> ids, Pageable page);

	// public List<Livro> findByCategoria(List<Categoria> categorias);

	// List<Livro> findByCategorias(List<Categoria> categorias);
}
