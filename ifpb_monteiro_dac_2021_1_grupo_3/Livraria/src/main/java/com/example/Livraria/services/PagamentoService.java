package com.example.Livraria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.example.Livraria.model.MetodoPagamento;
import com.example.Livraria.repositorio.MetodoPagamentoRepositorio;

@Service
public class PagamentoService {

	@Autowired
	MetodoPagamentoRepositorio metodoPagamentoRepositorio;

	@Autowired
	UsuarioService usuarioService;

	public void criarMetodoPagamento(String nome) {
		try {
			MetodoPagamento m = new MetodoPagamento();
			m.setNomeDoPagamento(nome);
			metodoPagamentoRepositorio.save(m);
		} catch (Exception e) {
			throw new DuplicateKeyException("Já existe um método com esse nome");
		}

	}

	public void deletarMetodoPagamento(Long idMetodo) {
		if (usuarioService.isPagamentoInPedido(idMetodo)) {
			metodoPagamentoRepositorio.deleteById(idMetodo);
		} else {
			throw new DuplicateKeyException("O metodo não pode ser deletado pois está em uso");
		}
	}

	public void editarMetodoPagamento(Long idMetodo, MetodoPagamento editado) {

		MetodoPagamento metodo = metodoPagamentoRepositorio.findByNomeDoPagamento(editado.getNomeDoPagamento());

		if (metodo != null) {
			if (metodo.getIdMetodoPagamento() != idMetodo) {
				throw new DuplicateKeyException("Esse metodo de pagamento já está cadastrado");
			}

		}

		MetodoPagamento metodoPag = metodoPagamentoRepositorio.findById(idMetodo).get();

		metodoPag.setNomeDoPagamento(editado.getNomeDoPagamento());
		metodoPagamentoRepositorio.save(metodoPag);
	}
	
	public MetodoPagamento encontarMetodo(Long id) {
		return  metodoPagamentoRepositorio.findById(id).get();
		
	}

}
