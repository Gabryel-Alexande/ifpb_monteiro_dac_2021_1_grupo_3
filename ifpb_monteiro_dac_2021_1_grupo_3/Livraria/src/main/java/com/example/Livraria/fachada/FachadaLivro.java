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
import com.example.Livraria.repositorio.LivroRepositorio;
import com.example.Livraria.utilitarios.EnviadorDeEmail;

@Service
public class FachadaLivro {

	@Autowired
	private LivroRepositorio livroRepositorio;
	@Autowired
	private AutorRepositorio autorRepositorio;

	public void cadastrarLivro(String isbn, String tituloLivro, List<Categoria> categoria, String descricao,
			BigDecimal preco, Float margemX, Float margemY, String edicao, int anoLancamento, Editora editora,
			List<Image> fotosLivro, List<Long> autores, Integer quantidade) {
		Livro livro = new Livro(isbn, tituloLivro, categoria, descricao, preco, margemX, margemY, edicao, quantidade,
				editora, fotosLivro, null, quantidade);
		List<Autor> autoresRegatados = new ArrayList<Autor>();
		for (Long autorDaVez : autores) {
			Autor autor = autorRepositorio.findById(autorDaVez);
			autor.adcionarLivro(livro);
			if (autor != null) {
				autoresRegatados.add(autor);
				EnviadorDeEmail.enviarEmail(autor.getEmail(), "Novo livro cadastrado.",
						"Você foi adcionado como autor do livro "+livro.getTituloLivro()+"!");
				autorRepositorio.save(autor);
			}
		}
		livro.setAutores(autoresRegatados);
		livroRepositorio.save(livro);
	}

	public void alterarLivro(String isbn, String tituloLivro, String descricao, BigDecimal preco, Float margemX,
			Float margemY, String edicao, Integer anoLancamento, Editora editora, Integer quantidadeEstoque) {
		Livro livro = livroRepositorio.findByISBN(isbn);
		livro.setTituloLivro(tituloLivro);
		livro.setDescricao(descricao);
		livro.setPreco(preco);
		livro.setMargemX(margemX);
		livro.setMargemY(margemY);
		livro.setEdicao(edicao);
		livro.setAnoLancamento(anoLancamento);
		livro.setEditora(editora);
		livro.setQuantidadeEstoque(quantidadeEstoque);
		livroRepositorio.save(livro);
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

	public void adcionarCategoria(String isbn, Categoria categoria) {
		Livro livro = livroRepositorio.findByISBN(isbn);
		livro.adcionarCategoria(categoria);
		livroRepositorio.save(livro);
	}

	public void removerCategoria(String isbn, Categoria categoria) {
		Livro livro = livroRepositorio.findByISBN(isbn);
		livro.removerCategoria(categoria);
		livroRepositorio.save(livro);
	}

	public List<Livro> listarLivros() {
		return livroRepositorio.findAll();
	}
	public List<Livro> listarLivros(int quantidadeDePaginas) {
		return null;
	}
	public List<Livro> listarCincoLivrosComMenorPreco(){
		return null;
	}
}
