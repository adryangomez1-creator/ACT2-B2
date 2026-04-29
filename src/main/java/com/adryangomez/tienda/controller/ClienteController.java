package com.adryangomez.tienda.controller;

import com.adryangomez.tienda.entity.Cliente;
import com.adryangomez.tienda.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listar());
        return "cliente";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("modoEdicion", false);
        return "cliente-form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("cliente") Cliente cliente,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "cliente-form";
        }

        clienteService.crear(cliente);
        return "redirect:/cliente";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        model.addAttribute("cliente", clienteService.buscarPorId(id));
        model.addAttribute("modoEdicion", true);
        return "cliente-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id,
                             @Valid @ModelAttribute("cliente") Cliente cliente,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "cliente-form";
        }

        clienteService.actualizar(id, cliente);
        return "redirect:/cliente";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        clienteService.eliminar(id);
        return "redirect:/cliente";
    }
}