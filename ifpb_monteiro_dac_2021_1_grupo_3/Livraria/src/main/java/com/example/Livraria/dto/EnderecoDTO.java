package com.example.Livraria.dto;

import javax.validation.constraints.NotBlank;

import com.example.Livraria.model.Endereco;

import lombok.Data;
@Data
public class EnderecoDTO {
	@NotBlank(message = "O cep não pode ser vazio")
	private String cep;
	@NotBlank(message = "A rua não pode ser vazio")
	private String rua;
	@NotBlank(message = "O estado não pode ser vazio")
	private String estado;
	@NotBlank(message = "A cidade  não pode ser vazio")
	private String cidade;
	
	private String complemento="Não Informado";
	@NotBlank(message = "O País não pode ser vazio")
	private String pais;
	@NotBlank(message = "O bairro não pode ser vazio")
	private String bairro;
	@NotBlank(message = "O numero da casa não pode ser vazio")
	private String numeroCasa;

	public Endereco parse() {
		Endereco endereco = new Endereco(cep, rua, estado, cidade, complemento, pais, bairro, numeroCasa, null);
		return endereco;
	}
}
