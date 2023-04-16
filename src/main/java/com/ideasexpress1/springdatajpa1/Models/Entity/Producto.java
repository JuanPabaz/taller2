package com.ideasexpress1.springdatajpa1.Models.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "productos")
public class Producto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    
    @NotNull(message = "Debes especificar el nombre")
    @NotEmpty
    private String Nombre;

    @NotNull(message = "Debes especificar la descripcion")
    @NotEmpty
    private String Descripcion;

    @NotNull(message = "Debes especificar el precio")
    private Integer Precio;

    @NotNull(message = "Debes especificar las unidades")
    private Integer Unidades;

    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    public Integer getPrecio() {
        return Precio;
    }
    public void setPrecio(Integer precio) {
        Precio = precio;
    }
    public Integer getUnidades() {
        return Unidades;
    }
    public void setUnidades(Integer unidades) {
        Unidades = unidades;
    }

    
    
}
