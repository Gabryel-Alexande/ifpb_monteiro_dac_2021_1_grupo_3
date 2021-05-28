package com.example.Livraria.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.exeception.CPFException;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.model.Endereco;
import com.example.Livraria.model.ItemPedido;
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
//		EnviadorDeEmail.enviarEmail(email, "Sua conta foi criada com sucesso!", "Seja bem vindo a nossa loja "
//				+ nomeUsusario
//				+ "\nAqui temos uma grande variedade de livros.\nSinta-se a vontade para nos contactar.\nObrigado por nos escolher.");
		usuarioRepositorio.save(usuario);
	}
	public void adcionarEndereco(Long idEndereco,String email) {
		Endereco enderecoRegatado= enderecoRepositorio.findById(idEndereco).get();
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		usuario.adcionarEndereco(enderecoRegatado);
		usuarioRepositorio.save(usuario);
	}
	public void removerEndereco(Long idEndereco,String email) {
		Endereco enderecoRegatado= enderecoRepositorio.findById(idEndereco).get();
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if(usuario.getEnderecos()!= null) {
			usuario.removerEndereco(enderecoRegatado);;
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

	public List<Pedido> listarPedidos(String email){
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		return usuario.getPedidos();
	}
	public void adcionarAoCarinho(String isbn,Integer quantidade,String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		Livro livro= livroRepositorio.findById(email).get();	
		usuario.adcionarAoCarinho(new ItemPedido(livro, quantidade));
		usuarioRepositorio.save(usuario);
	}
	public void removerDoCarinho(Integer indice,String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		usuario.removerDoCarinho(indice);
		usuarioRepositorio.save(usuario);
	}
	public List<ItemPedido> verCarrinho(String email){
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		return usuario.getCarrinho();
	}
	public void comprarLivro(String email) throws NotFoundException{
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		for (ItemPedido itemPedido : usuario.getCarrinho()) {
			if(itemPedido.getLivro().isEmEstoque()) {
				throw new NotFoundException("Este livro não estar em estoque!");
			}
			itemPedido.getLivro().diminuirEtoque(itemPedido.getQuantidade());
			usuario.removerDoCarinho(itemPedido);
		}
		Pedido pedido= new Pedido(usuario, usuario.getCarrinho());
		usuario.adcionarPedido(pedido);
		pedidoRepositorio.save(pedido);
		for (ItemPedido itemPedido : usuario.getCarrinho()) {
			livroRepositorio.save(itemPedido.getLivro());
		}
		EnviadorDeEmail.enviarEmail(usuario.getEmail(), "Sua compra foi feita com sucesso!", "Obrigado por sua compra.\nSeu pedido chegara em breve!");
	}

	public void cancelarPedido(Long idPedido,String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		Pedido pedido = pedidoRepositorio.findById(idPedido).get();
		for (ItemPedido itemPedido : usuario.getCarrinho()) {
			itemPedido.getLivro().aumentarEtoque();
			livroRepositorio.save(itemPedido.getLivro());
		}
		usuario.removerPedido(pedido);
		pedidoRepositorio.save(pedido);
		usuarioRepositorio.save(usuario);
		EnviadorDeEmail.enviarEmail(usuario.getEmail(), "Sua compra cancelada com sucesso!", "Sua compra foi cancelada, logo receberá seu reembolso!");

	}

}
