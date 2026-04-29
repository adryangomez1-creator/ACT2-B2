package com.adryangomez.tienda.controller;

import com.adryangomez.tienda.entity.Usuario;
import com.adryangomez.tienda.enumtypes.userType;
import com.adryangomez.tienda.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/guardar-usuario")
    public String registrarUsuario(@Valid @ModelAttribute Usuario usuario,
                                   BindingResult result) {

        // 🔥 DEBUG IMPORTANTE
        if (result.hasErrors()) {
            System.out.println("❌ ERRORES EN REGISTRO:");
            result.getAllErrors().forEach(e -> System.out.println(e.toString()));
            return "register";
        }

        usuario.setEstado(1);
        usuario.setRol(userType.ROLE_USER);

        usuarioService.crear(usuario);

        System.out.println("✅ USUARIO REGISTRADO: " + usuario.getUsername());

        return "redirect:/login";
    }
}