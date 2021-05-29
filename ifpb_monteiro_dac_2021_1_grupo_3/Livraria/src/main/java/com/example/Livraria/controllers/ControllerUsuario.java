package com.example.Livraria.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.Livraria.exeception.CPFException;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.fachada.FachadaUsuario;

import javassist.NotFoundException;
@Controller
public class ControllerUsuario{


	public void cadatrarUsusario(String cpf, String nomeUsusario, String email, String senha, boolean admisnistrador)
			throws CPFException, LoginException {
		fachadaUsuario.cadastrarUsuario(cpf, nomeUsusario, email, senha, admisnistrador);
	}

	public void adcionarEndereco(Long idEndereco, String email) {
		fachadaUsuario.adcionarEndereco(idEndereco, email);
	}

	@Autowired
	private FachadaUsuario fachadaUsuario ;

	public void removerEndereco(Long idEdereco,String email) {
		fachadaUsuario.removerEndereco(idEdereco,email);
	}

	public Object consultarPorEmail(String email) throws NotFoundException {
		return fachadaUsuario.consultarUsuarioPorEmail(email);
	}

	public Object[] listarUsuarios() {
		return fachadaUsuario.listarUsuarios().toArray();
	}
	public Object[] listarPedidos(String email){
		return fachadaUsuario.listarPedidos(email).toArray();
	}
	public Object[] verCarrinho(String email){
		return fachadaUsuario.verCarrinho(email).toArray();
	}
	public void adcionarAoCarinho(String isbn,Integer quantidade,String email) {
		fachadaUsuario.adcionarAoCarinho(isbn, quantidade, email);
	}

	public void removerDoCarinho(Integer indice, String email) {
		fachadaUsuario.removerDoCarinho(indice, email);
	}

	public void comprarLivro(String email) throws NotFoundException {
		fachadaUsuario.comprarLivro(email);
	}

	public void cancelarPedido(Long idPedido, String email) {
		fachadaUsuario.cancelarPedido(idPedido, email);
	}

}
