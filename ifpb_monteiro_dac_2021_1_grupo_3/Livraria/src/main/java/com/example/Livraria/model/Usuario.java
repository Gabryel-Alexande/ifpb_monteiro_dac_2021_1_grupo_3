package com.example.Livraria.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

import jdk.jfr.BooleanFlag;
import lombok.Data;

@Entity
@Data
public class Usuario {
	@Column(name = "nome_usuario", nullable = false)
	private String nomeUsusario;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long idUsusario;
	@NotNull
	private String email;
	@NotNull
	private String senha;
	@NotNull
	@Embedded
	private Endereco endereco;
	@NotNull
	private String cpf;
	@NotNull
	@BooleanFlag
	private boolean admisnistrador;
	@ManyToMany
	private List<Livro> carrinho;
	@OneToMany
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

	public void adcionarAoCarinho(Livro livro) {
		carrinho.add(livro);
	}

	public void removerDoCarinho(Livro livro) {
		carrinho.remove(livro);
	}

	public void adcionarPedido(Pedido pedido) {
		pedidos.add(pedido);
	}

	public void removerPedido(Pedido pedido) {
		pedidos.remove(pedido);
		pedido.removerClienteDoPedido();
	}
}
