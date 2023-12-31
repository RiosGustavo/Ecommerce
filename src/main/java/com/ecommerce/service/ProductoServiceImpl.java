/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.service;

import com.ecommerce.model.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.repository.IProductoRepository;

/**
 *
 * @author User
 */
@Service
public class ProductoServiceImpl implements ProductoService{
    
    @Autowired
    private IProductoRepository productoRepository;

    
        //// aca con los metodos heredados de JpaRepository le decimos que guarde 
    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> get(String id) {
        return productoRepository.findById(id);
    }

    @Override
    public void upDate(Producto producto) {
         productoRepository.save(producto);
    }

    @Override
    public void delete(String id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
        
    }
    
}
