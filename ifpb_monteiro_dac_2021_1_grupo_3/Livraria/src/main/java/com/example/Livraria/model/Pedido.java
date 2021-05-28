package com.example.Livraria.model;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class Pedido {
	@Id
	@Column(name = "id_pedido")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idPedido;
	@ManyToOne
	private Usuario cliente;
	@OneToMany(mappedBy = "idItemPedido")
	private List<ItemPedido> itemPedido;
	@Transient
	private BigDecimal preco;

	public Pedido(Usuario cliente, List<ItemPedido> itemPedido) {
		super();
		this.cliente = cliente;
		this.itemPedido = itemPedido;
		cliente.adcionarPedido(this);
		for (ItemPedido item : itemPedido) {
			preco = preco.add(item.getLivro().getPreco().multiply(new BigDecimal(item.getQuantidade())));
		}
	}

	public void removerClienteDoPedido() {
		cliente = null;
	}

	// Este metodo foi criado com a finalidade de resolver o problema da clausula
	// @Data,
	// pois, a mesa cria um metodo plublico que permiti a alteração do atributo
	// indetificador
	// da entendiade, assim trazendo inconsistencia para o codiogo.
	private void setIdPedido(Long idPedido) {

	}

	public void setPreco(BigDecimal preco) {

	}
}
