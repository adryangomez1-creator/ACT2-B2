package com.adryangomez.tienda.entity;

import com.adryangomez.tienda.enumtypes.userType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_usuario")
    private Integer codigoUsuario;

    @NotBlank(message = "El username no puede ir vacío")
    @Size(max = 45, message = "El username no puede exceder 45 caracteres")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "La contraseña no puede ir vacía")
    @Size(max = 100, message = "La contraseña no puede exceder 100 caracteres")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "El email no puede ir vacío")
    @Email(message = "Debe ingresar un correo válido")
    @Size(max = 60, message = "El email no puede exceder 60 caracteres")
    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private userType rol;

    @NotNull(message = "El estado no puede ir vacío")
    @Column(nullable = false)
    private Integer estado = 1;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor completo
    public Usuario(Integer codigoUsuario, String username, String password, String email, userType rol, Integer estado) {
        this.codigoUsuario = codigoUsuario;
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.estado = estado;
    }

    // Getters y Setters
    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public userType getRol() {
        return rol;
    }

    public void setRol(userType rol) {
        this.rol = rol;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}