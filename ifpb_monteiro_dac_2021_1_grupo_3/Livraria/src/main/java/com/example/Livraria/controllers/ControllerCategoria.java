package com.example.Livraria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.Livraria.fachada.FachadaCategoria;
@Controller
public class ControllerCategoria {
	@Autowired
	private FachadaCategoria fachadaCategoria;

	public void criarCategoria(String nome) {
		fachadaCategoria.criarCategoria(nome);
	}

	public void editarCategoria(Long id, String nome) {
		fachadaCategoria.editarCategoria(id, nome);
	}
	public Object[] listarCategoria() {
		return fachadaCategoria.listarCategoria().toArray();
	}
	public void excluirCategoria(Long id) {
		fachadaCategoria.excluirCategoria(id);
	}
}
