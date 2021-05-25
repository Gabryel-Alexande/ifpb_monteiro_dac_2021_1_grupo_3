package com.example.Livraria.controllers;

import java.util.List;

import com.example.Livraria.exeception.CPFException;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.fachada.FachadaUsuario;

import javassist.NotFoundException;

public class ControllerUsuario {
	private FachadaUsuario fachadaUsuario;

	public void cadatrarUsusario(String cpf, String nomeUsusario, String email, String senha,
			boolean admisnistrador) throws CPFException, LoginException {
		fachadaUsuario.cadastrarUsuario(cpf, nomeUsusario, email, senha, admisnistrador);
	}
	public void adcionarEndereco(Long idEndereco,Long idUsusario) {
		fachadaUsuario.adcionarEndereco(idEndereco, idUsusario);
	}
	public Object consultarPorEmail(String email) throws NotFoundException{
		return fachadaUsuario.consultarUsuarioPorEmail(email);
	}
	public Object[] listarUsuarios() {
		return fachadaUsuario.listarUsuarios().toArray();
	}

	public void adcionarAoCarinho(String isbn,Long idUsusario) {
		fachadaUsuario.adcionarAoCarinho(isbn, idUsusario);
	}

	public void removerAoCarinho(String isbn,Long idUsusario) {
		fachadaUsuario.removerAoCarinho(isbn, idUsusario);
	}
	public void comprarLivro(List<String> isbns,Long idUsusario) throws NotFoundException{
		fachadaUsuario.comprarLivro(isbns, idUsusario);
	}

	public void cancelarPedido(Long idPedido,Long idUsusario) {
		fachadaUsuario.cancelarPedido(idPedido, idUsusario);
	}

}
