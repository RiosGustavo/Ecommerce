/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.service;

import com.ecommerce.model.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author User
 */
public interface IUsuarioService {
   public Optional<Usuario> findById(String id);
   public Usuario save (Usuario usuario);
   public  Optional<Usuario> findByEmail(String email);
   public  List<Usuario> findAll();
    
}
