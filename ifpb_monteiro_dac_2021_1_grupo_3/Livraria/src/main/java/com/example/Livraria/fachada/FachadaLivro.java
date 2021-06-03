package com.example.Livraria.fachada;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Service
public class FachadaLivro {

	@Autowired
	private LivroRepositorio livroRepositorio;
	@Autowired
	private AutorRepositorio autorRepositorio;
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	@Autowired
	private EditoraRepositorio editoraRepositorio;

	public void cadastrarLivro(String isbn, String tituloLivro, List<Long> categorias, String descricao,
			BigDecimal preco, String edicao, Integer anoLancamento, Long idEditora, List<Image> fotosLivro,
			List<Long> autores, Integer quantidade) throws IllegalArgumentException{

		List<Categoria> categoriasResgatados = new ArrayList<Categoria>();
		if(livroRepositorio.findByIsbn(isbn)!=null) {
			throw new IllegalArgumentException("Este isbn já existe!");
		}
		for (Long idCategoria : categorias) {
			Categoria categoria = categoriaRepositorio.findById(idCategoria).get();
			if (categoria != null) {
				categoriasResgatados.add(categoria);
			}
		}
		Editora editora = editoraRepositorio.findById(idEditora).get();
		Livro livro = new Livro(isbn, tituloLivro, categoriasResgatados, descricao, preco, edicao, quantidade, editora,
				fotosLivro, null, quantidade);
		List<Autor> autoresRegatados = new ArrayList<Autor>();
		for (Long autorDaVez : autores) {
			Autor autor = autorRepositorio.findById(autorDaVez).get();
			if (autor != null) {
				autoresRegatados.add(autor);
//				EnviadorDeEmail.enviarEmail(autor.getEmail(), "Novo livro cadastrado.",
//						"Você foi adcionado como autor do livro " + livro.getTituloLivro() + "!");
			}
		}
		livro.setAutores(autoresRegatados);
		livroRepositorio.save(livro);
	}

	public void alterarLivro(Long id, String isbn, String tituloLivro, String descricao, BigDecimal preco,
			String edicao, Integer anoLancamento, Long idEditora, Integer quantidadeEstoque) {
		Livro livro = livroRepositorio.findById(id).get();
		livro.setIsbn(isbn);
		livro.setTituloLivro(tituloLivro);
		livro.setDescricao(descricao);
		livro.setPreco(preco);
		livro.setEdicao(edicao);
		livro.setAnoLancamento(anoLancamento);
//		Editora editoraAntiga = editoraRepositorio.findById(livro.getEditora().getIdEditora()).get();
//		editoraAntiga.removerLivroNaEditora(livro);
		Editora editoraNova = editoraRepositorio.findById(idEditora).get();
//		editoraNova.adicionarLivroNaEditora(livro);
		livro.setEditora(editoraNova);
		livro.setQuantidadeEstoque(quantidadeEstoque);
		livroRepositorio.save(livro);

	}

	public void removerLivro(String isbn) {
		Livro livroRemove = livroRepositorio.findByIsbn(isbn);
		livroRepositorio.delete(livroRemove);

	}

	public void adcionarFoto(String isbn, Image imagem) {
		Livro livro = livroRepositorio.findByIsbn(isbn);
		livro.adcionarFoto(imagem);
		livroRepositorio.save(livro);
	}

	public void removerFoto(String isbn, Image imagem) {
		Livro livro = livroRepositorio.findByIsbn(isbn);
		livro.removerFoto(imagem);
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

	public List<String> listarLivros(String campoOrdenacao, int ordem, int quantidadeDePaginas) {
		Direction sortDirection = Sort.Direction.DESC;
		if (ordem == 2) {
			sortDirection = Sort.Direction.ASC;
		}
		Sort sort = Sort.by(sortDirection, campoOrdenacao);
		Page<Livro> pagina = livroRepositorio.findAll(PageRequest.of(--quantidadeDePaginas, 5, sort));

		ArrayList<String> livros = new ArrayList<String>();

		for (Livro livro : pagina) {
			livros.add(livro.toString());
		}
		return livros;
	}

	public List<String> listarCincoLivrosComMenorPreco() {
		return listarLivros("preco", 2, 1);
	}
}
