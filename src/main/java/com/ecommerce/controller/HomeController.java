/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controller;


import com.ecommerce.model.Producto;
import org.slf4j.Logger;
import com.ecommerce.service.ProductoService;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/")
public class HomeController {
    
    /// con esto nos impurime en al outload la trazabilidad poor si presenta algun error
    private final Logger log = LoggerFactory.getLogger(HomeController.class);
     
    @Autowired
    private ProductoService productoService;
    
    
    
    @GetMapping("")
    public String home(Model modelo){
        
        modelo.addAttribute("productos", productoService.findAll());
        
        return "usuario/home";
        
    }
    
    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable String id, Model modelo){
        log.info("id producto enviado como parametro {}", id);
        
        Producto producto = new Producto();
        
        Optional<Producto> productoRespuesta = productoService.get(id);
        
        producto = productoRespuesta.get();
        
        modelo.addAttribute("producto", producto);
        
        return "usuario/productohome";
    }
    
    
    
}
