package com.example.Livraria.services;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectDeletedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.dto.LivroDTO;
import com.example.Livraria.model.Autor;
import com.example.Livraria.model.Categoria;
import com.example.Livraria.model.Editora;
import com.example.Livraria.model.ItemPedido;
import com.example.Livraria.model.Livro;
import com.example.Livraria.repositorio.AutorRepositorio;
import com.example.Livraria.repositorio.CategoriaRepositorio;
import com.example.Livraria.repositorio.EditoraRepositorio;
import com.example.Livraria.repositorio.ItemPedidoRepositorio;
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

	@Autowired
	private ItemPedidoRepositorio itemPedidoRepositorio;

	/*
	 * Este metodo cadastra um livro no banco recenbendo todos os seus parametros e
	 * o id da editora que pertece e uma coleção de id de categoria, autor e imagens
	 * que pertencem a este livro
	 */
	public void cadastrarLivro(LivroDTO livroDTO) throws IllegalArgumentException {

		// Aqui ocorre a valição dos dados,o isbn não podeser igual a nenhuma ja
		// existente no banco

		if (livroRepositorio.findByIsbn(livroDTO.getIsbn()) != null) {
			throw new IllegalArgumentException("[ERRO] Este isbn já existe!");
		}
		validarValoresLivro(livroDTO.getAnoLancamento(), new BigDecimal(livroDTO.getPreco()),
				livroDTO.getQuantidadeEstoque());

		livroDTO.instanciarArrays();

		for (Long id : livroDTO.getListaAutores()) {
			livroDTO.getAutores().add(autorRepositorio.findById(id).get());

		}

		for (Long id : livroDTO.getListaCategorias()) {
			livroDTO.getCategorias().add(categoriaRepositorio.findByIdCategortia(id));

		}

		livroDTO.setEditora(editoraRepositorio.findById(livroDTO.getIdEditora()).get());

		// parte responsavel por buscar todas a categoria requisitadas pelo id

		Livro livro = livroDTO.parser();

		// parte responsavel por buscar todas os autores requisitados pelo id

		livroRepositorio.save(livro);
	}

	/*
	 * Todos os parametros de livro são setados aqui, aqueles que não mudam veem com
	 * o mesmo valor
	 */
	public void alterarLivro(LivroDTO livroDTO, Long idLivroEdita) {

		Livro validacao = livroRepositorio.findByIsbn(livroDTO.getIsbn());
		// Aqui ocorre a valição dos dados,o isbn não podeser igual a nenhuma ja
		// existente no banco

		if (validacao.getIsbn().equals(livroDTO.getIsbn()) && validacao.getIdLivro() != idLivroEdita) {
			throw new IllegalArgumentException("[ERRO] Este isbn já existe!");
		}

		livroDTO.instanciarArrays();

		for (Long id : livroDTO.getListaAutores()) {
			livroDTO.getAutores().add(autorRepositorio.findById(id).get());

		}

		for (Long id : livroDTO.getListaCategorias()) {
			livroDTO.getCategorias().add(categoriaRepositorio.findByIdCategortia(id));

		}

		validarValoresLivro(livroDTO.getAnoLancamento(), new BigDecimal(livroDTO.getPreco()),
				livroDTO.getQuantidadeEstoque());
		Livro livro = livroRepositorio.findById(idLivroEdita).get();
		livro.setIsbn(livroDTO.getIsbn());
		livro.setTituloLivro(livroDTO.getTituloLivro());
		livro.setDescricao(livroDTO.getDescricao());
		livro.setPreco(new BigDecimal(livroDTO.getPreco()));
		livro.setEdicao(livroDTO.getEdicao());
		livro.setAnoLancamento(livroDTO.getAnoLancamento());
		Editora editoraNova = editoraRepositorio.findById(livroDTO.getIdEditora()).get();
		livro.setEditora(editoraNova);

		if (livroDTO.getCategorias().size() != 0) {
			livro.setCategorias(livroDTO.getCategorias());

		}

		if (livroDTO.getAutores().size() != 0) {
			livro.setAutores(livroDTO.getAutores());

		}

		livro.setQuantidadeEstoque(livroDTO.getQuantidadeEstoque());
		livroRepositorio.save(livro);

	}

	public void removerLivro(Long idLivro) throws IllegalArgumentException{
		Livro livroRemove = livroRepositorio.findById(idLivro).get();
		List<ItemPedido> itemPedidos = itemPedidoRepositorio.findByLivro(livroRemove);

		if(itemPedidos.size()>0) {
			throw new IllegalArgumentException("Impossível deletar esse livro póis clientes já o compraram");
		}

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

	public Livro buscarLivroPorId(Long id) {
		return livroRepositorio.findById(id).get();
	}

	public Page<Livro> listarLivrosCategoria(List<Long> idCategoria, int numeroPagina) {

		List<Long> idLivros = livroRepositorio.filtrarPorCategoria(idCategoria);

		return livroRepositorio.buscarLivrosPeloId(idLivros, PageRequest.of(--numeroPagina, 12));

	}

	public Page<Livro> listarTodosOsLivros(Integer numeroPagina) {

		Direction sortDirection = Sort.Direction.ASC;

		Sort sort = Sort.by(sortDirection, "tituloLivro");
		Page<Livro> pagina = livroRepositorio.findAll(PageRequest.of(--numeroPagina, 12, sort));

		return pagina;
	}

	public List<Livro> listarCincoLivrosComMenorPreco() {
		List<Livro> livros = new ArrayList<Livro>();
		int numeroPagina = 1;
		for (Livro livro : livroRepositorio.livrosEmEstoque(PageRequest.of(--numeroPagina, 5))) {
			livros.add(livro);
		}
		return livros;
	}

	public Page<Livro> bucarLivroPorNome(String nome, Integer numeroPagina) {
//		List<Livro> livros= new ArrayList<Livro>();
//		for (Livro livro : livroRepositorio.findByTituloLivro(nome)) {
//			livros.add(livro);
//		}
		Sort sort = Sort.by(Sort.Direction.ASC, "tituloLivro");
		return livroRepositorio.conteinsTitulo(nome, PageRequest.of(--numeroPagina, 12, sort));
	}

//	public List<Livro> bucarLivrosPorCategoria(Long id){
//		List<Livro> livros= new ArrayList<Livro>();
//		Categoria categoria= categoriaRepositorio.findByIdCategortia(id);
//		for (Livro livro : livroRepositorio.findByCategorias(categoria)) {
//			livros.add(livro);
//		}
//		return livros;
//	}
	public Livro bucarLivrosPorIsbn(String isbn) {
		return livroRepositorio.findByIsbn(isbn);
	}

	public Livro bucarLivrosPorId(Long id) {
		Optional<Livro> livro = livroRepositorio.findById(id);
		if (livro.isPresent()) {
			return livro.get();

		}
		return null;

	}

	/*
	 * Metodo validador de dados do livro, aqui é testado se todos os valores
	 * correspondem as restrições do banco
	 */
	private void validarValoresLivro(Integer anoLancamento, BigDecimal preco, Integer quantidade) {
		if (anoLancamento > Calendar.getInstance().get(Calendar.YEAR) || anoLancamento < 0) {
			throw new IllegalArgumentException("[ERRO] Data invalida!");
		}
		if (preco.floatValue() <= 0) {
			throw new IllegalArgumentException("[ERRO] Preco invalida!");
		}
		if (quantidade < 0) {
			throw new IllegalArgumentException("[ERRO] Quantidade invalida!");
		}
	}
}
