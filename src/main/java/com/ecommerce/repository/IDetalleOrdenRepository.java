/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.repository;

import com.ecommerce.model.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface IDetalleOrdenRepository extends JpaRepository<DetalleOrden, String>{
    
}
