package com.example.Livraria.dto;

import java.util.List;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import com.example.Livraria.model.Categoria;
import com.example.Livraria.model.Livro;

import lombok.Data;

@Data
public class CategoriaDTO {
	
	private Long idCategortia;
	
	@NotBlank
	@Size(min=3,max=50 , message = "O nome da Categoria deve ter entre 3 a 50 caracteres")
	private String nomeCategoria;
	
	private List<Livro> livros;
	
	public Categoria parser() {
		Categoria categoria =  new Categoria(this.nomeCategoria);
		return categoria;
	}

}
