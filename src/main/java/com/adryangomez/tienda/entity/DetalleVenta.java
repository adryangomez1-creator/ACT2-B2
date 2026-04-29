package com.adryangomez.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "DetalleVenta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_detalle_venta")
    private Integer codigoDetalleVenta;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    @NotNull(message = "El subtotal es obligatorio")
    @DecimalMin(value = "0.01", message = "El subtotal debe ser mayor a 0")
    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;

    // 🔥 FIX IMPORTANTE: cargar relaciones siempre para evitar null en Thymeleaf
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Productos_codigo_producto", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Ventas_codigo_venta", nullable = false)
    private Venta venta;

    public DetalleVenta() {
    }

    public DetalleVenta(Integer codigoDetalleVenta, Integer cantidad, BigDecimal precioUnitario,
                        BigDecimal subtotal, Producto producto, Venta venta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.producto = producto;
        this.venta = venta;
    }

    public Integer getCodigoDetalleVenta() {
        return codigoDetalleVenta;
    }

    public void setCodigoDetalleVenta(Integer codigoDetalleVenta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}