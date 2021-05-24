package com.example.Livraria.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.autenticacao.AutenticacaoCPF;
import com.example.Livraria.autenticacao.AutenticacaoLogin;
import com.example.Livraria.exeception.CPFException;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.model.Endereco;
import com.example.Livraria.model.Usuario;
import com.example.Livraria.repositorio.UsuarioRepositorio;

import javassist.NotFoundException;

@Service
public class FachadaUsuario {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	public void cadastrarUsuario(String cpf,String nomeUsusario, String email, String senha, Endereco endereco,
			boolean admisnistrador)throws CPFException,LoginException{
		Usuario usuario = new Usuario(nomeUsusario, email, senha, endereco,cpf, admisnistrador);
		if(!AutenticacaoCPF.autenticarCPF(cpf)) {
			throw new CPFException();
		}else if(!AutenticacaoLogin.validarLogin(email)) {
			throw new LoginException("Email invalido!");
		}else if(!AutenticacaoLogin.validarrSenha(senha)) {
			throw new LoginException("Senha invalido!");
		}
		usuarioRepositorio.save(usuario);
	}

	public Usuario consultarUsuarioPorEmail(String email)throws NotFoundException{
		Usuario usuario= usuarioRepositorio.findByEmail(email);
		if(usuario!= null) {
			return usuario; 
		}
		throw new NotFoundException("Email não cadastrado!");
	}

	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}

}
