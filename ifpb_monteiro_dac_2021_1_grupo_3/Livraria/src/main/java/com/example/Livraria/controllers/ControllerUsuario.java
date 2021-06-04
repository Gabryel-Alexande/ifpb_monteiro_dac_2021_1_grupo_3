package com.example.Livraria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.Livraria.exeception.CPFException;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.fachada.FachadaUsuario;

import javassist.NotFoundException;

@Controller
public class ControllerUsuario {

	@Autowired
	private FachadaUsuario fachadaUsuario;

	public void cadatrarUsusario(String cpf, String nomeUsusario, String email, String senha, boolean admisnistrador,
			Integer anoDeNascimento) throws CPFException, LoginException {
		fachadaUsuario.cadastrarUsuario(cpf, nomeUsusario, email, senha, admisnistrador, anoDeNascimento);
	}
	public void alteraUsuario(String email, String cpf, String nomeUsusario, String senha, boolean admisnistrador,
			Integer anoDeNascimento) throws CPFException, LoginException {
		fachadaUsuario.alteraUsuario(email, cpf, nomeUsusario, senha, admisnistrador, anoDeNascimento);
	}

	public void adcionarEndereco(String email, String cep, String rua, String estado, String cidade, String complemento,
			String pais, String bairro, String numeroCasa) {
		fachadaUsuario.adcionarEndereco(email, cep, rua, estado, cidade, complemento, pais, bairro, numeroCasa);
	}

	public void removerEndereco(Long idEdereco, String email) throws NotFoundException {

		fachadaUsuario.removerEndereco(idEdereco, email);
	}

	public Object consultarPorEmail(String email) throws NotFoundException {
		return fachadaUsuario.consultarUsuarioPorEmail(email);
	}

	public Object[] listarUsuarios() {
		return fachadaUsuario.listarUsuarios().toArray();
	}

	public Object[] listarPedidos(String email) {
		return fachadaUsuario.listarPedidos(email).toArray();
	}

	public Object[] verCarrinho(String email) {
		return fachadaUsuario.verCarrinho(email).toArray();
	}

	public void adcionarAoCarinho(String isbn, Integer quantidade, String email) {
		fachadaUsuario.adcionarAoCarinho(isbn, quantidade, email);
	}

	public void removerDoCarinho(Long id, String email) {
		fachadaUsuario.removerDoCarinho(id, email);
	}

	public void comprarLivro(String email) throws NotFoundException {
		fachadaUsuario.comprarLivro(email);
	}

	public void cancelarPedido(Long idPedido, String email) {
		fachadaUsuario.cancelarPedido(idPedido, email);
	}

}
