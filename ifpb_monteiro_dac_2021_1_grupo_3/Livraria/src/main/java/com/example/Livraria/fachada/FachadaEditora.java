package com.example.Livraria.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.model.Editora;
import com.example.Livraria.model.Endereco;
import com.example.Livraria.model.Livro;
import com.example.Livraria.model.Usuario;
import com.example.Livraria.repositorio.EditoraRepositorio;
import com.example.Livraria.repositorio.EnderecoRepositorio;
import com.example.Livraria.repositorio.LivroRepositorio;
@Service
public class FachadaEditora {
	@Autowired
	private EditoraRepositorio editoraRepositorio;
	@Autowired
	private LivroRepositorio livroRepositorio;
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
		Endereco enderecoRegatado= enderecoRepositorio.findByIdEndereco(idEndereco);
		Editora editora = editoraRepositorio.findByIdEditora(idEditora);
		editora.setEndereco(enderecoRegatado);
		editoraRepositorio.save(editora);
	}
	public void removerEndereco(Long idEditora) {
		Editora editora = editoraRepositorio.findByIdEditora(idEditora);
		if(editora.getEndereco()!= null) {
			editora.setEndereco(null);
			editoraRepositorio.save(editora);			
		}
	}

}
