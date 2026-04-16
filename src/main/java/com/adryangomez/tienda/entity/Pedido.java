package com.adryangomez.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @NotBlank(message = "La fecha no puede estar vacía")
    @Column(name = "fecha_pedido")
    private String fechaPedido;

    @NotNull(message = "El total no puede estar vacío")
    @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
    @Column(name = "total_pedido")
    private BigDecimal totalPedido;

    @NotNull(message = "El id del usuario no puede estar vacío")
    @Min(value = 1, message = "Debe seleccionar un usuario válido")
    @Column(name = "id_usuario")
    private Integer idUsuario;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public BigDecimal getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
