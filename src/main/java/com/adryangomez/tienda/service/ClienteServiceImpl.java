package com.adryangomez.tienda.service;

import com.adryangomez.tienda.entity.Cliente;
import com.adryangomez.tienda.exception.ResourceNotFoundException;
import com.adryangomez.tienda.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente crear(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente con DPI no encontrado: " + id));
    }

    @Override
    public Cliente actualizar(Integer id, Cliente cliente) {
        Cliente clienteExistente = buscarPorId(id);

        clienteExistente.setNombreCliente(cliente.getNombreCliente());
        clienteExistente.setApellidoCliente(cliente.getApellidoCliente());
        clienteExistente.setDireccion(cliente.getDireccion());
        clienteExistente.setEstado(cliente.getEstado());

        return clienteRepository.save(clienteExistente);
    }

    @Override
    public void eliminar(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente con DPI no encontrado: " + id);
        }

        clienteRepository.deleteById(id);
    }
}