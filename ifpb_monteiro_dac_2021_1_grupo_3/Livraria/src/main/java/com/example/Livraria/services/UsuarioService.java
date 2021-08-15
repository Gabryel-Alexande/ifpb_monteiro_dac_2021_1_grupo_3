package com.example.Livraria.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.hibernate.loader.plan.exec.process.internal.AbstractRowReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Livraria.dto.EnderecoDTO;
import com.example.Livraria.dto.UsuarioDTO;
import com.example.Livraria.exeception.CPFException;
import com.example.Livraria.exeception.LoginException;
import com.example.Livraria.model.Autoridades;
import com.example.Livraria.model.Endereco;
import com.example.Livraria.model.EstadoPedido;
import com.example.Livraria.model.ItemPedido;
import com.example.Livraria.model.Livro;
import com.example.Livraria.model.MetodoPagamento;
import com.example.Livraria.model.Pedido;
import com.example.Livraria.model.Usuario;
import com.example.Livraria.repositorio.EnderecoRepositorio;
import com.example.Livraria.repositorio.ItemPedidoRepositorio;
import com.example.Livraria.repositorio.LivroRepositorio;
import com.example.Livraria.repositorio.MetodoPagamentoRepositorio;
import com.example.Livraria.repositorio.PedidoRepositorio;
import com.example.Livraria.repositorio.UsuarioRepositorio;
import com.example.Livraria.utilitarios.AutenticacaoCPF;
import com.example.Livraria.utilitarios.AutenticacaoLogin;
import com.example.Livraria.utilitarios.EnviadorDeEmail;
import com.example.Livraria.utilitarios.ValidadorCep;

import javassist.NotFoundException;

@Service
public class UsuarioService implements Serializable {

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
	MetodoPagamentoRepositorio metodoPagamentoRepositorio;
	@Autowired
	private EnviadorDeEmail enviadorDeEmail;

	public void cadastrarUsuario(UsuarioDTO usuarioDTO) throws CPFException, LoginException {
		Usuario usuario = usuarioDTO.parser();
		List<Autoridades> autoridades = new ArrayList<>();

		if (usuarioRepositorio.findByEmail(usuario.getEmail()) != null) {
			throw new LoginException("[ERRO] Email já cadastrado");
		}

		validarDados(usuario.getCpf(), usuario.getEmail(), usuario.getSenha(), usuario.getDataDeNascimento());

		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

		Autoridades auto = new Autoridades();
		
	
		auto.setId(Autoridades.CLIENTE);
		
		autoridades.add(auto);
		usuario.setAutoridades(autoridades);

//		enviadorDeEmail.enviarEmail(usuario.getEmail(), "Sua conta foi criada com sucesso!",
//				"Seja bem vindo a nossa loja " + usuario.getNomeUsuario()
//						+ "\nAqui temos uma grande variedade de livros.\nSinta-se a vontade para nos contactar.\nObrigado por nos escolher.");

		usuarioRepositorio.save(usuario);
	}

