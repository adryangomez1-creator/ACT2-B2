package com.adryangomez.tienda.service;

import com.adryangomez.tienda.entity.Producto;
import com.adryangomez.tienda.exception.ResourceNotFoundException;
import com.adryangomez.tienda.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Producto crear(Producto producto) {
        producto.setCodigoProducto(null);
        return productoRepository.save(producto);
    }

    @Override
    public Producto buscarPorId(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
    }

    @Override
    public Producto actualizar(Integer id, Producto producto) {
        Producto existente = buscarPorId(id);

        existente.setNombreProducto(producto.getNombreProducto());
        existente.setPrecio(producto.getPrecio());
        existente.setStock(producto.getStock());
        existente.setEstado(producto.getEstado());

        return productoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + id);
        }

        productoRepository.deleteById(id);
    }
}