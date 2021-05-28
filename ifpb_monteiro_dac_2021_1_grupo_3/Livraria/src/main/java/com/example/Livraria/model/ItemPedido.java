package com.example.Livraria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ItemPedido {
	@Id	
	@Column(name ="id_item_pedido")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idItemPedido;
	@ManyToOne
	@JoinColumn(name = "LIVRO_FK")
	private Livro livro;
	@ManyToOne
	private Usuario usuario;
	@Column(nullable =false)
	private Integer quantidade;
	public ItemPedido(Livro livro, Integer quantidade) {
		super();
		this.livro = livro;
		this.quantidade = quantidade;
	}
	private void setIdItemPedido(Long id) {
		
	}
}
