package com.adryangomez.tienda.repository;

import com.adryangomez.tienda.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