	public void alteraUsuario(UsuarioDTO usuarioDTO) throws CPFException, LoginException {
		Usuario usuario = usuarioRepositorio.findByEmail(usuarioDTO.getEmail());

		validarDados(usuarioDTO.getCpf(), usuarioDTO.getEmail(), usuarioDTO.getSenha(),
				usuarioDTO.getDataDeNascimento());
		usuario.setCpf(usuarioDTO.getCpf());
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioDTO.getSenha()));
		usuario.setNomeUsuario(usuarioDTO.getNomeUsuario());
		usuario.setDataDeNascimento(usuarioDTO.getDataDeNascimento());
		usuarioRepositorio.save(usuario);
	}

	public void adcionarEndereco(EnderecoDTO enderecoDTO, String email) throws IllegalArgumentException {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		Endereco enderecoRegatado = enderecoDTO.parse();

		usuario.setEndereco(enderecoRegatado);

		enderecoRegatado.setUsuario(usuario);

		enderecoRepositorio.save(enderecoRegatado);
		usuarioRepositorio.save(usuario);
	}

	public Endereco consultarEndereco(String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if (usuario.getEndereco() == null) {
			return new Endereco();
		}

		return usuario.getEndereco();
	}

	public void editarEndereco(EnderecoDTO enderecoDTO, String email) throws IllegalArgumentException {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		Endereco enderecoRegatado = usuario.getEndereco();

		enderecoRegatado.setBairro(enderecoDTO.getBairro());
		enderecoRegatado.setCep(enderecoDTO.getCep());
		enderecoRegatado.setCidade(enderecoDTO.getCidade());
		enderecoRegatado.setComplemento(enderecoDTO.getComplemento());
		enderecoRegatado.setEstado(enderecoDTO.getEstado());
		enderecoRegatado.setNumeroCasa(enderecoDTO.getNumeroCasa());
		enderecoRegatado.setPais(enderecoDTO.getPais());
		enderecoRegatado.setRua(enderecoDTO.getRua());

		enderecoRepositorio.save(enderecoRegatado);
		usuarioRepositorio.save(usuario);
	}

	public void removerEndereco(Long idEndereco, String email) throws NotFoundException {
		Endereco enderecoRegatado = enderecoRepositorio.findById(idEndereco).get();

		Usuario usuario = usuarioRepositorio.findByEmail(email);

		if (usuario == null) {
			throw new NotFoundException("Email não encontrado");
		} else {
			usuario.setEndereco(null);
			usuarioRepositorio.save(usuario);
		}
		enderecoRepositorio.delete(enderecoRegatado);

	}

	public Usuario consultarUsuarioPorEmail(String email) throws NotFoundException {

		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if (usuario != null) {
			return usuario;
		}
		throw new NotFoundException("[ERRO] Email não cadastrado!");
	}

	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}

	public Pedido listarCarrinhoUsuario(String email) {
		Usuario user = usuarioRepositorio.findByEmail(email);

		try {
			Pedido p = pedidoRepositorio.findCarrinho(user.getIdUsusario()).get(0);
			p.setItemPedido(itemPedidoRepositorio.findByPedido(p));
			p.atualizarValor();

			return p;

		} catch (Exception e) {

			Pedido pedido = new Pedido(user);
			pedido.setEstadoPedido(EstadoPedido.Aberto);
			user.getPedidos().add(pedido);
			pedidoRepositorio.save(pedido);
			usuarioRepositorio.save(user);

			return pedido;

		}

	}

	public List<Pedido> listarPedidosUsuario(String email) {
		Usuario user = usuarioRepositorio.findByEmail(email);

		List<Pedido> pedidos = pedidoRepositorio.findPedidos(user.getIdUsusario());

//		if (pedidos==null) {
//
//			Pedido pedido = new Pedido(user);
//			pedido.setEstadoPedido(EstadoPedido.Fechado);
//			user.getPedidos().add(pedido);
//			pedidoRepositorio.save(pedido);
//			usuarioRepositorio.save(user);
//			pedidos.add(pedido);
//			
//		}
		return pedidos;
	}

	public void adcionarAoCarinho(Long idLivro, Integer quantidade, String email) {
		Pedido pedido = this.listarCarrinhoUsuario(email);
		Livro livro = livroRepositorio.findById(idLivro).get();
		boolean chave = false;
		if (quantidade < 1) {
			throw new IllegalArgumentException("Quantidade invalida!");
		}

		for (ItemPedido itemPedido : itemPedidoRepositorio.findByPedido(pedido)) {
			if (itemPedido.getLivro().getIdLivro() == livro.getIdLivro()) {
				itemPedido.setQuantidade(quantidade + itemPedido.getQuantidade());
				pedido.setPreco(
						new BigDecimal(pedido.getPreco().floatValue() + (livro.getPreco().floatValue() * quantidade)));
				itemPedidoRepositorio.save(itemPedido);
				chave = true;
				break;
			}

		}

		if (!chave) {

			ItemPedido item = new ItemPedido(livro, quantidade, pedido);

			pedido.addPreco(item);

			itemPedidoRepositorio.save(item);
			pedidoRepositorio.save(pedido);
		}

	}

	public void removerDoCarinho(Long id, String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		ItemPedido item = itemPedidoRepositorio.findById(id).get();
		Pedido pedido = pedidoRepositorio.findCarrinho(usuario.getIdUsusario()).get(0);

		if (usuario.getIdUsusario() == item.getPedido().getUsuario().getIdUsusario()) {
			item.setPedido(null);
			pedido.removerPreco(item);
			pedidoRepositorio.save(pedido);
			itemPedidoRepositorio.delete(item);
		}
	}

	public List<ItemPedido> verCarrinho(String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		return itemPedidoRepositorio.findByPedido(getCarrinho(usuario));
	}

	private Pedido setarEndereco(Pedido p, Endereco e) {
		p.setBairro(e.getBairro());
		p.setCep(e.getCep());
		p.setCidade(e.getCidade());
		p.setComplemento(e.getComplemento());
		p.setEstado(e.getEstado());
		p.setNumeroCasa(e.getNumeroCasa());
		p.setPais(e.getPais());
		p.setRua(e.getRua());
		p.setDataDaCompra(LocalDate.now());

		return p;
	}

	public void comprarLivro(String email, String metodoPag) throws NotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(email);

		Pedido pedido = this.listarCarrinhoUsuario(email);

		List<ItemPedido> itens = itemPedidoRepositorio.findByPedido(pedido);

		for (ItemPedido itemPedido : itemPedidoRepositorio.findByPedido(pedido)) {

			if (!itemPedido.getLivro().isEmEstoque()) {
				throw new NotFoundException("[ERRO] Este livro não estar em estoque!");
			}
		}

		pedido = setarEndereco(pedido, usuario.getEndereco());

		pedido.setMetodoPagamento(metodoPagamentoRepositorio.findByNomeDoPagamento(metodoPag));

		pedido.setEstadoPedido(EstadoPedido.Fechado);

		for (ItemPedido itemPedido : itens) {
			System.out.println("CHEGUEI AQUIIIIIIIIII");
			Livro l = livroRepositorio.findById(itemPedidoRepositorio.findByLivro(itemPedido.getIdItemPedido())).get();
			l.diminuirEtoque(itemPedido.getQuantidade());
			livroRepositorio.save(l);
		}
		pedidoRepositorio.save(pedido);
