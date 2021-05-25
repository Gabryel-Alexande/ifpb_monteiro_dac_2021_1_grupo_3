package com.example.Livraria.controllers;

import com.example.Livraria.fachada.FachadaEndereco;

public class ControllerEndereco {
	
	private FachadaEndereco fachadaEndereco = new FachadaEndereco();

	public void adcionarEndereco(String cep, String rua, String estado, String cidade, String complemento, String pais,
			String bairro, String numeroCasa) {
		fachadaEndereco.adcionarEndereco(cep, rua, estado, cidade, complemento, pais, bairro, numeroCasa);
	}
	public Object[] listarEnderecos(){
		return fachadaEndereco.listarEnderecos().toArray();
	}
}
