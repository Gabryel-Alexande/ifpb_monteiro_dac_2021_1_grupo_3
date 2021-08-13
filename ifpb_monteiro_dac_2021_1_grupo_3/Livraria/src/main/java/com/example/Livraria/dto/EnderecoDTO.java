package com.example.Livraria.dto;

import javax.validation.constraints.NotBlank;

import com.example.Livraria.model.Endereco;

import lombok.Data;
@Data
public class EnderecoDTO {
	@NotBlank
	private String cep;
	@NotBlank
	private String rua;
	@NotBlank
	private String estado;
	@NotBlank
	private String cidade;
	
	private String complemento="Não Informado";
	@NotBlank
	private String pais;
	@NotBlank
	private String bairro;
	@NotBlank
	private String numeroCasa;

	public Endereco parse() {
		Endereco endereco = new Endereco(cep, rua, estado, cidade, complemento, pais, bairro, numeroCasa, null);
		return endereco;
	}
}
