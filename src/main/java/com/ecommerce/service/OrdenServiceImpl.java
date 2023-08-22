/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.service;

import com.ecommerce.model.Orden;
import com.ecommerce.repository.IOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class OrdenServiceImpl implements IOrdenService{

    @Autowired
    private IOrdenRepository ordenRepository;
        
    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }
    
}
