package com.example.Livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Livraria.dto.AutorDTO;
import com.example.Livraria.dto.CategoriaDTO;
import com.example.Livraria.dto.EditoraDTO;
import com.example.Livraria.dto.LivroDTO;
import com.example.Livraria.dto.PesquisaDTO;
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

	
	@GetMapping("/cadastrarLivro")
	public String solicitarCadastroLivro(LivroDTO livroDTO, Model modelo) {
		
		
		return "/adm/cadastro_livro";
	}
	
	
	
	
	
	@GetMapping("/cadastrarEditora")
	public String solicitarCadastroEditora(EditoraDTO editoraDTO, Model modelo) {
		
		modelo.addAttribute("editoras",editoraService.listarEditoras());
	
		return "/adm/cadastro_editora";
	}

	
	
	
	@GetMapping("/cadastrarCategoria")
	public String solicitarCadastroCategoria(CategoriaDTO categoriaDTO, Model modelo) {
		modelo.addAttribute("categorias",categoriaService.listarCategoria());
		return "/adm/cadastro_categoria";
	}
	
	

	@GetMapping("/cadastrarAutor")
	public String solicitarCadastroAutor(AutorDTO autor, Model modelo) {
		modelo.addAttribute("autores",autorService.listarAutores());
		
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

		return "redirect:/livraria/adm/cadastrarAutor";

	}
	
	@PostMapping("/deletarAutor")
	public String deletarAutor(@RequestParam(name = "id") Long idAutor) {
		autorService.removerAutor(idAutor);
		return "redirect:/livraria/adm/cadastrarAutor";
	}
	
	@PostMapping("/cadastrarCategoria")
	public String cadastrarCategoria(@Valid CategoriaDTO categoriaDTO, BindingResult result) {
		if (result.hasErrors()) {
			return "/adm/cadastro_categoria";
		}

		categoriaService.criarCategoria(categoriaDTO);
		return "redirect:/livraria/adm/cadastrarCategoria";
	}
	
	@PostMapping("/deletarCategoria")
	public String deletarCategoria(@RequestParam(name = "id") Long idCategoria ) {
		
		categoriaService.removerCategoria(idCategoria);
		return "redirect:/livraria/adm/cadastrarCategoria";
	}

	
	@PostMapping("/cadastrarEditora")
	public String cadastarEditora(@Valid EditoraDTO editoraDTO, BindingResult result) {
		if (result.hasErrors()) {
			return "/adm/cadastro_editora";
		}

		editoraService.cadastrarEditora(editoraDTO);

		return "redirect:/livraria/adm/cadastrarEditora";
	}
	
	
	@PostMapping("/deletarEditora")
	public String deletarEditora(@RequestParam(name = "id") Long idEditora ) {
		editoraService.excluirEditora(idEditora);
		
		return "redirect:/livraria/adm/cadastrarEditora";
	}
	
	
	
	

}
