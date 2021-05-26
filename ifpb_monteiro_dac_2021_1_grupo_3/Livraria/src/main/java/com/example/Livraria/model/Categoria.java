
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
public class Categoria {
	@Id
	@Column(name = "id_categoria")
	private Long idCategortia = System.currentTimeMillis();
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
}
