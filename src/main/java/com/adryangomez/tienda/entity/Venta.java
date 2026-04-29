package com.adryangomez.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_venta")
    private Integer codigoVenta;

    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha_venta", nullable = false)
    private LocalDate fechaVenta;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @NotNull(message = "El estado es obligatorio")
    @Column(name = "estado", nullable = false)
    private Integer estado;

    @ManyToOne
    @JoinColumn(name = "Clientes_dpi_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "Usuarios_codigo_usuario", nullable = false)
    private Usuario usuario;

    public Venta() {
    }

    public Venta(Integer codigoVenta, LocalDate fechaVenta, BigDecimal total, Integer estado, Cliente cliente, Usuario usuario) {
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    public Integer getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(Integer codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}