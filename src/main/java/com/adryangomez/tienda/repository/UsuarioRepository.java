package com.adryangomez.tienda.repository;

import com.adryangomez.tienda.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByUsername(String username);

}