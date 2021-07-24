package com.example.Livraria.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.Livraria.model.Autor;
import com.example.Livraria.model.Livro;

import lombok.Data;

@Data
public class AutorDTO {

	private Long idAutor;
	
	@NotBlank
	@Size(min=3,max=50)
	private String nomeAutor;
	
	private List<Livro> lirvosPublicados;

	public Autor parser() {

		Autor autor = new Autor(this.nomeAutor);
		return autor;
	}

}
