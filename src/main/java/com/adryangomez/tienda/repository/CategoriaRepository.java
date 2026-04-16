package com.adryangomez.tienda.repository;

import com.adryangomez.tienda.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
