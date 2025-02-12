package com.example.Livraria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idEndereco;

	@Column(nullable = false)
	private String cep;

	@Column(nullable = false)
	private String rua;

	@Column(nullable = false)
	private String estado;

	@Column(nullable = false)
	private String cidade;

	@Column(nullable = false)
	private String complemento;

	@Column(nullable = false)
	private String pais;

	@Column(nullable = false)
	private String bairro;

	@Column(name = "numero_casa", nullable = false)
	private String numeroCasa;
	
	@OneToOne
	private Usuario usuario;
	
	public Endereco(){};

	public Endereco(String cep, String rua, String estado, String cidade, String complemento, String pais,
			String bairro, String numeroCasa , Usuario user) {
		super();
		this.cep = cep;
		this.rua = rua;
		this.estado = estado;
		this.cidade = cidade;
		this.complemento = complemento;
		this.pais = pais;
		this.bairro = bairro;
		this.numeroCasa = numeroCasa;
		this.usuario = user;
	}

	// Este metodo foi criado com a finalidade de resolver o problema da clausula
	// @Data,
	// pois, a mesa cria um metodo plublico que permiti a alteração do atributo
	// indetificador
	// da entendiade, assim trazendo inconsistencia para o codiogo.
	@SuppressWarnings("unused")
	private void setIdEndereco(Long idEndereco) {

	}

	public String toString() {
		return "Id: " + idEndereco + "\nCEP: " + cep + "\nRua: " + rua + "\nBairro: " + bairro + "\nEstado: " + estado
				+ "\nCidade: " + cidade + "\nComplemento: " + complemento + "\nPais: " + pais + "\nNúmero da casa: "
				+ numeroCasa;
	}
}
