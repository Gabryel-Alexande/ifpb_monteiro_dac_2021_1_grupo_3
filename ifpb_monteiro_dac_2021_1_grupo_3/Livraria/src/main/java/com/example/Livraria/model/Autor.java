package com.example.Livraria.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Autor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_autor")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idAutor;

	@Column(name = "nome_autor", nullable = false)
	@Size(min=3,max=50)
	private String nomeAutor;
	
	@ManyToMany(mappedBy = "autores")
	private List<Livro> lirvosPublicados;

	public Autor(String nomeAutor) {
		super();
		this.nomeAutor = nomeAutor;

	}

	@SuppressWarnings("unused")
	private Autor() {
	}

	// Este metodo foi criado com a finalidade de resolver o problema da clausula
	// @Data,
	// pois, a mesa cria um metodo plublico que permiti a alteração do atributo
	// indetificador
	// da entendiade, assim trazendo inconsistencia para o codiogo.
	@SuppressWarnings("unused")
	private void setAutorId(Long autorId) {

	}

	public void adcionarLivro(Livro livro) {
		lirvosPublicados.add(livro);
	}

	public String toString() {
		return "Id: " + idAutor + "\nNome do autor: " + nomeAutor ;
	}
}
