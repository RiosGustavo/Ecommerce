/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.service;

import com.ecommerce.model.Orden;
import java.util.List;

/**
 *
 * @author User
 */
public interface IOrdenService {
    public Orden save (Orden orden);
    
    List<Orden> findAll();
    
    public String generarNumeroOrden();
    
}
