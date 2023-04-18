package com.ideasexpress1.springdatajpa1.Models.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ideasexpress1.springdatajpa1.Models.Entity.Venta;

@Repository
public class VentaDaoImp implements IDaoVenta {

    @PersistenceContext

    private EntityManager aux;

    @Override
    public Venta searchsale(Long Id_venta) {
        return aux.find(Venta.class,Id_venta);
    }
    
}
