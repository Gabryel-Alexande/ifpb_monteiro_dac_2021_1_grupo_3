package com.example.Livraria.dto;

import java.time.LocalDate;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.example.Livraria.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;

@Data
public class UsuarioDTO {
	@NotBlank
	private String nome;
	private String email;
	@NotBlank
	private String senha;
	@NotBlank
	@Pattern(regexp ="[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}",message = "CPF Não Está no Formato Aceito")
	private String cpf;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate data;

	private boolean admisnistrador;

	public Usuario parser() {

		Usuario usuario = new Usuario();

		usuario.setNomeUsuario(this.getNome());
		usuario.setSenha(this.getSenha());
		usuario.setEmail(this.getEmail());
		usuario.setDataDeNascimento(this.getData());
		usuario.setCpf(this.getCpf());

		return usuario;
	}
}
