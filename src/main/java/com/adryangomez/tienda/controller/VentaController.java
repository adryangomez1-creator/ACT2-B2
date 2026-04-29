package com.adryangomez.tienda.controller;

import com.adryangomez.tienda.entity.Venta;
import com.adryangomez.tienda.service.VentaService;
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
@RequestMapping("/venta")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    // =========================
    // VISTAS (Thymeleaf)
    // =========================

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ventas", ventaService.listar());
        return "venta";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("modoEdicion", false);
        return "venta-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("venta") Venta venta,
                        BindingResult result,
                        Model model) {

        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "venta-form";
        }

        ventaService.crear(venta);
        return "redirect:/venta";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        model.addAttribute("venta", ventaService.buscarPorId(id));
        model.addAttribute("modoEdicion", true);
        return "venta-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id,
                             @Valid @ModelAttribute("venta") Venta venta,
                             BindingResult result,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "venta-form";
        }

        ventaService.actualizar(id, venta);
        return "redirect:/venta";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        ventaService.eliminar(id);
        return "redirect:/venta";
    }

    // =========================
    // API REST
    // =========================

    @GetMapping("/get")
    public List<Venta> listarAPI() {
        return ventaService.listar();
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Venta> crearAPI(@Valid @RequestBody Venta venta) {
        Venta creada = ventaService.crear(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping("/getId/{id}")
    public Venta buscarPorId(@PathVariable Integer id) {
        return ventaService.buscarPorId(id);
    }

    @PutMapping("/put/{id}")
    public Venta actualizarAPI(@PathVariable Integer id,
                               @Valid @RequestBody Venta venta) {
        return ventaService.actualizar(id, venta);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAPI(@PathVariable Integer id) {
        ventaService.eliminar(id);
    }
}