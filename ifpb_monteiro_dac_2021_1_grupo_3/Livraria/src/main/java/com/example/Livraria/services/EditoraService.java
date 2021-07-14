package com.example.Livraria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.model.Editora;
import com.example.Livraria.repositorio.EditoraRepositorio;
@Service
public class EditoraService {
	@Autowired
	private EditoraRepositorio editoraRepositorio;
	
	public void cadastrarEditora(String nomeEditora) {

		Editora editora = new Editora(nomeEditora);
		editoraRepositorio.save(editora);
	}
	
	public List<Editora> listarEditoras(){
		return editoraRepositorio.findAll();
	}
	public void editarEndereco(String nome,Long idEditora) {
		Editora editora = editoraRepositorio.findById(idEditora).get();
		editora.setNomeEditora(nome);
		editoraRepositorio.save(editora);
	}
	public void excluirEditora(Long idEditora) {
		editoraRepositorio.deleteById(idEditora);
	}
}
