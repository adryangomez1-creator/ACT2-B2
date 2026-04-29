package com.adryangomez.tienda.controller;

import com.adryangomez.tienda.entity.DetalleVenta;
import com.adryangomez.tienda.service.DetalleVentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Controller
@RequestMapping("/detalleventa")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    // =========================
    // VISTAS (Thymeleaf)
    // =========================

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("detalleventas", detalleVentaService.listar());
        return "detalleventa";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("detalleventa", new DetalleVenta());
        model.addAttribute("modoEdicion", false);
        return "detalleventa-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("detalleventa") DetalleVenta detalleVenta,
                        BindingResult result,
                        Model model) {

        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "detalleventa-form";
        }

        detalleVentaService.crear(detalleVenta);
        return "redirect:/detalleventa";
    }

    @GetMapping("/editar/{id}")
    public String formularioEdicion(@PathVariable Integer id, Model model) {
        model.addAttribute("detalleventa", detalleVentaService.buscarPorId(id));
        model.addAttribute("modoEdicion", true);
        return "detalleventa-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id,
                             @Valid @ModelAttribute("detalleventa") DetalleVenta detalleVenta,
                             BindingResult result,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "detalleventa-form";
        }

        detalleVentaService.actualizar(id, detalleVenta);
        return "redirect:/detalleventa";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        detalleVentaService.eliminar(id);
        return "redirect:/detalleventa";
    }

    // =========================
    // API REST
    // =========================

    @GetMapping("/get")
    public List<DetalleVenta> listarAPI() {
        return detalleVentaService.listar();
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DetalleVenta> crearAPI(@Valid @RequestBody DetalleVenta detalleVenta) {
        DetalleVenta creado = detalleVentaService.crear(detalleVenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/getId/{id}")
    public DetalleVenta buscarPorId(@PathVariable Integer id) {
        return detalleVentaService.buscarPorId(id);
    }

    @PutMapping("/put/{id}")
    public DetalleVenta actualizarAPI(@PathVariable Integer id,
                                      @Valid @RequestBody DetalleVenta detalleVenta) {
        return detalleVentaService.actualizar(id, detalleVenta);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAPI(@PathVariable Integer id) {
        detalleVentaService.eliminar(id);
    }
}