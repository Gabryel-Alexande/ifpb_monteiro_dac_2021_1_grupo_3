package com.example.Livraria.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Livraria.model.ItemPedido;
import com.example.Livraria.model.Livro;
import com.example.Livraria.model.Pedido;
@Repository
public interface ItemPedidoRepositorio extends JpaRepository<ItemPedido, Long>{
	public List<ItemPedido> findByPedido(Pedido pedido);

	public List<ItemPedido> findByLivro(Livro livro);
}
