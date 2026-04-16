package com.adryangomez.tienda.controller;

import com.adryangomez.tienda.entity.Usuario;
import com.adryangomez.tienda.service.UsuarioService;
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
 @RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
//metodo nuevo para listar usuarios

     @GetMapping
     public String listar(Model model){
         model.addAttribute("usuarios", usuarioService.listar());
         return "usuarios";
     }

//METODO PARA cargar la vista del formulario
     @GetMapping("/nuevo")
     public String FormularioNuevo(Model model){
      model.addAttribute("usuario", new Usuario());
      model.addAttribute("modoEdicion", false);
      return "usuario-form";
     }

      //METODO PARA CREAR UN NUEVO USUARIO
     @PostMapping("/guardar")
     public String crear(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model){
     if(result.hasErrors()){
         model.addAttribute("modoEdicion", false);
         return "usuario-form";
     }
        usuarioService.crear(usuario);
         return "redirect:/usuarios";
     }

     @GetMapping("/editar/{id}")
     public String FormularioEdicion(Model model, @PathVariable Integer id){
         model.addAttribute("usuario", usuarioService.buscarPorId(id));
         model.addAttribute("modoEdicion", true);
         return "usuario-form";
     }

     //METODO PARA CREAR UN NUEVO USUARIO

     @PostMapping("/actualizar/{id}")
     public String Actualizar(@Valid @ModelAttribute("usuario")  Usuario usuario, BindingResult result, Model model, @PathVariable Integer id){
         if(result.hasErrors()){
             model.addAttribute("modoEdicion", true);
             return "usuario-form";
         }

         usuarioService.actualizar(id,usuario);
         return "redirect:/usuarios";
     }
     
@GetMapping("/eliminar/{id}")

public String Eliminar(@PathVariable Integer id){
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
}







    @GetMapping("/getId/{id}")
    public Usuario buscar(@PathVariable  Integer id){
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/put/{id}")
    public Usuario actualizar(@PathVariable  Integer id, @Valid @RequestBody Usuario usuario){
        return usuarioService.actualizar(id,usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id){
        usuarioService.eliminar(id);
    }
}
