package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Livraria.model.ItemPedido;

public interface ItemPedidoRepositorio extends JpaRepository<ItemPedido, Long>{
}
