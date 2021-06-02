package com.example.Livraria.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Editora implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_editora")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idEditora;

	@Column(name = "nome_editora", nullable = false)
	private String nomeEditora;

//	@OneToMany(mappedBy = "idLivro")
//	private List<Livro> livros;

	@OneToOne
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

//	public void adicionarLivroNaEditora(Livro livro) {
//		livros.add(livro);
//	}
//
//	public void removerLivroNaEditora(Livro livro) {
//		livros.remove(livro);
//	}
	public String toString() {
		return "Editora: " + nomeEditora + " | id: " + idEditora + " Endereço: ";
	}
	private Editora() {}

}
