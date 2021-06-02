package com.example.Livraria.model;

import java.io.Serializable;

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
public class ItemPedido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idItemPedido;
	
	@ManyToOne
	@JoinColumn(name = "LIVRO_FK")
	private Livro livro;
	
	@ManyToOne()
	@JoinColumn(name="idUsusario")
	private Usuario usuario;
	
	@Column(nullable = false)
	private Integer quantidade;

	public ItemPedido(Livro livro, Integer quantidade,Usuario usuario) {
		super();
		this.livro = livro;
		this.quantidade = quantidade;
		this.usuario = usuario;
	}
	private ItemPedido() {}
	
	private void setIdItemPedido(Long id) {
}
}
