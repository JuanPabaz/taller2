package com.ideasexpress1.springdatajpa1.Models.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productos_vendidos")
public class ProductoVendido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id_producto;

    private String Nombre;
    private String Descripcion;
    private Float Cantidad;
    private Float Precio;
    private String Codigo;

    @ManyToOne
    @JoinColumn
    private Venta venta;

    public ProductoVendido(String nombre, String descripcion, Float cantidad, Float precio, String codigo,
            Venta venta) {
        Nombre = nombre;
        Descripcion = descripcion;
        Cantidad = cantidad;
        Precio = precio;
        Codigo = codigo;
        this.venta = venta;
    }

    public ProductoVendido() {
    }

    public ProductoVendido(Long id_producto, String nombre1, String descripcion1, Float cantidad1, Float precio1,
            String codigo1, Venta venta1) {
    }

    public ProductoVendido(String nombre1, Float cantidad1, Float precio1, String codigo1, Venta venta1) {
    }

    public Float getTotal(){
        return this.Cantidad * this.Precio;
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

    public Float getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Float cantidad) {
        Cantidad = cantidad;
    }

    public Float getPrecio() {
        return Precio;
    }

    public void setPrecio(Float precio) {
        Precio = precio;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    
    

    
    
    
}
