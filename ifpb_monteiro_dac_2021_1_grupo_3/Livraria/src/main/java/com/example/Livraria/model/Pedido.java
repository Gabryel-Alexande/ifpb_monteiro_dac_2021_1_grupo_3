package com.example.Livraria.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;


@Entity
@Data
public class Pedido {
	@Id
	@Column(name = "id_pedido")
	private Long idPedido = System.currentTimeMillis();
	@ManyToOne
	private Usuario cliente;
	@ManyToMany
	private List<Livro> livros;
	@Transient
	private BigDecimal preco;
	
	public Pedido(Usuario cliente,List<Livro> livros) {
		super();
		this.cliente = cliente;
		this.livros = livros;
		cliente.adcionarPedido(this);
		for (Livro livro : livros) {
			preco=preco.add(livro.getPreco());
		}
	}
	public void removerClienteDoPedido() {
		cliente=null;
	}
	//Este metodo foi criado com a finalidade de resolver o problema da clausula @Data,
	//pois, a mesa cria um metodo plublico que permiti a alteração do atributo indetificador
	//da entendiade, assim trazendo inconsistencia para o codiogo.
	private void setIdPedido(Long idPedido){
		
	}	
	public void setPreco(BigDecimal preco){

	}
	public List<Endereco> getEndereco() {
		return cliente.getEndereco();
	}
}
