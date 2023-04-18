package com.ideasexpress1.springdatajpa1.Models.Repositiorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideasexpress1.springdatajpa1.Models.Entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Long>{

    Producto findBycodigo(String codigo);
    
}
