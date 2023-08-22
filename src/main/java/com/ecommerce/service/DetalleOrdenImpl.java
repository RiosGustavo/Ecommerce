/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.service;

import com.ecommerce.model.DetalleOrden;
import com.ecommerce.repository.IDetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class DetalleOrdenImpl implements IDetalleOrdenService{
    
    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
       return detalleOrdenRepository.save(detalleOrden);
    }
    
}
