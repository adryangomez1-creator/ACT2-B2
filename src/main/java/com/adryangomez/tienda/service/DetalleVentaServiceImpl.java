package com.adryangomez.tienda.service;

import com.adryangomez.tienda.entity.DetalleVenta;
import com.adryangomez.tienda.exception.ResourceNotFoundException;
import com.adryangomez.tienda.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaServiceImpl(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public List<DetalleVenta> listar() {
        return detalleVentaRepository.findAllFull();
    }

    @Override
    public DetalleVenta crear(DetalleVenta detalleVenta) {
        detalleVenta.setCodigoDetalleVenta(null);
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public DetalleVenta buscarPorId(Integer id) {
        return detalleVentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleVenta no encontrado: " + id));
    }

    @Override
    public DetalleVenta actualizar(Integer id, DetalleVenta detalleVenta) {
        DetalleVenta existente = buscarPorId(id);

        existente.setCantidad(detalleVenta.getCantidad());
        existente.setPrecioUnitario(detalleVenta.getPrecioUnitario());
        existente.setSubtotal(detalleVenta.getSubtotal());
        existente.setProducto(detalleVenta.getProducto());
        existente.setVenta(detalleVenta.getVenta());

        return detalleVentaRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if (!detalleVentaRepository.existsById(id)) {
            throw new ResourceNotFoundException("DetalleVenta no encontrado: " + id);
        }
        detalleVentaRepository.deleteById(id);
    }
}