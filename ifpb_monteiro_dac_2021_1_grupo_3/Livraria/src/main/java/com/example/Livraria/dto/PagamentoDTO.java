package com.example.Livraria.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class PagamentoDTO {
	
	@NotBlank
	String nomeDoPagamento;



}
