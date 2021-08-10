package com.example.Livraria.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Livraria.dto.CategoriaFiltroDTO;
import com.example.Livraria.dto.LivroDTO;
import com.example.Livraria.dto.PesquisaDTO;
import com.example.Livraria.facade.LivroFacade;
import com.example.Livraria.model.Livro;
import com.example.Livraria.services.CategoriaService;
import com.example.Livraria.services.LivroService;

@Controller
@RequestMapping("/livraria/publico")
public class ControllerHome {

	@Autowired
	private LivroService livroService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private LivroFacade livroFacade;
	
	
	private Integer pagina =1;
	
	private String buscaPagina ="";
	
	private List<Long> idsLivros = new ArrayList<Long>();
	
	
	
	@GetMapping("/home")
	public String solicitarHome(PesquisaDTO pesquisa,CategoriaFiltroDTO dto ,Model modelo) {
		
		if (this.idsLivros.size()==0) {
			this.idsLivros.add(-1l);
		}
		
		Page<Livro> livros = livroFacade.paginarLivros(pagina, idsLivros, buscaPagina);
		
		modelo.addAttribute("livros",livros);
		
		modelo.addAttribute("categorias",categoriaService.listarCategoria());
		
		modelo.addAttribute("numeracao",livroFacade.criarBotoes(livros.getTotalPages(),pagina));
		
		modelo.addAttribute("fim", livros.getTotalPages());
		
		return "/public/home";

	}
	
	
	@GetMapping("/Retornar_Home")
	public String retornarHome() {
		this.buscaPagina="";
		this.pagina=1;
		this.idsLivros=new ArrayList<Long>();
		
		return "redirect:/livraria/publico/home";
	}
	
	
	
	
	@GetMapping("/escolher_pagina_inicio/{id}")
	public String escolherPagina(@PathVariable Integer id ) {

		pagina = id;

		return "redirect:/livraria/publico/home";
	}
	
	@GetMapping("/home/pesquisa")
	public String solicitarLivroPesquisa(PesquisaDTO pesquisa, Model modelo,CategoriaFiltroDTO dto) {
		this.buscaPagina = pesquisa.getCampo();
		this.pagina = 1;
		return"redirect:/livraria/publico/home";
		
	}
	
	
	@GetMapping("/home/pesquisa/categoria")
	public String solicitarLivroPesquisaCategoria(@Valid CategoriaFiltroDTO dto ,PesquisaDTO pesquisa, BindingResult result,Model modelo ) {
//		for (Long valor : dto.getIdCategoria()) {
//			
//			System.out.println(valor);
//			
//		}
//		
		
//		if(result.hasErrors()) {
//			return"/public/home";
//		}
		List<Long> categorias = dto.getIdCategoria();
		if(categorias == null) {
			
			categorias = new ArrayList<Long>();
			categorias.add(-1l);
			
		}
		this.idsLivros = categorias;
		
		this.buscaPagina="";
		this.pagina = 1;
		
		return "redirect:/livraria/publico/home";
		
	}
	

	@GetMapping("/home/livro")
	public String solicitarLivro(@RequestParam(name = "id") Long idLivro,PesquisaDTO pesquisa , Model modelo) {
		LivroDTO livroDTO = this.transformarEmDTO(livroService.bucarLivrosPorId(idLivro));
		
		
		modelo.addAttribute("livro", livroDTO);

		return "/public/livro";

	}
	
	

	private List<LivroDTO> listarLivrosDTO(List<Livro> livros) {

		ArrayList<LivroDTO> livrosDTO = new ArrayList<>();

		for (Livro livro : livroService.listarLivros()) {
			LivroDTO livroDTO = new LivroDTO();

			livroDTO.setAutores(livro.getAutores());
			livroDTO.setIdLivro(livro.getIdLivro());
			livroDTO.setPreco(livro.getPreco().floatValue());
			livroDTO.setFotoLivro(livro.getFotoLivro());
			livroDTO.setEditora(livro.getEditora());
			livroDTO.setCategorias(livro.getCategorias());
			livroDTO.setTituloLivro(livro.getTituloLivro());
			livroDTO.setDescricao(livro.getDescricao());
			
			livrosDTO.add(livroDTO);

		}
		
		return livrosDTO;

	}
	
	private LivroDTO transformarEmDTO(Livro livro) {
		
		LivroDTO livroDTO = new LivroDTO();

		livroDTO.setAutores(livro.getAutores());
		livroDTO.setIdLivro(livro.getIdLivro());
		livroDTO.setPreco(livro.getPreco().floatValue());
		livroDTO.setFotoLivro(livro.getFotoLivro());
		livroDTO.setEditora(livro.getEditora());
		livroDTO.setCategorias(livro.getCategorias());
		livroDTO.setTituloLivro(livro.getTituloLivro());
		livroDTO.setDescricao(livro.getDescricao());
		
		return livroDTO;
	}

}
