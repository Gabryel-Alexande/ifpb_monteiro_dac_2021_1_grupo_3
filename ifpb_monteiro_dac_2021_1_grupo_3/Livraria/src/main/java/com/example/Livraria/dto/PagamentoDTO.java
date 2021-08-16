package com.example.Livraria.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class PagamentoDTO {
	
	@NotBlank(message = "Informe o Nome do Pagamento")
	String nomeDoPagamento;



}
