package com.example.Livraria.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.example.Livraria.model.Autor;
import com.example.Livraria.model.Categoria;
import com.example.Livraria.model.Editora;
import com.example.Livraria.model.Livro;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LivroDTO {

	private Long idLivro;

	@NotBlank
	private String isbn;

	@NotBlank
	private String fotoLivro;

	@NotBlank
	private String tituloLivro;

	@NotBlank
	private String descricao;

	@NotBlank
	private String edicao;
	@NotNull
	private Integer quantidadeEstoque;
	@NotNull
	private Integer anoLancamento;
	@NotNull
	private Float preco;
	@NotNull
	private List<Long> listaAutores;
	@NotNull
	private List<Long> listaCategorias;
	@NotNull
	private Long idEditora;


	private List<Autor> autores;

	private List<Categoria> categorias;

	private Editora editora;

	public void instanciarArrays() {
		this.autores = new ArrayList<Autor>();
		this.categorias = new ArrayList<Categoria>();
	}

	public Livro parser() {

		Livro livro = new Livro();

		livro.setIsbn(this.isbn);

		livro.setFotoLivro(this.fotoLivro);

		livro.setTituloLivro(this.getTituloLivro());

		livro.setEdicao(this.edicao);

		livro.setQuantidadeEstoque(this.quantidadeEstoque);

		livro.setAnoLancamento(this.anoLancamento);

		livro.setPreco(new BigDecimal(this.getPreco()));
		livro.setDescricao(this.getDescricao());
		livro.setEditora(this.getEditora());
		
		
		livro.setCategorias(this.getCategorias());
		
		livro.setAutores(this.getAutores());

		
		

		return livro;
	}
}
