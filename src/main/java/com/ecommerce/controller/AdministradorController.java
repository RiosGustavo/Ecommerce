/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controller;

import com.ecommerce.model.Orden;
import com.ecommerce.model.Producto;
import com.ecommerce.service.IOrdenService;
import com.ecommerce.service.IUsuarioService;
import com.ecommerce.service.ProductoService;
import com.ecommerce.service.UsuarioServiceImpl;
import java.util.List;
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
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;
    
    @Autowired
    private IOrdenService ordenService;

    @GetMapping("")
    public String home(Model model) {

        List<Producto> productos = productoService.findAll();

        model.addAttribute("productos", productos);

        return "administrador/home.html";  /// hay que poner la ruta donde esta guardado el html en templates
    }

    @GetMapping("/usuarios")
    public String usuarios(Model modelo) {

        modelo.addAttribute("usuarios", usuarioService.findAll());
        return "administrador/usuarios";
    }

    @GetMapping("/ordenes")
    public String ordenes(Model modelo) {
        
        modelo.addAttribute("ordenes", ordenService.findAll());
        return "administrador/ordenes";
    }
    
     @GetMapping("/detalle/{id}")
    public String detalle(Model modelo, @PathVariable String id){
        
        Orden orden = ordenService.findById(id).get();
            
        modelo.addAttribute("detalles", orden.getDetalle());
        
        return "administrador/detalleorden";
        
    }

}
