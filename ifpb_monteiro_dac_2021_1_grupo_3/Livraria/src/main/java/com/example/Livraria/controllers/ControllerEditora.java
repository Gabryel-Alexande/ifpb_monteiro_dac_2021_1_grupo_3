package com.example.Livraria.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.example.Livraria.fachada.FachadaEditora;
import com.example.Livraria.model.Editora;


@Controller
public class ControllerEditora {

	private FachadaEditora fachadaEditora = new FachadaEditora();

	public void cadastrarEditora(String nomeEditora) {
		fachadaEditora.cadastrarEditora(nomeEditora);
	}

	public List<Editora> listarEditoras() {
		return fachadaEditora.listarEditoras();
	}

	public void adcionarEndereco(Long idEndereco, Long idEditora) {
		fachadaEditora.adcionarEndereco(idEndereco, idEditora);
	}

	public void removerEndereco(Long idEditora) {
		fachadaEditora.removerEndereco(idEditora);
	}
}
