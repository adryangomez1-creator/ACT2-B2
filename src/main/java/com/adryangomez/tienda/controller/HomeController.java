package com.adryangomez.tienda.controller;

import com.adryangomez.tienda.entity.Usuario;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal){
        model.addAttribute("username", principal.getName());
        return "home";
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    // 🔥 NUEVO: mostrar formulario de registro
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @GetMapping("/denegado")
    public String accesoDenegado(){
        return "denegado";
    }
}