package com.adryangomez.tienda.entity;

import com.adryangomez.tienda.enumtypes.userType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Integer idUsuario;

    @NotBlank(message = "El nombre del usuario no puede ir vacio")
    @Size(min = 2, max = 60, message = "El nombre debe tener entre 2 y 60 caracteres.")
    @Column(name="nombre_usuario")
    private String nombreUsuario;

    @NotBlank(message = "El apellido del usuario no puede ir vacio")
    @Size(min = 2, max = 60, message = "El apellido debe tener entre 2 y 60 caracteres.")
    @Column(name="apellido_usuario")
    private String apellidoUsuario;

    @NotNull(message = "La edad no puede ir vacia")
    @Min(value = 1, message = "La edad debe de ser mayor o igual a 1")
    @Max(value = 120, message = "La edad maxima es de 120 caracteres")
    @Column(name="edad_usuario")
    private Integer edadUsuario;

    @Column(unique = true)
    @NotBlank(message = "El username no puede ir vacío")
    private String username;

@Column
    @NotBlank(message = "La contraseña no puede ir vacía")
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private userType rol;

    //Metodos getters and setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public Integer getEdadUsuario() {
        return edadUsuario;
    }

    public void setEdadUsuario(Integer edadUsuario) {
        this.edadUsuario = edadUsuario;
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

    public userType getRol() {
        return rol;
    }

    public void setRol(userType rol) {
        this.rol = rol;
    }

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nombreUsuario, String apellidoUsuario, Integer edadUsuario, String username, String password, userType rol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.edadUsuario = edadUsuario;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }
}
