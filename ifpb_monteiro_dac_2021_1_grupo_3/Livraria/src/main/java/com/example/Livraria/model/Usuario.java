package com.example.Livraria.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;

import jdk.jfr.BooleanFlag;
import lombok.Data;

@Entity
@Data

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
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
	
	@OneToMany(cascade = CascadeType.MERGE,mappedBy = "usuario")
	private List<Endereco> enderecos;
	
	@Column(nullable = false)
	private String cpf;

	@Column(nullable = false)
	private Integer anoDeNascimento;
	
	@BooleanFlag
	@Column(nullable = false)
	private boolean admisnistrador;

	@OneToMany(cascade = CascadeType.MERGE,mappedBy = "usuario")
	private List<Pedido> pedidos;

	
	private Usuario () {}
	
	public Usuario(String nomeUsusario, String email, String senha, String cpf,
			boolean admisnistrador,Integer anoDeNascimento) {
		super();
		this.nomeUsusario = nomeUsusario;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.admisnistrador = admisnistrador;
		this.anoDeNascimento=anoDeNascimento;
	}

	// Este metodo foi criado com a finalidade de resolver o problema da clausula
	// @Data,
	// pois, a mesa cria um metodo plublico que permiti a alteração do atributo
	// indetificador
	// da entendiade, assim trazendo inconsistencia para o codiogo.
	private void setIdUsusario(Long idUsusario) {

	}
	
	public String toString() {

		return "Id: "+idUsusario+"\nNome: "+this.nomeUsusario+"\nCpf: "+this.cpf+"\nEmail: "+this.email+"\nÉ ADM ? "+this.admisnistrador;
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

	public Pedido getCarrinho() {
		for (Pedido pedido : pedidos) {
			if(pedido.getEstadoPedido()==EstadoPedido.Aberto) {
				return pedido;
			}
		}
		return null;
	}
	
}
