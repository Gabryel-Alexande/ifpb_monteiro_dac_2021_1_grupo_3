package com.example.Livraria.controllers;

import com.example.Livraria.exeception.CPFException;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.fachada.FachadaUsuario;
import com.example.Livraria.model.Endereco;

import javassist.NotFoundException;

public class ControllerUsuario {
	private FachadaUsuario fachadaUsuario;

	public void cadatrarUsusario(String cpf, String nomeUsusario, String email, String senha, Endereco endereco,
			boolean admisnistrador) throws CPFException, LoginException {
		fachadaUsuario.cadastrarUsuario(cpf, nomeUsusario, email, senha, endereco, admisnistrador);
	}
	public Object consultarPorEmail(String email) throws NotFoundException{
		return fachadaUsuario.consultarUsuarioPorEmail(email);
	}
}
