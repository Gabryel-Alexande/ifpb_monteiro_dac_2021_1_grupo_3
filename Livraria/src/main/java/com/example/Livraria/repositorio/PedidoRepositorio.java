package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Livraria.model.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long>{

}
