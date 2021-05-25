package com.example.Livraria.controllers;

import com.example.Livraria.fachada.FachadaCategoria;

public class ControllerCategoria {
	
	private FachadaCategoria fachadaCategoria= new FachadaCategoria();

	public void criarCategoria(String nome) {
		fachadaCategoria.criarCategoria(nome);
	}

	public void editarCategoria(Long id, String nome) {
		fachadaCategoria.editarCategoria(id, nome);
	}
	public Object[] listarCategoria(Long id,String nome) {
		return fachadaCategoria.listarCategoria().toArray();
	}
	public void excluirCategoria(Long id) {
		fachadaCategoria.excluirCategoria(id);
	}
}
