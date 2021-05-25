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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
public class Livro {
	
	@Column(name = "titulo_livro", nullable = false)
	private String tituloLivro;
	@Id
	private String isbn;
	@NotNull
	@ManyToMany
	private List<Categoria> categorias;
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
	@ManyToMany
	private List<Autor> autores;
	@NotNull
	private Integer quantidadeEstoque;
	
	public Livro(String isbn,String tituloLivro, List<Categoria> categorias, String descricao, BigDecimal preco, Float margemX,
			Float margemY, String edicao, Integer anoLancamento, Editora editora, List<Image> fotosLivro,
			 List<Autor> autores,Integer quantidadeEstoque) {
		this.isbn=isbn;
		this.tituloLivro = tituloLivro;
		this.categorias = categorias;
		this.descricao = descricao;
		this.preco = preco;
		this.margemX = margemX;
		this.margemY = margemY;
		this.edicao = edicao;
		this.anoLancamento = anoLancamento;
		this.editora = editora;
		this.fotosLivro = fotosLivro;
		this.quantidadeEstoque = quantidadeEstoque;
		this.autores=autores;
	}

	public boolean isEmEstoque() {
		return (quantidadeEstoque > 0) ? true : false;
	}
	
	//Este metodo foi criado com a finalidade de resolver o problema da clausula @Data,
	//pois, a mesa cria um metodo plublico que permiti a alteração do atributo indetificador
	//da entendiade, assim trazendo inconsistencia para o codiogo.
	private void setIsbn(String isbn) {
		
	}
	public void adcionarAutor(Autor autor) {
		autores.add(autor);
	}
	public void adcionarFoto(Image imagem) {
		fotosLivro.add(imagem);
	}
	public void removerFoto(Image imagem) {
		fotosLivro.remove(imagem);
	}

	public void adcionarCategoria(Categoria categoria) {
		categorias.add(categoria);
	}

	public void removerCategoria(Categoria categoria) {
		categorias.remove(categoria);
	}
	public void diminuirEtoque() {
		quantidadeEstoque--;
	}
	public void aumentarEtoque() {
		quantidadeEstoque++;
	}
}
