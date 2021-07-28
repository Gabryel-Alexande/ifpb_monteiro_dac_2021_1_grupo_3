package com.example.Livraria.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Livraria.model.Pedido;
import com.example.Livraria.model.Usuario;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long>{
	public List<Pedido> findByUsuario(Usuario usuario);
	
	@Query(value="select * from pedido where estado_pedido='Aberto' and ",nativeQuery = true)
	public List<Pedido> findCarrinho(Usuario user);
	
	@Query(value="select * from pedido where estado_pedido='Fechado'",nativeQuery = true)
	public List<Pedido> findPedidos(Usuario usuario);
}
