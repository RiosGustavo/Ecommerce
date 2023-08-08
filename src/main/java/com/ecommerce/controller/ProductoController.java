/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controller;

import com.ecommerce.model.Producto;
import com.ecommerce.model.Usuario;

import com.ecommerce.service.ProductoService;
import org.slf4j.*; //// con el * le decimos que importe todo lo que tiene este paquete 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {
    //// con este Logger podemos obtener mensajes de como es esta ejecutando nuestra aplicacion para ver 
    /// si hay errores
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    
    @Autowired
    private ProductoService productoService;
    
    //// con este medoto redireccionamos hacia la vista show
    @GetMapping("")
    public String show(){
        return "productos/show";
    }
    
    ////ahro ahacemos otro metodo el cual nosretornara al html para crer los proudctos
    
    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }
    
    
    @PostMapping("/save")
    public String save(Producto producto){
        LOGGER.info("Este es el objeto producto {}", producto );
        Usuario usuario = new Usuario();
        producto.setUsuario(usuario);
        productoService.save(producto);
        return"redirect:/productos";
        
    }
    
    
}
