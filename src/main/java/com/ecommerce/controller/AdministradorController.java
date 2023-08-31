/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controller;

import com.ecommerce.model.Producto;
import com.ecommerce.service.IUsuarioService;
import com.ecommerce.service.ProductoService;
import com.ecommerce.service.UsuarioServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private IUsuarioService usuarioService;
    
    
    @GetMapping("")
    public String home(Model model){
        
        List<Producto> productos = productoService.findAll();
        
        model.addAttribute("productos", productos);
        
        return "administrador/home.html";  /// hay que poner la ruta donde esta guardado el html en templates
    }
    
    @GetMapping("/usuarios")
    public String usuarios(Model modelo){
        
        modelo.addAttribute("usuarios", usuarioService.findAll() );
        return "administrador/usuarios";
    }
    
}
