package com.example.Livraria.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
public class Perfil{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idPerfil;
	
	
	@Column
	private String nomePerfil;

	
	
	

}
