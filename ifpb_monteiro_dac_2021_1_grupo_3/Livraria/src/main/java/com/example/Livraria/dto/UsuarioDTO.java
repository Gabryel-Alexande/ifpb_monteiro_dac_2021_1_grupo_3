package com.example.Livraria.dto;

import java.time.LocalDate;
import javax.validation.constraints.Email;
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
	private String nomeUsuario;
	
	@Email(message = "Informe um email Válido")
	private String email;
	
	@NotBlank(message = "Senha não pode ser vazia")
	private String senha;
	
	@NotBlank(message = "O CPF Não Pode ser vazio")
	//@Pattern(regexp ="[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}",message = "CPF Não Está no Formato Aceito")
	private String cpf;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dataDeNascimento;

	public Usuario parser() {

		Usuario usuario = new Usuario();

		usuario.setNomeUsuario(this.getNomeUsuario());
		usuario.setSenha(this.getSenha());
		usuario.setEmail(this.getEmail());
		usuario.setDataDeNascimento(this.getDataDeNascimento());
		usuario.setCpf(this.getCpf());

		return usuario;
	}
}
