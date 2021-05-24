package com.example.Livraria.fachada;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.model.Categoria;
import com.example.Livraria.model.Editora;
import com.example.Livraria.model.Livro;
import com.example.Livraria.repositorio.LivroRepositorio;

@Service
public class FachadaLivro {
	
	@Autowired
	private LivroRepositorio livroRepositorio;

	public void cadastrarLivro(String tituloLivro, List<Categoria> categoria, String descricao, BigDecimal preco, Float margemX,
			Float margemY, String edicao, int anoLancamento, Editora editora, List<Image> fotosLivro,
			Integer quantidade){
		Livro livro= new Livro(tituloLivro, categoria, descricao, preco, margemX, margemY, edicao, anoLancamento, editora, fotosLivro, quantidade);
		livroRepositorio.save(livro);
	}

	public List<Livro> listarLivros() {
		return livroRepositorio.findAll();
	}
}
