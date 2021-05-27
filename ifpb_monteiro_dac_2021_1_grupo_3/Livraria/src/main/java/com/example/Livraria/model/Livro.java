package com.example.Livraria.model;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import javassist.NotFoundException;
import lombok.Data;

@Entity
@Data
public class Livro {
	@Id
	@Column(name="id_livro")
	private Long idLivro= System.currentTimeMillis();
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
	
	@ManyToOne
	@JoinColumn(name="id_editora")
	private Editora editora;
	
	@Transient
	private List<Image> fotosLivro;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE} )
	@JoinTable(name = "LIVROAUTOR",  joinColumns = @JoinColumn(name = "LIVROID"), 
			inverseJoinColumns = @JoinColumn(name = "AUTORID"))
	private List<Autor> autores;
	@Column(nullable = false)
	private Integer quantidadeEstoque;
	
	public Livro(String isbn,String tituloLivro, List<Categoria> categorias, String descricao, BigDecimal preco,
			 String edicao, Integer anoLancamento, Editora editora, List<Image> fotosLivro,
			 List<Autor> autores,Integer quantidadeEstoque) {
		this.isbn=isbn;
		this.tituloLivro = tituloLivro;
		this.categorias = categorias;
		this.descricao = descricao;
		this.preco = preco;
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
	public void diminuirEtoque(int quantidade)throws NotFoundException {
		if(quantidadeEstoque<quantidade) {
			throw new NotFoundException("Este item nao tem em estoque!");
		}
		quantidadeEstoque--;
	}
	public void aumentarEtoque() {
		quantidadeEstoque++;
	}
}
