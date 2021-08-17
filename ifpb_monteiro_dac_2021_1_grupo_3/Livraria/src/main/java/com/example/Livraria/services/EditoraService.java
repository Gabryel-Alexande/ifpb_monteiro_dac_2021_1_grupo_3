package com.example.Livraria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.example.Livraria.dto.EditoraDTO;
import com.example.Livraria.model.Editora;
import com.example.Livraria.repositorio.EditoraRepositorio;

@Service
public class EditoraService {
	@Autowired
	private EditoraRepositorio editoraRepositorio;

	public void cadastrarEditora(EditoraDTO editoraDTO) {

		Editora editora = editoraDTO.parser();
		if (editoraRepositorio.findByNomeEditora(editoraDTO.getNomeEditora()) != null) {
			throw new DuplicateKeyException("Já existe uma editora com esse nome");
		}

		editoraRepositorio.save(editora);
	}

	public Editora encontarEditora(Long idEditora) {
		return editoraRepositorio.findById(idEditora).get();
	}

	public List<Editora> listarEditoras() {
		return editoraRepositorio.findAll();
	}

	public void editarEditora(String nome, Long idEditora) {
		Editora editora = editoraRepositorio.findByNomeEditora(nome);
		try {
			if (editora.getNomeEditora().equalsIgnoreCase(nome) && editora.getIdEditora() != idEditora) {
				throw new DuplicateKeyException("Nome já existe ");
			}
		} catch (NullPointerException e) {
		}

		editora = editoraRepositorio.findById(idEditora).get();
		editora.setNomeEditora(nome);
		editoraRepositorio.save(editora);
	}

	public void excluirEditora(Long idEditora) {
		editoraRepositorio.deleteById(idEditora);
	}
}
