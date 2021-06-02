package com.example.Livraria.controllers;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.Livraria.fachada.FachadaLivro;
@Controller
public class ControllerLivro{
	@Autowired
	private FachadaLivro fachadaLivro;
	
	public void cadastrarLivro(String isbn, String tituloLivro, List<Long> categoria, String descricao,
			BigDecimal preco,String edicao, Integer anoLancamento, Long idEditora,
			List<Image> fotosLivro, List<Long> autores, Integer quantidade) {
		fachadaLivro.cadastrarLivro(isbn, tituloLivro, categoria, descricao, preco,edicao, anoLancamento, idEditora, fotosLivro, autores, quantidade);
	}

	public void alterarLivro(Long id,String isbn, String tituloLivro, String descricao, BigDecimal preco,
			 String edicao, Integer anoLancamento, Long idEditora, Integer quantidadeEstoque) {
		fachadaLivro.alterarLivro(id,isbn, tituloLivro, descricao, preco, edicao, anoLancamento, idEditora, quantidadeEstoque);
	}
	public void removerLivro(String isbn) {
		fachadaLivro.removerLivro(isbn);
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
	public Object[] listarLivros(String campoOrdenacao, int ordem, int quantidadeDePaginas) {
		return fachadaLivro.listarLivros(campoOrdenacao, ordem, quantidadeDePaginas).toArray();
	}
}
