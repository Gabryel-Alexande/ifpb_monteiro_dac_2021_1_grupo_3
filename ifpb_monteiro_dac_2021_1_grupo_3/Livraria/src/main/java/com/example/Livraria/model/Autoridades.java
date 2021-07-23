package com.example.Livraria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
public class Autoridades implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Long CLIENTE = 1l;
	public static final Long ADMINISTRADOR = 2l;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String nome;

	@Override
	public String getAuthority() {

		return nome;
	}

}
