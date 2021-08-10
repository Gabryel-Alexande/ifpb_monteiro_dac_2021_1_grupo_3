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
import javax.persistence.OneToOne;
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
	
	@OneToOne
	private Endereco endereco;
	
	@Column(nullable = false)
	private String cpf;

	@Column(nullable = false)
	private LocalDate dataDeNascimento;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Autoridades>autoridades;

	@OneToMany(cascade = CascadeType.MERGE,mappedBy = "usuario")
	private List<Pedido> pedidos;

	
	@SuppressWarnings("unused")
	public Usuario () {}
	
	public Usuario(String nomeUsusario, String email, String senha, String cpf,
			LocalDate anoDeNascimento) {
		super();
		this.nomeUsuario = nomeUsusario;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
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

		return "Id: "+idUsusario+"\nNome: "+this.nomeUsuario+"\nCpf: "+this.cpf+"\nEmail: "+this.email;
	}

	public void adcionarPedido(Pedido pedido) {
		pedidos.add(pedido);
	}

	public void removerPedido(Pedido pedido) {
		pedidos.remove(pedido);
		pedido.removerClienteDoPedido();
	}
	
	public Pedido getCarrinho() {
		for (Pedido pedido : pedidos) {
			if(pedido.getEstadoPedido()==EstadoPedido.Aberto) {
				return pedido;
			}
		}
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.autoridades;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
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
