package com.example.Livraria.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Livraria.model.Pedido;
import com.example.Livraria.model.Usuario;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long>{
	public List<Pedido> findByUsuario(Usuario usuario);
}
