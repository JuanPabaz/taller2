package com.ideasexpress1.springdatajpa1.Models.Repositiorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideasexpress1.springdatajpa1.Models.Entity.Venta;

public interface VentaRepository extends JpaRepository<Venta,Long>{
    
}
