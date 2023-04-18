package com.ideasexpress1.springdatajpa1.Models.Auxiliar;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ideasexpress1.springdatajpa1.Models.Entity.Producto;

public class ProductoVenta extends Producto{

    private Float Cantidad;

    public ProductoVenta(Long id_producto,
            @NotEmpty(message = "El producto debe tener un nombre valido") @Size(min = 3, max = 30) String nombre,
            @NotEmpty(message = "El producto debe tener una descripcion") String descripcion,
            @NotNull(message = "El producto debe tener unidades") @DecimalMin("1") Float unidades,
            @NotNull(message = "El producto debe tener un precio") @DecimalMin("9999.99") @DecimalMax("999999.99") Float precio,
            @NotNull(message = "El producto debe tener un codigo") @Size(min = 1, max = 50, message = "El codigo debe tener entre 1 y 5 caracateres") String codigo,
            Float cantidad) {
        super(id_producto, nombre, descripcion, unidades, precio, codigo);
        Cantidad = cantidad;
    }

    public ProductoVenta(
            @NotEmpty(message = "El producto debe tener un nombre valido") @Size(min = 3, max = 30) String nombre,
            @NotEmpty(message = "El producto debe tener una descripcion") String descripcion,
            @NotNull(message = "El producto debe tener unidades") @DecimalMin("1") Float unidades,
            @NotNull(message = "El producto debe tener un precio") @DecimalMin("9999.99") @DecimalMax("999999.99") Float precio,
            @NotNull(message = "El producto debe tener un codigo") @Size(min = 1, max = 50, message = "El codigo debe tener entre 1 y 5 caracateres") String codigo,
            Float cantidad) {
        super(nombre, descripcion, unidades, precio, codigo);
        Cantidad = cantidad;
    }

    public Float getCantidad() {
        return Cantidad;
    }

    public void aumentarCantidad(){
        this.Cantidad++;
    }

    public void disminuirCantidad(){
        this.Cantidad--;
    }

    public Float getTotal(){
        return this.getPrecio() * this.Cantidad;
    }

    
    
}
