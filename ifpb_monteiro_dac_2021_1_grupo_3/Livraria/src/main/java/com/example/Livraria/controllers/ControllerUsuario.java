package com.example.Livraria.controllers;

import java.util.List;

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

	public void cadatrarUsusario(String cpf, String nomeUsusario, String email, String senha, boolean admisnistrador)
			throws CPFException, LoginException {
		fachadaUsuario.cadastrarUsuario(cpf, nomeUsusario, email, senha, admisnistrador);
	}

	public void adcionarEndereco(Long idEndereco, String email) {
		fachadaUsuario.adcionarEndereco(idEndereco, email);
	}

	public void removerEndereco(String email) {
		fachadaUsuario.removerEndereco(email);
	}

	public Object consultarPorEmail(String email) throws NotFoundException {
		return fachadaUsuario.consultarUsuarioPorEmail(email);
	}

	public Object[] listarUsuarios() {
		return fachadaUsuario.listarUsuarios().toArray();
	}
	public Object[] listarPedios(String email){
		return fachadaUsuario.listarPedios(email).toArray();
	}
	public void adcionarAoCarinho(String isbn, String email) {
		fachadaUsuario.adcionarAoCarinho(isbn, email);
	}

	public void removerDoCarinho(String isbn, String email) {
		fachadaUsuario.removerDoCarinho(isbn, email);
	}

	public void comprarLivro(List<String> isbns, String email) throws NotFoundException {
		fachadaUsuario.comprarLivro(isbns, email);
	}

	public void cancelarPedido(Long idPedido, String email) {
		fachadaUsuario.cancelarPedido(idPedido, email);
	}

}
