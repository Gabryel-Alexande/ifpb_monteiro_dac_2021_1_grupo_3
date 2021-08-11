package com.example.Livraria.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Livraria.model.Livro;
import com.example.Livraria.model.MetodoPagamento;
import com.example.Livraria.model.Pedido;

public interface MetodoPagamentoRepositorio extends JpaRepository<MetodoPagamento, Long> {
	
	public MetodoPagamento findByNomeDoPagamento(String nomeDoPagamento);
	
	
	
}
