package com.example.Livraria.fachada;

import com.example.Livraria.model.Endereco;
import com.example.Livraria.repositorio.EnderecoRepositorio;

public class FachadaEndereco {
	private EnderecoRepositorio enderecoRepositorio;

	public void adcionarEdereco(String cep, String rua, String estado, String cidade, String complemento, String pais,
			String bairro, String numeroCasa) {
		Endereco endereco = new Endereco(cep, rua, estado, cidade, complemento, pais, bairro, numeroCasa);
		enderecoRepositorio.save(endereco);
	}
}
