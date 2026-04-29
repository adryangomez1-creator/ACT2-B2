package com.adryangomez.tienda.controller;

import com.adryangomez.tienda.entity.Usuario;
import com.adryangomez.tienda.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        return "usuarios";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("modoEdicion", false);
        return "usuario-form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Usuario usuario,
                          BindingResult result) {

        if (result.hasErrors()) {
            return "usuario-form";
        }

        usuarioService.crear(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("usuario", usuarioService.buscarPorId(id));
        model.addAttribute("modoEdicion", true);
        return "usuario-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id,
                             @Valid @ModelAttribute Usuario usuario,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "usuario-form";
        }

        usuarioService.actualizar(id, usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }
}