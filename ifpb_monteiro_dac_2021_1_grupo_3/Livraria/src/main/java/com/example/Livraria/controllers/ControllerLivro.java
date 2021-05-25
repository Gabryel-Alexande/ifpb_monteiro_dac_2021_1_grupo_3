package com.example.Livraria.controllers;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.List;

import com.example.Livraria.fachada.FachadaLivro;
import com.example.Livraria.model.Editora;

public class ControllerLivro{
	private FachadaLivro fachadaLivro = new FachadaLivro();

	public void cadastrarLivro(String isbn, String tituloLivro, List<Long> categoria, String descricao,
			BigDecimal preco,String edicao, int anoLancamento, Editora editora,
			List<Image> fotosLivro, List<Long> autores, Integer quantidade) {
		fachadaLivro.cadastrarLivro(isbn, tituloLivro, categoria, descricao, preco,edicao, anoLancamento, editora, fotosLivro, autores, quantidade);
	}

	public void alterarLivro(String isbn, String tituloLivro, String descricao, BigDecimal preco,
			 String edicao, Integer anoLancamento, Editora editora, Integer quantidadeEstoque) {
		fachadaLivro.alterarLivro(isbn, tituloLivro, descricao, preco, edicao, anoLancamento, editora, quantidadeEstoque);
	}

	public void adcionarFoto(String isbn, Image imagem) {
		fachadaLivro.adcionarFoto(isbn, imagem);
	}

	public void removerFoto(String isbn, Image imagem) {
		fachadaLivro.removerFoto(isbn, imagem);
	}

	public void adcionarCategoria(String isbn, Long idCategoria) {
		fachadaLivro.adcionarCategoria(isbn, idCategoria);
	}

	public void removerCategoria(String isbn, Long idCategoria) {
		fachadaLivro.removerCategoria(isbn, idCategoria);
	}

	public Object[] listarLivros() {
		return fachadaLivro.listarLivros().toArray();
	}
	public Object[] listarCincoLivrosComMenorPreco(){
		return fachadaLivro.listarCincoLivrosComMenorPreco().toArray();
	}
	public Object[] listarLivros(int quantidadeDePaginas) {
		return fachadaLivro.listarLivros(quantidadeDePaginas).toArray();
	}
}
