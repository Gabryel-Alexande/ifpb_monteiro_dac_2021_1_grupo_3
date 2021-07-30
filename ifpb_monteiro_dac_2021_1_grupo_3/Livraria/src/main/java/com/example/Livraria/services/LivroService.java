package com.example.Livraria.services;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.model.Autor;
import com.example.Livraria.model.Categoria;
import com.example.Livraria.model.Editora;
import com.example.Livraria.model.Livro;
import com.example.Livraria.repositorio.AutorRepositorio;
import com.example.Livraria.repositorio.CategoriaRepositorio;
import com.example.Livraria.repositorio.EditoraRepositorio;
import com.example.Livraria.repositorio.LivroRepositorio;
import com.example.Livraria.utilitarios.EnviadorDeEmail;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Service
public class LivroService {

	@Autowired
	private LivroRepositorio livroRepositorio;
	@Autowired
	private AutorRepositorio autorRepositorio;
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	@Autowired
	private EditoraRepositorio editoraRepositorio;
	@Autowired
	private EnviadorDeEmail enviadorDeEmail;
	/*
	 * Este metodo cadastra um livro no banco recenbendo todos os seus parametros e o id da editora que pertece
	 * e uma coleção de id de categoria, autor e imagens que pertencem a este livro
	 */
	public void cadastrarLivro(String isbn, String tituloLivro, List<Long> categorias, String descricao,
			BigDecimal preco, String edicao, Integer anoLancamento, Long idEditora, String fotoLivro,
			List<Long> autores, Integer quantidade) throws IllegalArgumentException {
		List<Categoria> categoriasResgatados = new ArrayList<Categoria>();
		
		//Aqui ocorre a valição dos dados,o isbn não podeser igual a nenhuma ja existente no banco

		if (livroRepositorio.findByIsbn(isbn) != null) {
			throw new IllegalArgumentException("[ERRO] Este isbn já existe!");
		}
		validarValoresLivro(anoLancamento, preco, quantidade);
		
		//parte responsavel por buscar todas a categoria requisitadas pelo id
		for (Long idCategoria : categorias) {
			Categoria categoria = categoriaRepositorio.findById(idCategoria).get();
			if (categoria != null) {
				categoriasResgatados.add(categoria);
			}
		}
		Editora editora = editoraRepositorio.findById(idEditora).get();
		Livro livro = new Livro(isbn, tituloLivro, categoriasResgatados, descricao, preco, edicao, anoLancamento,
				editora, fotoLivro, null, quantidade);
		List<Autor> autoresRegatados = new ArrayList<Autor>();
		
		//parte responsavel por buscar todas os autores requisitados pelo id
		for (Long autorDaVez : autores) {
			Autor autor = autorRepositorio.findById(autorDaVez).get();
			if (autor != null) {
				autoresRegatados.add(autor);
			}
		}
		livro.setAutores(autoresRegatados);
		livroRepositorio.save(livro);
	}
	/*
	 * Todos os parametros de livro são setados aqui, aqueles que não mudam veem com o mesmo valor
	 */
	public void alterarLivro(Long id, String isbn, String tituloLivro, String descricao, BigDecimal preco,
			String edicao, Integer anoLancamento, Long idEditora, Integer quantidadeEstoque) {
		Livro validacao = livroRepositorio.findByIsbn(isbn);
		
		//Aqui ocorre a valição dos dados,o isbn não podeser igual a nenhuma ja existente no banco
		
		if (validacao.getIsbn().equals(isbn) && validacao.getIdLivro() != id) {
			throw new IllegalArgumentException("[ERRO] Este isbn já existe!");
		}
		validarValoresLivro(anoLancamento, preco, quantidadeEstoque);
		Livro livro = livroRepositorio.findById(id).get();
		livro.setIsbn(isbn);
		livro.setTituloLivro(tituloLivro);
		livro.setDescricao(descricao);
		livro.setPreco(preco);
		livro.setEdicao(edicao);
		livro.setAnoLancamento(anoLancamento);
		Editora editoraNova = editoraRepositorio.findById(idEditora).get();
		livro.setEditora(editoraNova);
		livro.setQuantidadeEstoque(quantidadeEstoque);
		livroRepositorio.save(livro);

	}

