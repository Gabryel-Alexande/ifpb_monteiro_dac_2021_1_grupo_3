package com.example.Livraria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Long idEndereco;
	@NotNull
	private String cep;
	@NotNull
	private String rua;
	@NotNull
	private String estado;
	@NotNull
	private String cidade;
	@NotNull
	private String Complemento;
	@NotNull
	private String pais;
	@NotNull
	private String bairro;
	@Column(name = "numero_casa", nullable = false)
	private String numeroCasa;
}
