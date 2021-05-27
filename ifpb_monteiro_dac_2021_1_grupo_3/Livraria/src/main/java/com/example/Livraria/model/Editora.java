package com.example.Livraria.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Editora {
	@Id
	@Column(name = "id_editora")
	private Long idEditora = System.currentTimeMillis();

	@Column(name = "nome_editora", nullable = false)
	private String nomeEditora;

	@OneToMany(mappedBy = "isbn")
	private List<Livro> livros;

	@ManyToOne
	private Endereco endereco;

	// Este metodo foi criado com a finalidade de resolver o problema da clausula
	// @Data, pois, a mesa cria um metodo plublico que permiti a alteração do
	// atributo indetificadorda entendiade, assim trazendo inconsistencia para o
	// codiogo.
	private void setIdEditora(Long idEditora) {

	}

	public Editora(String nomeEditora, Endereco endereco) {
		super();
		this.nomeEditora = nomeEditora;
	}

	public Editora(String nomeEditora) {
		super();
		this.nomeEditora = nomeEditora;

	}

	public void adicionarLivroNaEditora(Livro livro) {
		livros.add(livro);
	}

	public void removerLivroNaEditora(Livro livro) {
		livros.remove(livro);
	}

}
