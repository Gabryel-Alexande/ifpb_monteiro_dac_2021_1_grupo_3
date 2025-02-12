package com.example.Livraria.model;

import java.awt.Image;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javassist.NotFoundException;
import lombok.Data;

@Entity
@Data
public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_livro")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idLivro;

	@Column(name = "titulo_livro", nullable = false)
	private String tituloLivro;

	@Column(nullable = false)
	private String isbn;

	@ManyToMany
	@Column(nullable = false)
	private List<Categoria> categorias;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private BigDecimal preco;

	@Column(nullable = false)
	private String edicao;

	@Column(name = "ano_lancamento", nullable = false)
	private Integer anoLancamento;

	@OneToOne
	@JoinColumn(name = "id_editora")
	private Editora editora;

	@Column(nullable = false)
	private String fotoLivro;

	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "livro_autor", joinColumns = @JoinColumn(name = "idLivro"), inverseJoinColumns = @JoinColumn(name = "idAutor"))
	private List<Autor> autores;

	@Column(nullable = false)
	private Integer quantidadeEstoque;

	@SuppressWarnings("unused")
	public Livro() {
	}

	public Livro(String isbn, String tituloLivro, List<Categoria> categorias, String descricao, BigDecimal preco,
			String edicao, Integer anoLancamento, Editora editora, String fotoLivro, List<Autor> autores,
			Integer quantidadeEstoque) {
		this.isbn = isbn;
		this.tituloLivro = tituloLivro;
		this.categorias = categorias;
		this.descricao = descricao;
		this.preco = preco;
		this.edicao = edicao;
		this.anoLancamento = anoLancamento;
		this.editora = editora;
		this.fotoLivro = fotoLivro;
		this.quantidadeEstoque = quantidadeEstoque;
		this.autores = autores;
	}

	public boolean isEmEstoque() {
		return quantidadeEstoque > 0;
	}

	// Este metodo foi criado com a finalidade de resolver o problema da clausula
	// @Data,
	// pois, a mesa cria um metodo plublico que permiti a alteração do atributo
	// indetificador
	// da entendiade, assim trazendo inconsistencia para o codiogo.
	@SuppressWarnings("unused")
	private void setIdLivro(String idLivro) {

	}

	public void adcionarAutor(Autor autor) {
		autores.add(autor);
	}

	public void adcionarCategoria(Categoria categoria) {
		categorias.add(categoria);
	}

	public void removerCategoria(Categoria categoria) {
		categorias.remove(categoria);
	}

	public void diminuirEtoque(int quantidade) throws NotFoundException {
		if (quantidadeEstoque < quantidade) {
			throw new NotFoundException("Este item nao tem em estoque!");
		}
		quantidadeEstoque -= quantidade;

	}

	public void aumentarEtoque(int quantidade) {
		quantidadeEstoque += quantidade;
	}

	public String toString() {
		return "Titulo: " + tituloLivro + "\nISBN: " + isbn + "\nID: " + idLivro + "\nDescrição: " + descricao
				+ "\nPreço: " + preco + "\nEdição: " + edicao + "\nAno de lançamento: " + anoLancamento + "\nEditora: "
				+ editora.getNomeEditora() + "\nQuantidade em estoque: " + quantidadeEstoque + "\nEm estoque: "
				+ isEmEstoque();
	}

}
