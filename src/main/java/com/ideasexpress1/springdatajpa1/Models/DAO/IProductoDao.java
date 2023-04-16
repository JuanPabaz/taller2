package com.ideasexpress1.springdatajpa1.Models.DAO;

import java.util.List;

import com.ideasexpress1.springdatajpa1.Models.Entity.Producto;

public interface IProductoDao {
    
    public List<Producto> findAll();

    public void save(Producto producto);

    public Producto findOne(Long id);

    public void delete(Long id);
    
}
