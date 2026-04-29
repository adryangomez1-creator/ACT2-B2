package com.adryangomez.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Clientes")
public class Cliente {

    @Id
    @Column(name = "dpi_cliente")
    private Integer dpiCliente;

    @NotBlank(message = "El nombre no puede ir vacío")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    @NotBlank(message = "El apellido no puede ir vacío")
    @Size(max = 50, message = "El apellido no puede exceder 50 caracteres")
    @Column(name = "apellido_cliente", nullable = false)
    private String apellidoCliente;

    @Size(max = 100, message = "La dirección no puede exceder 100 caracteres")
    @Column(name = "direccion")
    private String direccion;

    @NotNull(message = "El estado es obligatorio")
    @Column(name = "estado", nullable = false)
    private Integer estado;

    public Cliente() {
    }

    public Cliente(Integer dpiCliente, String nombreCliente, String apellidoCliente,
                   String direccion, Integer estado) {
        this.dpiCliente = dpiCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.direccion = direccion;
        this.estado = estado;
    }

    public Integer getDpiCliente() {
        return dpiCliente;
    }

    public void setDpiCliente(Integer dpiCliente) {
        this.dpiCliente = dpiCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}