package com.example.Livraria.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.example.Livraria.exeception.CPFException;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.fachada.FachadaUsuario;
import com.example.Livraria.model.Usuario;

import javassist.NotFoundException;
@Controller
public class ControllerUsuario {
	private FachadaUsuario fachadaUsuario = new FachadaUsuario();

	public void cadatrarUsusario(String cpf, String nomeUsusario, String email, String senha, boolean admisnistrador)
			throws CPFException, LoginException {
		fachadaUsuario.cadastrarUsuario(cpf, nomeUsusario, email, senha, admisnistrador);
	}

	public void adcionarEndereco(Long idEndereco, Long idUsusario) {
		fachadaUsuario.adcionarEndereco(idEndereco, idUsusario);
	}

	public void removerEndereco(Long idUsusario) {
		fachadaUsuario.removerEndereco(idUsusario);
	}

	public Object consultarPorEmail(String email) throws NotFoundException {
		return fachadaUsuario.consultarUsuarioPorEmail(email);
	}

	public Object[] listarUsuarios() {
		return fachadaUsuario.listarUsuarios().toArray();
	}

	public void adcionarAoCarinho(String isbn, String email) {
		fachadaUsuario.adcionarAoCarinho(isbn, email);
	}

	public void removerAoCarinho(String isbn, String email) {
		fachadaUsuario.removerAoCarinho(isbn, email);
	}

	public void comprarLivro(List<String> isbns, String email) throws NotFoundException {
		fachadaUsuario.comprarLivro(isbns, email);
	}

	public void cancelarPedido(Long idPedido, String email) {
		fachadaUsuario.cancelarPedido(idPedido, email);
	}

}
