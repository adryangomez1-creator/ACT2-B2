package com.adryangomez.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @NotBlank(message = "El nombre de la categoria no puede estar vacía")
    @Column(name = "nombre_categoria", nullable = false)
    private String nombreCategoria;

    @NotBlank(message = "La descripción de la categoria no puede estar vacía")
    @Column(name = "descripcion_categoria", nullable = false)
    private String descripcionCategoria;


    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }
}
