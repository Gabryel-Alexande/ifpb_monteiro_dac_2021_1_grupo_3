package com.example.Livraria.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Livraria.dto.AutorDTO;
import com.example.Livraria.dto.CategoriaDTO;
import com.example.Livraria.dto.EditoraDTO;
import com.example.Livraria.dto.LivroDTO;
import com.example.Livraria.dto.PesquisaDTO;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.facade.LivroFacade;
import com.example.Livraria.model.Autor;
import com.example.Livraria.services.AutorService;
import com.example.Livraria.services.CategoriaService;
import com.example.Livraria.services.EditoraService;
import com.example.Livraria.services.LivroService;

@Controller
@RequestMapping("/livraria/adm")
public class ControllerAdm {
	@Autowired
	private AutorService autorService;
	@Autowired
	private EditoraService editoraService;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	LivroService livroService;
	
	@Autowired
	LivroFacade livroFacade;
	
	private Integer paginaAutor=1;
	
	private Long idCategoriaEditar;
	private Long idEditoraEditar;
	private Long idAutorEditar;
	

	
	private Long idLivroEditar;
	
	@GetMapping("/cadastrarLivro")
	public String solicitarCadastroLivro(LivroDTO livroDTO, Model modelo) {
		modelo.addAttribute("categorias",categoriaService.listarCategoria());
		modelo.addAttribute("editoras",editoraService.listarEditoras());
		modelo.addAttribute("autores",autorService.listarAutores());
		return "/adm/cadastro_livro";
	}
	
	@PostMapping("/cadastrarLivro")
	public String cadastrarLivro(@Valid LivroDTO livroDTO, BindingResult result) {
		
		if(result.hasErrors()) {
			return "/adm/cadastro_livro";
		}
		
		
		
		livroService.cadastrarLivro(livroDTO);
		
		return"redirect:/livraria/publico/home";
		
	}
	
	
	@GetMapping("/editarLivro")
	public String solicitarEditarLivro(@RequestParam(name = "id")Long idLivro,Model modelo) {
		
		
	 	LivroDTO livro =  livroFacade.transformarEmDTO(livroService.buscarLivroPorId(idLivro));
	 	this.idLivroEditar = livro.getIdLivro();
		modelo.addAttribute("livroDTO",livro);
		modelo.addAttribute("categorias",categoriaService.listarCategoria());
		modelo.addAttribute("editoras",editoraService.listarEditoras());
		modelo.addAttribute("autores",autorService.listarAutores());
		
		return "/adm/editar_livro";
		
	}
	
	@PostMapping("/editarLivro")
	public String editarLivro(@Valid LivroDTO livroDTO , BindingResult result) {
		if(result.hasErrors()) {
			return "/adm/editar_livro";
		}
		
		livroService.alterarLivro(livroDTO ,this.idLivroEditar);
		
		return "redirect:/livraria/publico/Retornar_Home";
	}
	
	@PostMapping("/deletarLivro")
	public String deletarLivro(@RequestParam(name = "id") Long idLivro , Model modelo) {
		livroService.removerLivro(idLivro);
		
		
		
		return "redirect:/livraria/publico/home";
		
	}
	
	
	
	
	
	@GetMapping("/cadastrarEditora")
	public String solicitarCadastroEditora(EditoraDTO editoraDTO, Model modelo) {
		
		modelo.addAttribute("editoras",editoraService.listarEditoras());
	
		return "/adm/cadastro_editora";
	}
	
	
	@GetMapping("/editarCategoria")
	public String solicitarEditarCategoria(@RequestParam(name = "id") Long idCategoria, Model modelo,CategoriaDTO cat) {
		this.idCategoriaEditar = idCategoria;
		modelo.addAttribute("categoriaDTO",categoriaService.encontarCategoria(idCategoria));
		
		return "/adm/editar_categoria";
		
	}
	
	
	
	@PostMapping("/editarCategoria")
	public String editarCategoria(@Valid CategoriaDTO categoriaDTO, BindingResult result ) {
		if(result.hasErrors()) {
			return "/adm/editar_categoria";
		}
		
		categoriaService.editarCategoria(idCategoriaEditar,categoriaDTO.getNomeCategoria());
		
		return "redirect:/livraria/adm/cadastrarCategoria";
		
	}
	
	
	
	
	

	
	
	
	@GetMapping("/cadastrarCategoria")
	public String solicitarCadastroCategoria(CategoriaDTO categoriaDTO, Model modelo) {
		modelo.addAttribute("categorias",categoriaService.listarCategoria());
		return "/adm/cadastro_categoria";
	}
	
	

	@GetMapping("/cadastrarAutor")
	public String solicitarCadastroAutor(AutorDTO autor, Model modelo) {
		
		Page<Autor> autores = autorService.listarAutores(paginaAutor);
		
		modelo.addAttribute("autores",autores);
		modelo.addAttribute("numeracao",livroFacade.criarBotoes(autores.getTotalPages(),paginaAutor));
		modelo.addAttribute("fim", autores.getTotalPages());
		
		return "/adm/cadastro_autor";
	}
	
	@GetMapping("/escolher_pagina_inicio/{id}")
	public String escolherPagina(@PathVariable Integer id ) {

		paginaAutor = id;

		return "redirect:/livraria/adm/cadastrarAutor";
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
	
	@GetMapping("/editarAutor")
	public String solicitarEditarAutor(@RequestParam(name = "id") Long idAutor, Model modelo,AutorDTO autorDTO) {
		this.idAutorEditar = idAutor;
		autorDTO.setNomeAutor(autorService.encontarAutor(idAutor).getNomeAutor());
		modelo.addAttribute("autorDTO",autorDTO);
		
		return "/adm/editar_autor";
		
	}
	
	
	
	@PostMapping("/editarAutor")
	public String editarAutor(@Valid AutorDTO autorDTO, BindingResult result ) {
		if(result.hasErrors()) {
			return "/adm/editar_autor";
		}
		
		
		autorService.alterarAutor(idAutorEditar,autorDTO.getNomeAutor());
		
		
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
		try {
			 editoraService.cadastrarEditora(editoraDTO);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

		return "redirect:/livraria/adm/cadastrarEditora";
	}
	
	
	@PostMapping("/deletarEditora")
	public String deletarEditora(@RequestParam(name = "id") Long idEditora ) {
		editoraService.excluirEditora(idEditora);
		
		return "redirect:/livraria/adm/cadastrarEditora";
	}
	
	
	@GetMapping("/editarEditora")
	public String solicitarEditarEditora(@RequestParam(name = "id") Long idEditora ,Model modelo,EditoraDTO editoraDTO) {
		this.idEditoraEditar = idEditora;
		
		editoraDTO.setNomeEditora(editoraService.encontarEditora(idEditora).getNomeEditora());
		
		modelo.addAttribute("editoraDTO", editoraDTO);
		
		return "/adm/editar_editora";
		
		
	}
	
	@PostMapping("/editarEditora")
	public String editarEditora(@Valid EditoraDTO editoraDTO,BindingResult result ) {
		
		if(result.hasErrors()) {
			return "/adm/editar_editora";
		}
		try {
			editoraService.editarEditora(editoraDTO.getNomeEditora(),idEditoraEditar);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/livraria/adm/cadastrarEditora";
		
	}
	
	
	
	
	

}