	public void removerLivro(String isbn) {
		Livro livroRemove = livroRepositorio.findByIsbn(isbn);
		livroRepositorio.delete(livroRemove);

	}

	public void adcionarFoto(String isbn, String imagem) {
		Livro livro = livroRepositorio.findByIsbn(isbn);
		livro.setFotoLivro(imagem);
		livroRepositorio.save(livro);
	}

	public void removerFoto(String isbn, Image imagem) {
		Livro livro = livroRepositorio.findByIsbn(isbn);
		livro.setFotoLivro(null);
		livroRepositorio.save(livro);
	}

	public void adcionarCategoria(String isbn, Long idCategoria) {
		Livro livro = livroRepositorio.findByIsbn(isbn);
		Categoria categoria = categoriaRepositorio.findById(idCategoria).get();
		livro.adcionarCategoria(categoria);
		categoria.adcionarLivro(livro);
		livroRepositorio.save(livro);
	}

	public void removerCategoria(String isbn, Long idCategoria) {
		Livro livro = livroRepositorio.findByIsbn(isbn);
		Categoria categoria = categoriaRepositorio.findById(idCategoria).get();
		livro.removerCategoria(categoria);
		categoria.removerLivro(livro);
		livroRepositorio.save(livro);
	}

	public List<Livro> listarLivros() {
		return livroRepositorio.findAll();
	}

	public List<Livro> listarLivros(String campoOrdenacao, int ordem, int numeroPagina) {
		Direction sortDirection = Sort.Direction.DESC;
		if (ordem == 2) {
			sortDirection = Sort.Direction.ASC;
		}

		Sort sort = Sort.by(sortDirection, campoOrdenacao);
		Page<Livro> pagina = livroRepositorio.findAll(PageRequest.of(--numeroPagina, 5, sort));

		List<Livro> livros = new ArrayList<Livro>();

		for (Livro livro : pagina) {
			livros.add(livro);
		}
		return livros;
	}

	public List<Livro> listarCincoLivrosComMenorPreco() {
		List<Livro> livros = new ArrayList<Livro>();
		int numeroPagina = 1;
		for (Livro livro : livroRepositorio.livrosEmEstoque(PageRequest.of(--numeroPagina, 5))) {
			livros.add(livro);
		}
		return livros;
	}
	public List<Livro> bucarLivroPorNome(String nome){
//		List<Livro> livros= new ArrayList<Livro>();
//		for (Livro livro : livroRepositorio.findByTituloLivro(nome)) {
//			livros.add(livro);
//		}
		
		return livroRepositorio.conteinsTitulo(nome) ;
	}
	public List<Livro> bucarLivrosPorCategoria(Long id){
		List<Livro> livros= new ArrayList<Livro>();
		Categoria categoria= categoriaRepositorio.findByIdCategortia(id);
		for (Livro livro : livroRepositorio.findByCategorias(categoria)) {
			livros.add(livro);
		}
		return livros;
	}
	public Livro bucarLivrosPorIsbn(String isbn){
		return livroRepositorio.findByIsbn(isbn);
	}
	public Livro bucarLivrosPorId(Long id){
		Optional<Livro> livro = livroRepositorio.findById(id);
		if(livro.isPresent()) {
			return livro.get();
			
		}
		return null;
		
	}
	
	/*
	 * Metodo validador de dados do livro, aqui é testado se todos os valores correspondem as restrições do banco
	 */
	private void validarValoresLivro(Integer anoLancamento, BigDecimal preco,Integer quantidade) {
		if (anoLancamento > Calendar.getInstance().get(Calendar.YEAR) || anoLancamento < 0) {
			throw new IllegalArgumentException("[ERRO] Data invalida!");
		}
		if (preco.floatValue() <= 0) {
			throw new IllegalArgumentException("[ERRO] Preco invalida!");
		}
		if(quantidade<0) {
			throw new IllegalArgumentException("[ERRO] Quantidade invalida!");
		}
	}
}
