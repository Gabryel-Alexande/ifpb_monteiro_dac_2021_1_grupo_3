package com.example.Livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.Livraria.dto.AutorDTO;
import com.example.Livraria.dto.CategoriaDTO;
import com.example.Livraria.dto.EditoraDTO;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.services.AutorService;
import com.example.Livraria.services.CategoriaService;
import com.example.Livraria.services.EditoraService;

@Controller
@RequestMapping("/livraria/adm")
public class ControllerAdm {
	@Autowired
	private AutorService autorService;
	@Autowired
	private EditoraService editoraService;
	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/cadastrarEditora")
	public String solicitarCadastroEditora(EditoraDTO editoraDTO) {

		return "/adm/cadastro_editora";
	}

	@GetMapping("/cadastrarCategoria")
	public String solicitarCadastroCategoria(CategoriaDTO categoriaDTO) {

		return "/adm/cadastro_categoria";
	}

	@GetMapping("/cadastrarAutor")
	public String solicitarCadastroAutor(AutorDTO autor) {

		return "/adm/cadastro_autor";
	}

	@PostMapping("/cadastrarAutor")
	public String cadastrarAutor(@Valid AutorDTO autor, BindingResult result) {

		if (result.hasErrors()) {
			return "/adm/cadastro_autor";
		}

		try {
			autorService.cadastrarAutor(autor);
		} catch (LoginException e) {
			e.printStackTrace();
		}

		return "/adm/cadastro_autor";

	}

	@PostMapping("/cadastrarCategoria")
	public String cadastrarCategoria(@Valid CategoriaDTO categoriaDTO, BindingResult result) {
		if (result.hasErrors()) {
			return "/adm/cadastro_categoria";
		}

		categoriaService.criarCategoria(categoriaDTO);
		return "/adm/cadastro_categoria";
	}

	@PostMapping("/cadastrarEditora")
	public String cadastarEditora(@Valid EditoraDTO editoraDTO, BindingResult result) {
		if (result.hasErrors()) {
			return "/adm/cadastro_editora";
		}

		editoraService.cadastrarEditora(editoraDTO);

		return "/adm/cadastro_editora";
	}

}
