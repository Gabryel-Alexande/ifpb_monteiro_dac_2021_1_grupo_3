package com.example.Livraria.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ItemPedido {
	@Id	
	@Column(name ="id_item_pedido")
	private Long idItemPedido=System.currentTimeMillis();
	@ManyToOne
	private Livro livro;
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
