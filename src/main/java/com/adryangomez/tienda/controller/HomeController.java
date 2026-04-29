package com.adryangomez.tienda.controller;

import com.adryangomez.tienda.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("username", principal.getName());
        } else {
            model.addAttribute("username", "Invitado");
        }

        return "home";
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        Usuario usuario = new Usuario();
        usuario.setEstado(1);
        model.addAttribute("usuario", usuario);
        return "register";
    }

    @GetMapping("/denegado")
    public String denegado() {
        return "denegado";
    }
}