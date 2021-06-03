package com.example.Livraria.model;

import java.io.Serializable;
import java.math.BigDecimal;

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

	public ItemPedido(Livro livro, Integer quantidade, Pedido pedido) {
		super();
		this.livro = livro;
		this.quantidade = quantidade;
		this.pedido = pedido;
		pedido.setPreco(pedido.getPreco().add(livro.getPreco().multiply(new BigDecimal(quantidade))));
	}

	private ItemPedido() {
	}

	private void setIdItemPedido(Long id) {
	
	}
	public BigDecimal getPreco() {
		return livro.getPreco().multiply(new BigDecimal(quantidade));
	}
	
}
