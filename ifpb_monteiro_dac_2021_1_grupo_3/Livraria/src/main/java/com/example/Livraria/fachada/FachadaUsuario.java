package com.example.Livraria.fachada;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.exeception.CPFException;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.model.Endereco;
import com.example.Livraria.model.Livro;
import com.example.Livraria.model.Pedido;
import com.example.Livraria.model.Usuario;
import com.example.Livraria.repositorio.EnderecoRepositorio;
import com.example.Livraria.repositorio.LivroRepositorio;
import com.example.Livraria.repositorio.PedidoRepositorio;
import com.example.Livraria.repositorio.UsuarioRepositorio;
import com.example.Livraria.utilitarios.AutenticacaoCPF;
import com.example.Livraria.utilitarios.AutenticacaoLogin;
import com.example.Livraria.utilitarios.EnviadorDeEmail;

import javassist.NotFoundException;

@Service
public class FachadaUsuario {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private LivroRepositorio livroRepositorio;
	@Autowired
	private PedidoRepositorio pedidoRepositorio;
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;

	public void cadastrarUsuario(String cpf, String nomeUsusario, String email, String senha,
			boolean admisnistrador) throws CPFException, LoginException {
		Usuario usuario = new Usuario(nomeUsusario, email, senha, cpf, admisnistrador);
		if (!AutenticacaoCPF.autenticarCPF(cpf)) {
			throw new CPFException();
		} else if (!AutenticacaoLogin.validarLogin(email)) {
			throw new LoginException("Email invalido!");
		} else if (!AutenticacaoLogin.validarrSenha(senha)) {
			throw new LoginException("Senha invalido!");
		}
		EnviadorDeEmail.enviarEmail(email, "Sua conta foi criada com sucesso!", "Seja bem vindo a nossa loja "
				+ nomeUsusario
				+ "\nAqui temos uma grande variedade de livros.\nSinta-se a vontade para nos contactar.\nObrigado por nos escolher.");
		usuarioRepositorio.save(usuario);
	}
	public void adcionarEndereco(Long idEndereco,Long idUsusario) {
		Endereco enderecoRegatado= enderecoRepositorio.findByIdEndereco(idEndereco);
		Usuario usuario = usuarioRepositorio.findById(idUsusario);
		usuario.setEndereco(enderecoRegatado);
		usuarioRepositorio.save(usuario);
	}
	public void removerEndereco(Long idUsusario) {
		Usuario usuario = usuarioRepositorio.findById(idUsusario);
		if(usuario.getEndereco()!= null) {
			usuario.setEndereco(null);
			usuarioRepositorio.save(usuario);			
		}
	}
	public Usuario consultarUsuarioPorEmail(String email) throws NotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if (usuario != null) {
			return usuario;
		}
		throw new NotFoundException("Email não cadastrado!");
	}

	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}

	public void adcionarAoCarinho(String isbn,String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		Livro livro= livroRepositorio.findByISBN(isbn);
		usuario.adcionarAoCarinho(livro);
		usuarioRepositorio.save(usuario);
	}

	public void removerAoCarinho(String isbn,String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		Livro livro= livroRepositorio.findByISBN(isbn);
		usuario.removerDoCarinho(livro);
		usuarioRepositorio.save(usuario);
	}
	public void comprarLivro(List<String> isbns,String email) throws NotFoundException{
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		List<Livro> livroResgatados= new ArrayList<Livro>();
		for (String isbn : isbns) {
			Livro livro= livroRepositorio.findByISBN(isbn);	
			if(livro!=null) {
				livroResgatados.add(livro);				
			}if(livro.isEmEstoque()) {
				throw new NotFoundException("Este livro não estar em estoque!");
			}
			livro.diminuirEtoque();
			usuario.removerDoCarinho(livro);
		}
		Pedido pedido= new Pedido(usuario, livroResgatados);
		usuario.adcionarPedido(pedido);
		pedidoRepositorio.save(pedido);
		for (Livro livro : livroResgatados) {
			livroRepositorio.save(livro);
		}
		EnviadorDeEmail.enviarEmail(usuario.getEmail(), "Sua compra foi feita com sucesso!", "Obrigado por sua compra.\nSeu pedido chegara em breve!");
	}

	public void cancelarPedido(Long idPedido,String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		Pedido pedido = pedidoRepositorio.findByIdPedido(idPedido);
		for (Livro livro : pedido.getLivros()) {
			livro.aumentarEtoque();
		}
		usuario.removerPedido(pedido);
		pedidoRepositorio.save(pedido);
		usuarioRepositorio.save(usuario);
		EnviadorDeEmail.enviarEmail(usuario.getEmail(), "Sua compra cancelada com sucesso!", "Sua compra foi cancelada, logo receberá seu reembolso!");

	}

}
