package com.ideasexpress1.springdatajpa1.Models.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




@Entity
@Table(name = "productos")
public class Producto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id_producto;
    
    @NotEmpty(message = "El producto debe tener un nombre valido")
    @Size(min = 3, max = 30)
    private String Nombre;

    @NotEmpty(message = "El producto debe tener una descripcion")
    private String Descripcion;

    @NotNull(message = "El producto debe tener unidades")
    @DecimalMin("1")
    private Float Unidades;

    @NotNull(message = "El producto debe tener un precio")
    @DecimalMin("9999.99")
    @DecimalMax("999999.99")
    private Float Precio;

    @NotNull(message = "El producto debe tener un codigo")
    @Size(min = 1,max = 50,message = "El codigo debe tener entre 1 y 5 caracateres")
    private String codigo;

    public Producto() {
    }

    public Producto(Long id_producto,
            @NotEmpty(message = "El producto debe tener un nombre valido") @Size(min = 3, max = 30) String nombre,
            @NotEmpty(message = "El producto debe tener una descripcion") String descripcion,
            @NotNull(message = "El producto debe tener unidades") @DecimalMin("1") Float unidades,
            @NotNull(message = "El producto debe tener un precio") @DecimalMin("9999.99") @DecimalMax("999999.99") Float precio,
            @NotNull(message = "El producto debe tener un codigo") @Size(min = 1, max = 50, message = "El codigo debe tener entre 1 y 5 caracateres") String codigo) {
        Id_producto = id_producto;
        Nombre = nombre;
        Descripcion = descripcion;
        Unidades = unidades;
        Precio = precio;
        this.codigo = codigo;
    }

    public Producto(
            @NotEmpty(message = "El producto debe tener un nombre valido") @Size(min = 3, max = 30) String nombre,
            @NotEmpty(message = "El producto debe tener una descripcion") String descripcion,
            @NotNull(message = "El producto debe tener unidades") @DecimalMin("1") Float unidades,
            @NotNull(message = "El producto debe tener un precio") @DecimalMin("9999.99") @DecimalMax("999999.99") Float precio,
            @NotNull(message = "El producto debe tener un codigo") @Size(min = 1, max = 50, message = "El codigo debe tener entre 1 y 5 caracateres") String codigo) {
        Nombre = nombre;
        Descripcion = descripcion;
        Unidades = unidades;
        Precio = precio;
        this.codigo = codigo;
    }

    public Producto(
            @NotNull(message = "El producto debe tener un codigo") @Size(min = 1, max = 50, message = "El codigo debe tener entre 1 y 5 caracateres") String codigo) {
        this.codigo = codigo;
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

    public Float getUnidades() {
        return Unidades;
    }

    public void setUnidades(Float unidades) {
        Unidades = unidades;
    }

    public Float getPrecio() {
        return Precio;
    }

    public void setPrecio(Float precio) {
        Precio = precio;
    }

    public boolean sinUnidades(){
        return this.Unidades <= 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void restarUnidades(Float float1){
        this.Unidades -= float1;
    }

    public Long getId_producto() {
        return Id_producto;
    }

    public void setId_producto(Long id_producto) {
        Id_producto = id_producto;
    }

    

}
