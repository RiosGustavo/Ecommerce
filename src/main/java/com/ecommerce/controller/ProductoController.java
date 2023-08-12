/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controller;

import com.ecommerce.model.Producto;
import com.ecommerce.model.Usuario;

import com.ecommerce.service.ProductoService;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.*; //// con el * le decimos que importe todo lo que tiene este paquete 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String show(Model modelo){
        modelo.addAttribute("productos", productoService.findAll());
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
        
        usuario.setId("1");
        usuario.setNombre("");
        usuario.setDireccion("");
        usuario.setEmail("");
        usuario.setPassword("");
        usuario.setTelefono("");
        usuario.setTipo("ADMIN");
        usuario.setUsername("gusti");
        usuario.setOrdenes(new ArrayList <>());
        usuario.setProductos(new ArrayList <>());
        
        producto.setUsuario(usuario);
        productoService.save(producto);
        return"redirect:/productos";
        
    }
    
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model modelo){
        
        Producto producto = new Producto();
        Optional<Producto> respuestaProducto = productoService.get(id);
        producto = respuestaProducto.get();
        
        /// con lo sigueinte vemso en el output si nostrae el objeto buscado
        LOGGER.info("Producto buscado: {}", producto);
        modelo.addAttribute("producto", producto);
        
        
        return "productos/edit";
    }
    
    @PostMapping("/update")
    public  String update(Producto producto){
        
        productoService.upDate(producto);
        return "redirect:/productos";
        
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        productoService.delete(id);
        return "redirect:/productos";
    }
    
}
