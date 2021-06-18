package com.example.Livraria.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_pedido")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idPedido;

	@ManyToOne
	@JoinColumn(name ="idUsusario")
	private Usuario usuario;

	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "pedido")
	private List<ItemPedido> itemPedido;

	@Transient
	private BigDecimal preco;

	@Enumerated(EnumType.STRING)
	private EstadoPedido estadoPedido;

	public Pedido(Usuario cliente) {
		super();
		this.usuario = cliente;
		estadoPedido = EstadoPedido.Aberto;
	}

	private Pedido() {
		
	}
	
	public void removerClienteDoPedido() {
		usuario = null;
	}

	// Este metodo foi criado com a finalidade de resolver o problema da clausula
	// @Data,
	// pois, a mesa cria um metodo plublico que permiti a alteração do atributo
	// indetificador
	// da entendiade, assim trazendo inconsistencia para o codiogo.
	private void setIdPedido(Long idPedido) {

	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public String toString(){
		return "Id: "+idPedido+"\nEstado: "+estadoPedido.toString()+"\nCliente: \n"+usuario.toString();
	}
	
}
