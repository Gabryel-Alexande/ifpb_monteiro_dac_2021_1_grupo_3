package com.example.Livraria.model;

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
public class Autor {
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
	@Column(name = "livros_publicados", nullable = false)
	@ManyToMany
	private List<Livro> lirvosPublicados;
	public Autor(String nomeAutor, String email, String senha) {
		super();
		this.nomeAutor = nomeAutor;
		this.email = email;
		this.senha = senha;
		
	}
	//Este metodo foi criado com a finalidade de resolver o problema da clausula @Data,
	//pois, a mesa cria um metodo plublico que permiti a alteração do atributo indetificador
	//da entendiade, assim trazendo inconsistencia para o codiogo.
	private void setAutorId(Long autorId){
		
	}	

	public void adcionarLivro(Livro livro) {
		lirvosPublicados.add(livro);
	}
}
