package com.example.Livraria.model;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class Livro {
	@Column(name = "titulo_livro", nullable = false)
	private String tituloLivro;
	@Id
	private String isbn;
	@NotNull
	@ManyToMany
	private List<Categoria> categoria;
	@NotNull
	private String descricao;
	@NotNull
	private BigDecimal preco;
	@Column(name = "margem_x", nullable = false)
	private Float margemX;
	@Column(name = "margem_y", nullable = false)
	private Float margemY;
	@NotNull
	private String edicao;
	@Column(name = "ano_lancamento", nullable = false)
	private Integer anoLancamento;
	@NotNull
	@ManyToOne
	private Editora editora;
	@Column(name = "fotos_livro", nullable = false)
	private List<Image> fotosLivro;
	@NotNull
	private Integer quantidade;
	
	public Livro(String tituloLivro, List<Categoria> categoria, String descricao, BigDecimal preco, Float margemX,
			Float margemY, String edicao, Integer anoLancamento, Editora editora, List<Image> fotosLivro,
			Integer quantidade) {
		this.tituloLivro = tituloLivro;
		this.categoria = categoria;
		this.descricao = descricao;
		this.preco = preco;
		this.margemX = margemX;
		this.margemY = margemY;
		this.edicao = edicao;
		this.anoLancamento = anoLancamento;
		this.editora = editora;
		this.fotosLivro = fotosLivro;
		this.quantidade = quantidade;
	}

	public boolean isEmEstoque() {
		return (quantidade > 0) ? true : false;
	}
}
