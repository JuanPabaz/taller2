package com.ideasexpress1.springdatajpa1.Models.Repositiorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideasexpress1.springdatajpa1.Models.Entity.ProductoVendido;

public interface ProductoVendidoRepository extends JpaRepository<ProductoVendido,Long> {
    
}
