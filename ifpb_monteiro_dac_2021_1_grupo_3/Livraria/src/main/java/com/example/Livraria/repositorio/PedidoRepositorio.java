package com.example.Livraria.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Livraria.model.MetodoPagamento;
import com.example.Livraria.model.Pedido;
import com.example.Livraria.model.Usuario;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long>{
	public List<Pedido> findByUsuario(Usuario usuario);
	
	public List<Pedido> findByMetodoPagamento(MetodoPagamento metodoPagamento);
	
	@Query(value=
			"SELECT * FROM pedido p WHERE p.estado_pedido='Aberto' and p.id_ususario= ?1"
			,nativeQuery = true)
	public List<Pedido> findCarrinho(Long idUsuario);
	
	@Query(value=
			"SELECT * FROM pedido p WHERE p.estado_pedido='Fechado' and p.id_ususario = ?1 "
			,nativeQuery = true)
	public List<Pedido> findPedidos(Long idUsuario);
}
