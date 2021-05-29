package com.example.Livraria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Endereco implements Serializable {
	/**
	 * 
	 */
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
	
	public Endereco(String cep, String rua, String estado, String cidade, String complemento, String pais,
			String bairro, String numeroCasa) {
		super();
		this.cep = cep;
		this.rua = rua;
		this.estado = estado;
		this.cidade = cidade;
		this.complemento = complemento;
		this.pais = pais;
		this.bairro = bairro;
		this.numeroCasa = numeroCasa;
	}
	
	private Endereco() {};

	// Este metodo foi criado com a finalidade de resolver o problema da clausula
	// @Data,
	// pois, a mesa cria um metodo plublico que permiti a alteração do atributo
	// indetificador
	// da entendiade, assim trazendo inconsistencia para o codiogo.
	private void setIdEndereco(Long idEndereco) {

	}
}
