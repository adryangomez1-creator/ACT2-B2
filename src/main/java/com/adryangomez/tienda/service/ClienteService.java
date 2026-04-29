package com.adryangomez.tienda.service;

import com.adryangomez.tienda.entity.Cliente;
import java.util.List;

public interface ClienteService {

    List<Cliente> listar();
    Cliente crear(Cliente cliente);
    Cliente actualizar(Integer id, Cliente cliente);
    Cliente buscarPorId(Integer id);
    void eliminar(Integer id);
}