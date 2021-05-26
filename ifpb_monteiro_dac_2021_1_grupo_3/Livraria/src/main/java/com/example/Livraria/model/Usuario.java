package com.example.Livraria.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@Column(name = "id_usuario")
	private Long idUsusario = System.currentTimeMillis();
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String senha;
	@OneToMany
	@JoinColumn(name="idEndereco")
	private List<Endereco> endereco;
	@Column(nullable = false)
	private String cpf;
	@BooleanFlag
	@Column(nullable = false)
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
	
	public String toString() {
		return "Nome:"+this.nomeUsusario+"\n Cpf:"+this.cpf+"\n Email: "+this.email+"\n É ADM ?"+this.admisnistrador;
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
