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
	private int anoLancamento;
	@NotNull
	@ManyToOne
	private Editora editora;
	@Column(name = "fotos_livro", nullable = false)
	private List<Image> fotosLivro;
	@NotNull
	private Integer quantidade;

	public boolean isEmEstoque() {
		return (quantidade > 0) ? true : false;
	}
}
