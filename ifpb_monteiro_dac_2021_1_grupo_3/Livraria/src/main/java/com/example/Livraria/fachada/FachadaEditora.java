package com.example.Livraria.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.model.Editora;
import com.example.Livraria.repositorio.EditoraRepositorio;
import com.example.Livraria.utilitarios.ValidadorNome;
@Service
public class FachadaEditora {
	@Autowired
	private EditoraRepositorio editoraRepositorio;
	
	public void cadastrarEditora(String nomeEditora) {
		if(!ValidadorNome.validarNome(nomeEditora)) {
			throw new IllegalArgumentException("[ERRO] Nome invalido!");
		}
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
//	public void adcionarEndereco(String cep, String rua, String estado, String cidade, String complemento,
//			String pais, String bairro, String numeroCasa,Long idEditora) {
//		Editora editora = editoraRepositorio.findById(idEditora).get();
//		editora.setEndereco(new Endereco(cep, rua, estado, cidade, complemento, pais, bairro, numeroCasa));
//		editoraRepositorio.save(editora);
//	}
//	public void removerEndereco(Long idEditora) {
//		Editora editora = editoraRepositorio.findById(idEditora).get();
//		if(editora.getEndereco()!= null) {
//			editora.setEndereco(null);
//			editoraRepositorio.save(editora);			
//		}
//	}
	public void excluirEditora(Long idEditora) {
		editoraRepositorio.deleteById(idEditora);
	}
}
