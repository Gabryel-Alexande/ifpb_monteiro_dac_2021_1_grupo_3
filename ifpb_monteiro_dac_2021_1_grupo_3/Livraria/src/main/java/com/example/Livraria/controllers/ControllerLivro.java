package com.example.Livraria.controllers;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.List;

import com.example.Livraria.fachada.FachadaLivro;
import com.example.Livraria.model.Categoria;
import com.example.Livraria.model.Editora;
import com.example.Livraria.model.Livro;

public class ControllerLivro {
	private FachadaLivro fachadaLivro;

	public void cadastrarLivro(String isbn, String tituloLivro, List<Categoria> categoria, String descricao,
			BigDecimal preco, Float margemX, Float margemY, String edicao, int anoLancamento, Editora editora,
			List<Image> fotosLivro, List<Long> autores, Integer quantidade) {
		fachadaLivro.cadastrarLivro(isbn, tituloLivro, categoria, descricao, preco, margemX, margemY, edicao, anoLancamento, editora, fotosLivro, autores, quantidade);
	}

	public void alterarLivro(String isbn, String tituloLivro, String descricao, BigDecimal preco, Float margemX,
			Float margemY, String edicao, Integer anoLancamento, Editora editora, Integer quantidadeEstoque) {
		fachadaLivro.alterarLivro(isbn, tituloLivro, descricao, preco, margemX, margemY, edicao, anoLancamento, editora, quantidadeEstoque);
	}

	public void adcionarFoto(String isbn, Image imagem) {
		fachadaLivro.adcionarFoto(isbn, imagem);
	}

	public void removerFoto(String isbn, Image imagem) {
		fachadaLivro.removerFoto(isbn, imagem);
	}

	public void adcionarCategoria(String isbn, Categoria categoria) {
		fachadaLivro.adcionarCategoria(isbn, categoria);
	}

	public void removerCategoria(String isbn, Categoria categoria) {
		fachadaLivro.removerCategoria(isbn, categoria);
	}

	public List<Livro> listarLivros() {
		return fachadaLivro.listarLivros();
	}
}
