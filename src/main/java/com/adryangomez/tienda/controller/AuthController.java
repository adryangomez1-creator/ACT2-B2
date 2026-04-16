package com.adryangomez.tienda.controller;

import com.adryangomez.tienda.entity.Usuario;
import com.adryangomez.tienda.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/guardar-usuario")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.crear(usuario);
        return "redirect:/login";
    }
}