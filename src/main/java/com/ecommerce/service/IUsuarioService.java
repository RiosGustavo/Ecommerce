/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.service;

import com.ecommerce.model.Usuario;
import java.util.Optional;

/**
 *
 * @author User
 */
public interface IUsuarioService {
    Optional<Usuario> findById(String id);
    Usuario save (Usuario usuario);
    
}
