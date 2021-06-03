package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Livraria.model.ItemPedido;
@Repository
public interface ItemPedidoRepositorio extends JpaRepository<ItemPedido, Long>{
}
