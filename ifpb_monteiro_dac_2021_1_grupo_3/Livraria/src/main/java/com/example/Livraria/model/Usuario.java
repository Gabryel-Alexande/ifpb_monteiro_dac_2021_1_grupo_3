package com.example.Livraria.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import jdk.jfr.BooleanFlag;
import lombok.Data;

@Entity
@Data
public class Usuario {
	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idUsusario;
	@Column(name = "nome_usuario", nullable = false)
	private String nomeUsusario;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String senha;
	@OneToMany
	@JoinColumn(name="idEndereco")
	private List<Endereco> enderecos;
	@Column(nullable = false)
	private String cpf;
	@BooleanFlag
	@Column(nullable = false)
	private boolean admisnistrador;
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "usuario")
	private List<ItemPedido> carrinho;
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Pedido> pedidos;

	public Usuario(String nomeUsusario, String email, String senha, String cpf,
			boolean admisnistrador) {
		super();
		this.nomeUsusario = nomeUsusario;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.admisnistrador = admisnistrador;
	}

	// Este metodo foi criado com a finalidade de resolver o problema da clausula
	// @Data,
	// pois, a mesa cria um metodo plublico que permiti a alteração do atributo
	// indetificador
	// da entendiade, assim trazendo inconsistencia para o codiogo.
	private void setIdUsusario(Long idUsusario) {

	}
	
	public String toString() {
		return "Nome:"+this.nomeUsusario+"\n Cpf:"+this.cpf+"\n Email: "+this.email+"\n É ADM ?"+this.admisnistrador;
	}

	public void adcionarAoCarinho(ItemPedido itemPedido) {
		carrinho.add(itemPedido);
	}

	public void removerDoCarinho(ItemPedido itemPedido) {
		carrinho.remove(itemPedido);
		itemPedido.setUsuario(this);
	}
	@SuppressWarnings("unlikely-arg-type")
	public void removerDoCarinho(Integer indice) {
		carrinho.remove(indice);
	}
	public void adcionarPedido(Pedido pedido) {
		pedidos.add(pedido);
	}

	public void removerPedido(Pedido pedido) {
		pedidos.remove(pedido);
		pedido.removerClienteDoPedido();
	}
	public void adcionarEndereco(Endereco endereco) {
		enderecos.add(endereco);
	}
	public void removerEndereco(Endereco endereco) {
		enderecos.remove(endereco);
	}
	
}
