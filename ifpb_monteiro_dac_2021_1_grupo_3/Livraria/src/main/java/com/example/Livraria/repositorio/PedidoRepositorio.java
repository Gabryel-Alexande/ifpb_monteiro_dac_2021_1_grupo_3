package com.example.Livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Livraria.model.Pedido;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long>{

}
