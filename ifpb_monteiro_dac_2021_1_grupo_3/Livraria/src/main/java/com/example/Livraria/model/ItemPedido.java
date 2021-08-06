package com.example.Livraria.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idItemPedido;

	@OneToOne
	@JoinColumn(name = "id_livro")
	private Livro livro;

	@ManyToOne
	@JoinColumn(name = "idPedido")
	private Pedido pedido;

	@Column(nullable = false)
	private Integer quantidade;

	@SuppressWarnings("unused")
	private ItemPedido() {
	}
	public ItemPedido(Livro livro, Integer quantidade, Pedido pedido) {
		super();
		this.livro = livro;
		this.quantidade = quantidade;
		this.pedido = pedido;
	}


	@SuppressWarnings("unused")
	private void setIdItemPedido(Long id) {

	}

	public float getPreco() {
		return livro.getPreco().floatValue()*quantidade;
	}

	public String toString() {
		return "Id: " + idItemPedido + "\nLivro: " + livro.toString() + "\nQuantidade: " + quantidade;
	}
}
