package com.example.Livraria.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class Editora {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_editora", nullable = false)
	private Long idEditora;
	@Column(name = "nome_editora", nullable = false)
	private String nomeEditora;
	@NotNull
	@ManyToMany
	private List<Livro> livros;
	@NotNull
	@Embedded
	private Endereco endereco;
}
