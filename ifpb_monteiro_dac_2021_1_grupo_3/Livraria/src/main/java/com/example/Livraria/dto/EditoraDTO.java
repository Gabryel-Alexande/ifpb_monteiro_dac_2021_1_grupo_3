package com.example.Livraria.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.Livraria.model.Editora;

import lombok.Data;

@Data
public class EditoraDTO {
	
	private Long idEditora;
	@NotBlank
	@Size(min=3,max=50 , message = "O nome da Editora deve ter entre 3 a 50 caracteres")
	private String nomeEditora;
	
	
	public Editora parser() {
		return new Editora(this.nomeEditora);
	}
	
	

}
