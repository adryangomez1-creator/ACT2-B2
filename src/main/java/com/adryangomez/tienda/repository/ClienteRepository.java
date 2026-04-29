package com.adryangomez.tienda.repository;

import com.adryangomez.tienda.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}