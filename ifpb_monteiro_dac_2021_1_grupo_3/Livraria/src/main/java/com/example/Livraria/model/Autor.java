package com.example.Livraria.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
	private String nomeAutor;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String senha;

	@ManyToMany(mappedBy = "autores")
	private List<Livro> lirvosPublicados;

	public Autor(String nomeAutor, String email, String senha) {
		super();
		this.nomeAutor = nomeAutor;
		this.email = email;
		this.senha = senha;

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
		return "Id: " + idAutor + "\nNome do autor: " + nomeAutor + "\nEmail: " + email + "\nSenha: " + senha;
	}
}
