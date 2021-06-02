package com.example.Livraria.fachada;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.model.Endereco;
import com.example.Livraria.repositorio.EnderecoRepositorio;

@Service
public class FachadaEndereco implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	
//	public void adcionarEndereco(String cep, String rua, String estado, String cidade, String complemento, String pais,
//			String bairro, String numeroCasa) {
//		Endereco endereco = new Endereco(cep, rua, estado, cidade, complemento, pais, bairro, numeroCasa);
//		enderecoRepositorio.save(endereco);
//	}
	public List<Endereco> listarEnderecos(){
		return enderecoRepositorio.findAll();
	}
}
