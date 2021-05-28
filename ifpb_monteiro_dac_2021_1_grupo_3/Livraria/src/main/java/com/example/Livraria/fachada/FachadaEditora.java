package com.example.Livraria.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.model.Editora;
import com.example.Livraria.model.Endereco;
import com.example.Livraria.repositorio.EditoraRepositorio;
import com.example.Livraria.repositorio.EnderecoRepositorio;
@Service
public class FachadaEditora {
	@Autowired
	private EditoraRepositorio editoraRepositorio;
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	public void cadastrarEditora(String nomeEditora) {
		Editora editora = new Editora(nomeEditora);
		editoraRepositorio.save(editora);
	}
	
	public List<Editora> listarEditoras(){
		return editoraRepositorio.findAll();
	}
	
	public void adcionarEndereco(Long idEndereco,Long idEditora) {
		Endereco enderecoRegatado= enderecoRepositorio.findById(idEndereco).get();
		Editora editora = editoraRepositorio.findById(idEditora).get();
		editora.setEndereco(enderecoRegatado);
		editoraRepositorio.save(editora);
	}
	public void removerEndereco(Long idEditora) {
		Editora editora = editoraRepositorio.findById(idEditora).get();
		if(editora.getEndereco()!= null) {
			editora.setEndereco(null);
			editoraRepositorio.save(editora);			
		}
	}

}
