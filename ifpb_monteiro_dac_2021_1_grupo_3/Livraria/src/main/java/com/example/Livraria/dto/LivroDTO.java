package com.example.Livraria.dto;

import java.math.BigDecimal;
import java.util.List;
import com.example.Livraria.model.Autor;
import com.example.Livraria.model.Livro;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class LivroDTO {
	
	@NotBlank
	private String tituloLivro;
	
	@NotBlank
	private String descricao;
	
	@NotBlank
	private BigDecimal preco;
	
	@NotBlank
	private List<Autor> autores;
	
	public Livro parser() {
		
		Livro livro = new Livro();
		
		livro.setTituloLivro(this.getTituloLivro());
		
		livro.setDescricao(this.getDescricao());
		
		livro.setAutores(this.getAutores());
		
		livro.setPreco(this.getPreco());
		
		return livro;
	}
}
