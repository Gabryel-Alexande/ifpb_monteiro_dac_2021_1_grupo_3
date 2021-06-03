package com.example.Livraria.fachada;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Livraria.exeception.CPFException;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.model.Endereco;
import com.example.Livraria.model.EstadoPedido;
import com.example.Livraria.model.ItemPedido;
import com.example.Livraria.model.Livro;
import com.example.Livraria.model.Pedido;
import com.example.Livraria.model.Usuario;
import com.example.Livraria.repositorio.EnderecoRepositorio;
import com.example.Livraria.repositorio.ItemPedidoRepositorio;
import com.example.Livraria.repositorio.LivroRepositorio;
import com.example.Livraria.repositorio.PedidoRepositorio;
import com.example.Livraria.repositorio.UsuarioRepositorio;
import com.example.Livraria.utilitarios.AutenticacaoCPF;
import com.example.Livraria.utilitarios.AutenticacaoLogin;
import com.example.Livraria.utilitarios.EnviadorDeEmail;

import javassist.NotFoundException;

@Service
public class FachadaUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private LivroRepositorio livroRepositorio;
	@Autowired
	private PedidoRepositorio pedidoRepositorio;
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	@Autowired
	private ItemPedidoRepositorio itemPedidoRepositorio;
	@Autowired
	private EnviadorDeEmail enviadorDeEmail;

	public void cadastrarUsuario(String cpf, String nomeUsusario, String email, String senha, boolean admisnistrador)
			throws CPFException, LoginException {

		if (usuarioRepositorio.findByEmail(email) != null) {
			throw new LoginException("[ERRO] Email já cadastrado");
		}

		Usuario usuario = new Usuario(nomeUsusario, email, senha, cpf, admisnistrador);

		if (!AutenticacaoCPF.autenticarCPF(cpf)) {
			throw new CPFException();
		} else if (!AutenticacaoLogin.validarLogin(email)) {
			throw new LoginException("[ERRO] Email invalido!");
		} else if (!AutenticacaoLogin.validarrSenha(senha)) {
			throw new LoginException("[ERRO] Senha invalido!");
		}
		enviadorDeEmail.enviarEmail(email, "Sua conta foi criada com sucesso!", "Seja bem vindo a nossa loja "
				+ nomeUsusario
				+ "\nAqui temos uma grande variedade de livros.\nSinta-se a vontade para nos contactar.\nObrigado por nos escolher.");
		usuarioRepositorio.save(usuario);
	}

//	  public void adcionarEndereco(Long idEndereco, String email) { Endereco
//	  enderecoRegatado = enderecoRepositorio.findById(idEndereco).get(); Usuario
//	  usuario = usuarioRepositorio.findByEmail(email);
//	  usuario.adcionarEndereco(enderecoRegatado); usuarioRepositorio.save(usuario);
//	  }

	public void adcionarEndereco(String email, String cep, String rua, String estado, String cidade, String complemento,
			String pais, String bairro, String numeroCasa) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		Endereco enderecoRegatado = new Endereco(cep, rua, estado, cidade, complemento, pais, bairro, numeroCasa,
				usuario);
		enderecoRepositorio.save(enderecoRegatado);
	}

	public void removerEndereco(Long idEndereco, String email) throws NotFoundException {
		Endereco enderecoRegatado = enderecoRepositorio.findById(idEndereco).get();

		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if (usuario.getIdUsusario() == enderecoRegatado.getUsuario().getIdUsusario()) {
			enderecoRegatado.setUsuario(null);
			enderecoRepositorio.delete(enderecoRegatado);
		} else {
			throw new NotFoundException("[ERRO] Email não encontrado");
		}

	}

	public Usuario consultarUsuarioPorEmail(String email) throws NotFoundException {

		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if (usuario != null) {
			System.out.println(usuario.getEnderecos());
			return usuario;
		}
		throw new NotFoundException("[ERRO] Email não cadastrado!");
	}

	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}

	public List<Pedido> listarPedidos(String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		return pedidoRepositorio.findByUsuario(usuario);
	}

	public void adcionarAoCarinho(String isbn, Integer quantidade, String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		Livro livro = livroRepositorio.findByIsbn(isbn);
		Pedido pedido = getCarrinho(usuario);

		if (pedido == null) {
			pedido = new Pedido(usuario);
			pedidoRepositorio.save(pedido);
		}
		ItemPedido item = new ItemPedido(livro, quantidade, pedido);
		itemPedidoRepositorio.save(item);
	}

	public void removerDoCarinho(Long id, String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		ItemPedido item = itemPedidoRepositorio.findById(id).get();
		if (usuario.getIdUsusario() == item.getPedido().getUsuario().getIdUsusario()) {
			item.setPedido(null);
			itemPedidoRepositorio.delete(item);
		}
	}

	public List<ItemPedido> verCarrinho(String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		return itemPedidoRepositorio.findByPedido(getCarrinho(usuario));
	}

	public void comprarLivro(String email) throws NotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		List<ItemPedido> itens = itemPedidoRepositorio.findAll();
		Pedido pedido=getCarrinho(usuario);
		for (ItemPedido itemPedido : itemPedidoRepositorio.findByPedido(pedido)) {
			if (!itemPedido.getLivro().isEmEstoque()) {
				throw new NotFoundException("[ERRO] Este livro não estar em estoque!");
			}
		}
		pedido.setEstadoPedido(EstadoPedido.Fechado);
		pedidoRepositorio.save(pedido);
		for (ItemPedido itemPedido : itens) {
			itemPedido.getLivro().diminuirEtoque(itemPedido.getQuantidade());
			itemPedido.getPedido()
			.setPreco(itemPedido.getLivro().getPreco().multiply(new BigDecimal(itemPedido.getQuantidade())));
			itemPedidoRepositorio.save(itemPedido);
		}
		enviadorDeEmail.enviarEmail(usuario.getEmail(), "Sua compra foi feita com sucesso!",
				"Obrigado por sua compra.\nSeu pedido chegara em breve!");
	}

	public void cancelarPedido(Long idPedido, String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		Pedido pedido = pedidoRepositorio.findById(idPedido).get();
		if (usuario.getIdUsusario() == pedido.getUsuario().getIdUsusario()) {
			pedidoRepositorio.delete(pedido);
		}
		enviadorDeEmail.enviarEmail(usuario.getEmail(), "Sua compra cancelada com sucesso!",
				"Sua compra foi cancelada, logo receberá seu reembolso!");
	}

	private Pedido getCarrinho(Usuario usuario) {
		for (Pedido pedidoDaVez : pedidoRepositorio.findByUsuario(usuario)) {
			if (pedidoDaVez.getEstadoPedido() == EstadoPedido.Aberto) {
				return pedidoDaVez;
			}
		}
		return null;
	}
}
