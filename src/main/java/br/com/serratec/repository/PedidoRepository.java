package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
