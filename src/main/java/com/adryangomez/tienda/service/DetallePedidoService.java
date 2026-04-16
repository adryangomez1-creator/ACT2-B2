package com.adryangomez.tienda.service;

import com.adryangomez.tienda.entity.DetallePedido;

import java.util.List;

public interface DetallePedidoService {
    List<DetallePedido> listar();
    DetallePedido crear(DetallePedido detallePedido);
    DetallePedido actualizar(Integer id, DetallePedido detallePedido);
    DetallePedido buscarPorId(Integer id);
    void eliminar(Integer id);
}
