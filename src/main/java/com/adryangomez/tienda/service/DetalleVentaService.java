package com.adryangomez.tienda.service;

import com.adryangomez.tienda.entity.DetalleVenta;
import java.util.List;

public interface DetalleVentaService {

    List<DetalleVenta> listar();
    DetalleVenta crear(DetalleVenta detalleVenta);
    DetalleVenta actualizar(Integer id, DetalleVenta detalleVenta);
    DetalleVenta buscarPorId(Integer id);
    void eliminar(Integer id);
}