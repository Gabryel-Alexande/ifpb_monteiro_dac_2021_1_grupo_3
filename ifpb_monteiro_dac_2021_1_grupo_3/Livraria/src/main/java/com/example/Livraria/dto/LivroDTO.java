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
import javax.validation.constraints.Size;

@Data
public class LivroDTO {

	private Long idLivro;

	@NotBlank(message = "O isbn não pode vazio")
	private String isbn;

	@NotBlank(message = "A foto do livro não pode ser vazia")
	private String fotoLivro;

	@NotBlank(message = "O titulo não pode ser vazio")
	private String tituloLivro;

	@NotBlank(message = "A descrição não pode  ser vazia")
	@Size(max = 255 ,message = "A Descrição não pode conter mais de 255 caracteres")
	private String descricao;

	@NotBlank(message = "A edição não pode ser vazia")
	private String edicao;
	@NotNull(message = "Por favor informe uma quantidade em estoque")
	private Integer quantidadeEstoque;
	@NotNull(message = "Informe o ano de lançamento do livro")
	private Integer anoLancamento;
	@NotNull(message = "Informe o valor do Livro")
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
