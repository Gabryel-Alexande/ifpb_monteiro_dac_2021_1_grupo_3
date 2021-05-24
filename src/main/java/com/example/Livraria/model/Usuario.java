package com.example.Livraria.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	public Usuario(String nomeUsusario, String email, String senha, Endereco endereco, String cpf,
			boolean admisnistrador) {
		super();
		this.nomeUsusario = nomeUsusario;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
		this.cpf = cpf;
		this.admisnistrador = admisnistrador;
	}

}
