package com.example.Livraria.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jdk.jfr.BooleanFlag;
import lombok.Data;

@Entity
@Data

public class Usuario implements Serializable,UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idUsusario;
	
	@Column(name = "nome_usuario", nullable = false)
	@Size(min=3,max=50)
	private String nomeUsuario;
	
	@Column(nullable = false) 
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	@OneToMany(cascade = CascadeType.MERGE,mappedBy = "usuario")
	private List<Endereco> enderecos;
	
	@Column(nullable = false)
	private String cpf;

	@Column(nullable = false)
	private LocalDate dataDeNascimento;
	
	@BooleanFlag
	@Column(nullable = false)
	private boolean admisnistrador;
	
	@ManyToMany
	private List<Perfil> perfis;

	@OneToMany(cascade = CascadeType.MERGE,mappedBy = "usuario")
	private List<Pedido> pedidos;

	
	@SuppressWarnings("unused")
	public Usuario () {}
	
	public Usuario(String nomeUsusario, String email, String senha, String cpf,
			boolean admisnistrador,LocalDate anoDeNascimento) {
		super();
		this.nomeUsuario = nomeUsusario;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.admisnistrador = admisnistrador;
		this.dataDeNascimento=anoDeNascimento;
	}

	// Este metodo foi criado com a finalidade de resolver o problema da clausula
	// @Data,
	// pois, a mesa cria um metodo plublico que permiti a alteração do atributo
	// indetificador
	// da entendiade, assim trazendo inconsistencia para o codiogo.
	@SuppressWarnings("unused")
	private void setIdUsuario(String nomeUsusario) {

	}
	
	public String toString() {

		return "Id: "+idUsusario+"\nNome: "+this.nomeUsuario+"\nCpf: "+this.cpf+"\nEmail: "+this.email+"\nÉ ADM ? "+this.admisnistrador;
	}

	public void adcionarPedido(Pedido pedido) {
		pedidos.add(pedido);
	}

	public void removerPedido(Pedido pedido) {
		pedidos.remove(pedido);
		pedido.removerClienteDoPedido();
	}
	public void adcionarEndereco(Endereco endereco) {
		enderecos.add(endereco);
	}
	public void removerEndereco(Endereco endereco) {
		enderecos.remove(endereco);
	}

	public Pedido getCarrinho() {
		for (Pedido pedido : pedidos) {
			if(pedido.getEstadoPedido()==EstadoPedido.Aberto) {
				return pedido;
			}
		}
		return null;
	}
	//CHANCE DE NULL POINTER PERGUNTAR AO PROFESSOR O QUE FAZER 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
//		return this.perfis;
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
