package com.example.Livraria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Livraria.dto.LivroDTO;
import com.example.Livraria.dto.PesquisaDTO;
import com.example.Livraria.model.Livro;
import com.example.Livraria.services.LivroService;

@Controller
@RequestMapping("/livraria/publico")
public class ControllerHome {

	@Autowired
	private LivroService livroService;

	@GetMapping("/home")
	public String solicitarHome(PesquisaDTO pesquisa ,Model modelo) {
		List<LivroDTO>livrosDTO = this.listarLivrosDTO(livroService.listarLivros());
		
		modelo.addAttribute("livros", livrosDTO);
		
		return "/public/home";

	}
	
	@GetMapping("/home/pesquisa")
	public String solicitarLivroPesquisa(PesquisaDTO pesquisa, Model modelo) {
		
		modelo.addAttribute("livros",livroService.bucarLivroPorNome(pesquisa.getCampo().toUpperCase())); 
		return"/public/home";
		
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
			livroDTO.setPreco(livro.getPreco());
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
		livroDTO.setPreco(livro.getPreco());
		livroDTO.setFotoLivro(livro.getFotoLivro());
		livroDTO.setEditora(livro.getEditora());
		livroDTO.setCategorias(livro.getCategorias());
		livroDTO.setTituloLivro(livro.getTituloLivro());
		livroDTO.setDescricao(livro.getDescricao());
		
		return livroDTO;
	}

}
