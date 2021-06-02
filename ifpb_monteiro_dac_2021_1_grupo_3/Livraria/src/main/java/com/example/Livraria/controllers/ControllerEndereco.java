package com.example.Livraria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.Livraria.fachada.FachadaEndereco;
@Controller
public class ControllerEndereco {
	@Autowired
	private FachadaEndereco fachadaEndereco;

//	public void adcionarEndereco(String cep, String rua, String estado, String cidade, String complemento, String pais,
//			String bairro, String numeroCasa) {
//		fachadaEndereco.adcionarEndereco(cep, rua, estado, cidade, complemento, pais, bairro, numeroCasa);
//	}
	public Object[] listarEnderecos(){
		return fachadaEndereco.listarEnderecos().toArray();
	}
}
