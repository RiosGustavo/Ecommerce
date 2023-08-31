/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.service;

import com.ecommerce.model.Orden;
import com.ecommerce.model.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author User
 */
public interface IOrdenService {

    public Orden save(Orden orden);

    public List<Orden> findAll();

    public String generarNumeroOrden();

    public List<Orden> findByUsuario(Usuario usuario);
    
    public Optional<Orden> findById(String id);

}
