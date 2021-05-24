package com.example.Livraria.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class Autor {
	@Column(name = "nome_autor", nullable = false)
	private String nomeAutor;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_autor")
	private Long idAutor;
	@NotNull
	private String email;
	@NotNull
	private String senha;
	@Column(name = "livros_publicados", nullable = false)
	@ManyToMany
	private List<Livro> lirvosPublicados;
	public Autor(String nomeAutor, String email, String senha, List<Livro> lirvosPublicados) {
		super();
		this.nomeAutor = nomeAutor;
		this.email = email;
		this.senha = senha;
		this.lirvosPublicados = lirvosPublicados;
	}
	
}
