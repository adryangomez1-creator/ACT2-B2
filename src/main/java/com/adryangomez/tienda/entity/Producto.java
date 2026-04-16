package com.adryangomez.tienda.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Integer idProducto;

    @NotBlank(message = "El nombre del producto no puede ir vacío")
    @Column(name="nombre_producto")
    private String nombreProducto;

    @NotNull(message = "El precio del producto es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Column(name="precio_producto")
    private BigDecimal precioProducto;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(name="stock")
    private Integer stockProducto;

    @NotNull(message = "La categoría es obligatoria")
    @Min(value = 1, message = "Debe seleccionar una categoría válida")
    @Column(name="id_categoria")
    private Integer idCategoria;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(BigDecimal precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Integer getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(Integer stockProducto) {
        this.stockProducto = stockProducto;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
}


