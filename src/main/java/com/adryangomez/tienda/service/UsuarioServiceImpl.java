package com.adryangomez.tienda.service;

import com.adryangomez.tienda.entity.Usuario;
import com.adryangomez.tienda.exception.ResourceNotFoundException;
import com.adryangomez.tienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario crear(Usuario usuario) {
        usuario.setIdUsuario(null);

        // 🔐 encriptar contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // asignar rol por defecto
        usuario.setRol("ROLE_USER");

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario con ID, no encontrado: " + id));
    }

    @Override
    public Usuario actualizar(Integer id, Usuario usuario) {
        Usuario usuarioExistente = buscarPorId(id);
        usuarioExistente.setNombreUsuario(usuario.getNombreUsuario());
        usuarioExistente.setApellidoUsuario(usuario.getApellidoUsuario());
        usuarioExistente.setEdadUsuario(usuario.getEdadUsuario());
usuarioExistente.setUsername(usuario.getUsername());
usuarioExistente.setPassword(usuario.getPassword());
        return usuarioRepository.save(usuarioExistente);
    }


    @Override
    public void eliminar(Integer id) {
        if(!usuarioRepository.existsById(id)){
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + id);
        }

        usuarioRepository.deleteById(id);

    }
}
