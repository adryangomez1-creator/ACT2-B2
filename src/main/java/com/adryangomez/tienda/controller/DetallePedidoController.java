package com.adryangomez.tienda.controller;

import com.adryangomez.tienda.entity.DetallePedido;
import com.adryangomez.tienda.entity.Pedido;
import com.adryangomez.tienda.service.DetallePedidoService;
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
@RequestMapping("/detallepedido")
public class DetallePedidoController {
    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("detallepedido", detallePedidoService.listar());
        return "detallepedido";
    }

    @GetMapping("/nuevo")
    public String FormularioNuevo(Model model){
        model.addAttribute("detallepedido", new DetallePedido());
        model.addAttribute("modoEdicion", false);
        return "detallepedido-form";
    }


    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("detallepedido") DetallePedido detallePedido, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion", false);
            return "detallepedido-form";
        }
        detallePedidoService.crear(detallePedido);
        return "redirect:/detallepedido";
    }

    @GetMapping("/editar/{id}")
    public String FormularioEdicion(Model model, @PathVariable Integer id){
        model.addAttribute("detallepedido", detallePedidoService.buscarPorId(id));
        model.addAttribute("modoEdicion", true);
        return "detallepedido-form";
    }

    @PostMapping("/actualizar/{id}")
    public String Actualizar(@Valid @ModelAttribute("detallepedido")  DetallePedido detallePedido, BindingResult result, Model model, @PathVariable Integer id){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion", true);
            return "detallepedido-form";
        }
        detallePedidoService.actualizar(id,detallePedido);
        return "redirect:/detallepedido";
    }

    @GetMapping("/eliminar/{id}")

    public String Eliminar(@PathVariable Integer id){
        detallePedidoService.eliminar(id);
        return "redirect:/detallepedido";
    }

















    @GetMapping("/get")
    public List<DetallePedido> listar(){
        return detallePedidoService.listar();
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DetallePedido> crear(@Valid @RequestBody DetallePedido detallePedido){
        DetallePedido creado = detallePedidoService.crear(detallePedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/getId/{id}")
    public DetallePedido buscarPorId(Integer id){
        return detallePedidoService.buscarPorId(id);
    }

    @PutMapping("put/{id}")
    public DetallePedido actualizar(@PathVariable Integer id, @Valid @RequestBody DetallePedido detallePedido){
        return detallePedidoService.actualizar(id,detallePedido);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id){
        detallePedidoService.eliminar(id);
    }
}
