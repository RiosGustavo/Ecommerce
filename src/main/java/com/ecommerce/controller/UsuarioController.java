/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controller;

import com.ecommerce.model.Orden;
import com.ecommerce.model.Usuario;
import com.ecommerce.service.IOrdenService;
import com.ecommerce.service.IUsuarioService;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    //// creamos una variable logger para controlar errores
    
    private final Logger logger =LoggerFactory.getLogger(UsuarioController.class);
    
    @Autowired
    private IUsuarioService usuarioService;
    
    @Autowired
    private IOrdenService ordenService;
    
    // /usuario/registro
    
    @GetMapping("/registro")
    public String create(){
        
        return "usuario/registro";
    }
    
    @PostMapping("/save")
    public String save(Usuario usuario){
        logger.info("Usuario registro: {}", usuario);
        
        usuario.setTipo("USER");
        
        usuarioService.save(usuario);
        
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }
    
    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session){
       // logger.info("Accesos : {}", usuario);
        
        Optional<Usuario>  user = usuarioService.findByEmail(usuario.getEmail());
        logger.info("Usuario de db: {}", user.get()); /// aca veridficamos en la consola cual es el usuario obtenido 
        
        if(user.isPresent()){
            session.setAttribute("idUsuario", user.get().getId());
            if (user.get().getTipo().equals("ADMIN")) {
                return "redirect:/administrador";
            }else{
                return "redirect:/";
            }
        }
        
        return "redirect:/";
    }
    
    @GetMapping("/compras")
    public String obtenerCompras(Model modelo, HttpSession session){
        
        modelo.addAttribute("sesion", session.getAttribute("idUsuario"));
        
        Usuario usuario = usuarioService.findById(session.getAttribute("idUsuario").toString()).get();
        
        List<Orden> ordenes = ordenService.findByUsuario(usuario);
        
        modelo.addAttribute("ordenes", ordenes);
        
        return "usuario/compras";
        
    }
    
}