//		enviadorDeEmail.enviarEmail(usuario.getEmail(), "Sua compra foi feita com sucesso!",
//				"Obrigado por sua compra.\nSeu pedido chegara em breve!");
	}

	public void cancelarPedido(Long idPedido, String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		Pedido pedido = pedidoRepositorio.findById(idPedido).get();
		if (usuario.getIdUsusario() == pedido.getUsuario().getIdUsusario()) {
			for (ItemPedido itemPedido : itemPedidoRepositorio.findByPedido(pedido)) {
				itemPedido.getLivro().aumentarEtoque(itemPedido.getQuantidade());
			}
			pedido.setEstadoPedido(EstadoPedido.Cancelado);
			pedidoRepositorio.save(pedido);
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

	private void validarDados(String cpf, String email, String senha, LocalDate dataDeNascimento)
			throws CPFException, LoginException {
		if (!AutenticacaoCPF.autenticarCPF(cpf)) {
			throw new CPFException();
		} else if (!AutenticacaoLogin.validarLogin(email)) {
			throw new LoginException("[ERRO] Email invalido!");
		} else if (!AutenticacaoLogin.validarrSenha(senha)) {
			throw new LoginException("[ERRO] Senha invalido!");
		}

		LocalDate dataAtual = LocalDate.now();
		if (Period.between(dataDeNascimento, dataAtual).getYears() < 18) {
			throw new IllegalArgumentException("[ERRO] Data invalida!");
		}

	}

	public boolean isPagamentoInPedido(Long idPagamento) {
		MetodoPagamento metodo = metodoPagamentoRepositorio.findById(idPagamento).get();
		List<Pedido> pedidos = pedidoRepositorio.findByMetodoPagamento(metodo);
		if (pedidos == null || pedidos.size() > 0) {
			return false;
		}

		return true;
	}
}
