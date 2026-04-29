package com.adryangomez.tienda.repository;

import com.adryangomez.tienda.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {

    @Query("SELECT d FROM DetalleVenta d JOIN FETCH d.producto JOIN FETCH d.venta")
    List<DetalleVenta> findAllFull();
}