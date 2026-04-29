package com.adryangomez.tienda.service;

import com.adryangomez.tienda.entity.Venta;
import com.adryangomez.tienda.exception.ResourceNotFoundException;
import com.adryangomez.tienda.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> listar() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta crear(Venta venta) {
        venta.setCodigoVenta(null);
        return ventaRepository.save(venta);
    }

    @Override
    public Venta buscarPorId(Integer id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada: " + id));
    }

    @Override
    public Venta actualizar(Integer id, Venta venta) {
        Venta existente = buscarPorId(id);

        existente.setFechaVenta(venta.getFechaVenta());
        existente.setTotal(venta.getTotal());
        existente.setEstado(venta.getEstado());
        existente.setCliente(venta.getCliente());
        existente.setUsuario(venta.getUsuario());

        return ventaRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if (!ventaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Venta no encontrada: " + id);
        }

        ventaRepository.deleteById(id);
    }
}