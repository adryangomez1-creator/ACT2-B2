package com.adryangomez.tienda.service;

import com.adryangomez.tienda.entity.Usuario;
import com.adryangomez.tienda.enumtypes.userType;
import com.adryangomez.tienda.exception.ResourceNotFoundException;
import com.adryangomez.tienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario crear(Usuario usuario) {

        usuario.setCodigoUsuario(null);

        // ✔ único lugar donde se encripta (CORRECTO)
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        if (usuario.getRol() == null) {
            usuario.setRol(userType.ROLE_USER);
        }

        if (usuario.getEstado() == null) {
            usuario.setEstado(1);
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public Usuario actualizar(Integer id, Usuario usuario) {
        Usuario existente = buscarPorId(id);

        existente.setUsername(usuario.getUsername());
        existente.setEmail(usuario.getEmail());
        existente.setEstado(usuario.getEstado());

        if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
            existente.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }

        if (usuario.getRol() != null) {
            existente.setRol(usuario.getRol());
        }

        return usuarioRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + id);
        }

        usuarioRepository.deleteById(id);
    }
}