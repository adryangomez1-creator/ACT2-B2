package com.adryangomez.tienda.service;

import com.adryangomez.tienda.entity.Venta;
import java.util.List;

public interface VentaService {

    List<Venta> listar();
    Venta crear(Venta venta);
    Venta actualizar(Integer id, Venta venta);
    Venta buscarPorId(Integer id);
    void eliminar(Integer id);
}