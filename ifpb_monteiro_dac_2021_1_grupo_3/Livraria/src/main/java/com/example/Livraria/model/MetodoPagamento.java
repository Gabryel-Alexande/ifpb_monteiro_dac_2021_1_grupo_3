package com.example.Livraria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name = "metodo_pagamento" ,uniqueConstraints=@UniqueConstraint(columnNames={"nome_do_pagamento"}))
@Data
public class MetodoPagamento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idMetodoPagamento;
	
	@Column(name = "nome_do_pagamento")
	private String nomeDoPagamento;
	

}
