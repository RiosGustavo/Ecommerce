/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.service;

import com.ecommerce.model.Producto;
import java.util.Optional;

/**
 *
 * @author User
 */
public interface ProductoService {
    
    /// ACA HACEMOS EL CRUD
    
    public Producto save(Producto producto);
       
    public Optional<Producto> get(String id);
    
    public void upDate(Producto producto);
    
    public void delete(String id);
    
}
