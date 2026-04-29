package com.adryangomez.tienda.repository;

import com.adryangomez.tienda.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
}