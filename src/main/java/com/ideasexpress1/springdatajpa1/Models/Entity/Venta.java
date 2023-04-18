package com.ideasexpress1.springdatajpa1.Models.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "ventas")
public class Venta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id_venta;
    private String Fecha;

    @OneToMany(mappedBy = "venta",cascade = CascadeType.ALL)
    private Set<ProductoVendido> productos;

    public Venta(){
        Fecha=FechaYHoraActual();
    }

    private String FechaYHoraActual(){
        String formato="yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        LocalDateTime actual=LocalDateTime.now();
        return formateador.format(actual);
    }

    public Long getId_venta() {
        return Id_venta;
    }

    public void setId_venta(Long id_venta) {
        Id_venta = id_venta;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public Set<ProductoVendido> getProductos() {
        return productos;
    }

    public void setProductos(Set<ProductoVendido> productos) {
        this.productos = productos;
    }

    public Float getTotal(){
        Float total=0f;

        for(ProductoVendido productoVendido : this.productos){
            total += productoVendido.getTotal();
        }
        return total;
    }
    

}
