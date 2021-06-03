
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
public class Categoria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id_categoria")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idCategortia;
	@Column(name = "nome_categoria", nullable = false)
	private String nomeCategoria;
	@Column(nullable = false)
	@ManyToMany
	private List<Livro> livros;
	
	public Categoria(String nomeCategoria) {
		super();
		this.nomeCategoria = nomeCategoria;
	}	
	//Este metodo foi criado com a finalidade de resolver o problema da clausula @Data,
	//pois, a mesa cria um metodo plublico que permiti a alteração do atributo indetificador
	//da entendiade, assim trazendo inconsistencia para o codiogo.
	private void setIdCategortia(Long idCategortia){
		
	}
	public void adcionarLivro(Livro livro) {
		livros.add(livro);
	}
	public void removerLivro(Livro livro) {
		livros.remove(livro);
	}
	public String toString() {
		return "Id: " + idCategortia+"\nCategoira: " + nomeCategoria;
	}
	private Categoria() {}
}
