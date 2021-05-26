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
import com.example.Livraria.utilitarios.EnviadorDeEmail;

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
			List<Long> autores, Integer quantidade) {

		List<Categoria> categoriasResgatados = new ArrayList<Categoria>();
		for (Long idCategoria : categorias) {
			Categoria categoria = categoriaRepositorio.findByIdCategoria(idCategoria);
			if (categoria != null) {
				categoriasResgatados.add(categoria);
			}
		}
		Editora editora = editoraRepositorio.findByIdEditora(idEditora);
		Livro livro = new Livro(isbn, tituloLivro, categoriasResgatados, descricao, preco, edicao, quantidade, editora,
				fotosLivro, null, quantidade);
		editora.adicionarLivroNaEditora(livro);
		List<Autor> autoresRegatados = new ArrayList<Autor>();
		for (Long autorDaVez : autores) {
			Autor autor = autorRepositorio.findById(autorDaVez);
			autor.adcionarLivro(livro);
			if (autor != null) {
				autoresRegatados.add(autor);
				EnviadorDeEmail.enviarEmail(autor.getEmail(), "Novo livro cadastrado.",
						"Você foi adcionado como autor do livro " + livro.getTituloLivro() + "!");
				autorRepositorio.save(autor);
			}
		}
		livro.setAutores(autoresRegatados);
		livroRepositorio.save(livro);
		editoraRepositorio.save(editora);
	}

	public void alterarLivro(String isbn, String tituloLivro, String descricao, BigDecimal preco, String edicao,
			Integer anoLancamento, Long idEditora, Integer quantidadeEstoque) {
		Livro livro = livroRepositorio.findByISBN(isbn);
		livro.setTituloLivro(tituloLivro);
		livro.setDescricao(descricao);
		livro.setPreco(preco);
		livro.setEdicao(edicao);
		livro.setAnoLancamento(anoLancamento);
		Editora editoraAntiga = editoraRepositorio.findByIdEditora(livro.getEditora().getIdEditora());
		editoraAntiga.removerLivroNaEditora(livro);
		editoraRepositorio.save(editoraAntiga);
		Editora editoraNova = editoraRepositorio.findByIdEditora(idEditora);
		editoraNova.adicionarLivroNaEditora(livro);
		editoraRepositorio.save(editoraNova);
		livro.setEditora(editoraNova);
		livro.setQuantidadeEstoque(quantidadeEstoque);
		livroRepositorio.save(livro);

	}

	public void removerLivro(String isbn) {
		Livro livroRemove = livroRepositorio.findByISBN(isbn);
		Editora editora = editoraRepositorio.findByIdEditora(livroRemove.getEditora().getIdEditora());
		editora.removerLivroNaEditora(livroRemove);
		editoraRepositorio.save(editora);
		livroRepositorio.delete(livroRemove);

	}

	public void adcionarFoto(String isbn, Image imagem) {
		Livro livro = livroRepositorio.findByISBN(isbn);
		livro.adcionarFoto(imagem);
		livroRepositorio.save(livro);
	}

	public void removerFoto(String isbn, Image imagem) {
		Livro livro = livroRepositorio.findByISBN(isbn);
		livro.removerFoto(imagem);
		livroRepositorio.save(livro);
	}

	public void adcionarCategoria(String isbn, Long idCategoria) {
		Livro livro = livroRepositorio.findByISBN(isbn);
		Categoria categoria = categoriaRepositorio.findByIdCategoria(idCategoria);
		livro.adcionarCategoria(categoria);
		categoria.adcionarLivro(livro);
		livroRepositorio.save(livro);
		categoriaRepositorio.save(categoria);
	}

	public void removerCategoria(String isbn, Long idCategoria) {
		Livro livro = livroRepositorio.findByISBN(isbn);
		Categoria categoria = categoriaRepositorio.findByIdCategoria(idCategoria);
		livro.removerCategoria(categoria);
		categoria.removerLivro(livro);
		livroRepositorio.save(livro);
		categoriaRepositorio.save(categoria);
	}

	public List<Livro> listarLivros() {
		return livroRepositorio.findAll();
	}

	public Page<Livro> listarLivros(String campoOrdenacao, int ordem, int quantidadeDePaginas) {
		Direction sortDirection = Sort.Direction.DESC;
		if (ordem == 2) {
			sortDirection = Sort.Direction.ASC;
		}
		Sort sort = Sort.by(sortDirection, campoOrdenacao);
		Page<Livro> pagina = livroRepositorio.findAll(PageRequest.of(--quantidadeDePaginas, 5, sort));

		return pagina;
	}

	public Page<Livro> listarCincoLivrosComMenorPreco() {
		Direction sortDirection = Sort.Direction.ASC;
		Sort sort = Sort.by(sortDirection, "preco");
		Page<Livro> pagina = livroRepositorio.findAll(PageRequest.of(5, 5, sort));
		return pagina;
	}
}
