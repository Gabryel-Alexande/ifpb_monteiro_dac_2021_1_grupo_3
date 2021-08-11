package com.example.Livraria.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_pedido")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idPedido;

	@ManyToOne
	@JoinColumn(name = "idUsusario")
	private Usuario usuario;

	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "pedido")
	private List<ItemPedido> itemPedido;

	private BigDecimal preco;

	@Enumerated(EnumType.STRING)
	private EstadoPedido estadoPedido;
	
	@OneToOne
	@JoinColumn(name = "idMetodoPagamento")
	private MetodoPagamento metodoPagamento;

	@SuppressWarnings("unused")
	private Pedido() {

	}

	public Pedido(Usuario cliente) {
		super();

		this.preco = new BigDecimal(0);
		this.usuario = cliente;
		estadoPedido = EstadoPedido.Aberto;
		this.itemPedido = new ArrayList<ItemPedido>();
	}

	public void removerClienteDoPedido() {
		usuario = null;
	}

	// Este metodo foi criado com a finalidade de resolver o problema da clausula
	// @Data,
	// pois, a mesa cria um metodo plublico que permiti a alteração do atributo
	// indetificador
	// da entendiade, assim trazendo inconsistencia para o codiogo.
	@SuppressWarnings("unused")
	private void setIdPedido(Long idPedido) {

	}
	public void removerPreco(ItemPedido item) {
		float preco = this.preco.floatValue();

		preco -= item.getPreco();
		this.preco = new BigDecimal(preco);
	}


	public void addPreco(ItemPedido item) {
		float preco = this.preco.floatValue();

		preco += item.getPreco();
		this.preco = new BigDecimal(preco);
	}
	
	
	public void atualizarValor() {
		
		float num = 0;
		for (ItemPedido itemPedido2 : itemPedido) {
			num+=itemPedido2.getPreco();
		}
		BigDecimal valor = new BigDecimal(num);
		
		preco = valor.divide(BigDecimal.ONE,2,BigDecimal.ROUND_HALF_UP);
		
	}

	public String toString() {
		return "Id: " + idPedido + "\nEstado: " + estadoPedido.toString() + "\nCliente: \n" + usuario.toString();
	}

}
