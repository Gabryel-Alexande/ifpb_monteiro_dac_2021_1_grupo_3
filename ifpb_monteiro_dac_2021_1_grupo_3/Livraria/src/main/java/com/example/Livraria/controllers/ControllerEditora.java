package com.example.Livraria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.Livraria.fachada.FachadaEditora;
import com.example.Livraria.model.Editora;


@Controller
public class ControllerEditora {
	@Autowired
	private FachadaEditora fachadaEditora;

	public void cadastrarEditora(String nomeEditora) {
		fachadaEditora.cadastrarEditora(nomeEditora);
	}

	public List<Editora> listarEditoras() {
		return fachadaEditora.listarEditoras();
	}

	public void adcionarEndereco(String cep, String rua, String estado, String cidade, String complemento,
			String pais, String bairro, String numeroCasa, Long idEditora) {
		fachadaEditora.adcionarEndereco(cep, rua, estado, cidade, complemento, pais, bairro, numeroCasa, idEditora);
	}
	
	public void editarEndereco(String nome,Long idEditora) {
		fachadaEditora.editarEndereco(nome, idEditora);
	}
	public void removerEndereco(Long idEditora) {
		fachadaEditora.removerEndereco(idEditora);
	}
	public void excluirEditora(Long idEditora) {
		fachadaEditora.excluirEditora(idEditora);
	}
}
