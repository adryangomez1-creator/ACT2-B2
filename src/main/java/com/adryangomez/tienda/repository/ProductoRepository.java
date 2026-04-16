package com.adryangomez.tienda.repository;

import com.adryangomez.tienda.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
