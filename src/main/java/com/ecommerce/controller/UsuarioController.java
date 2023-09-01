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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("/usuario")
public class UsuarioController {

    //// creamos una variable logger para controlar errores
    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    
    @Autowired
    private IUsuarioService usuarioService;
    
    @Autowired
    private IOrdenService ordenService;
    
     
    BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();

    // /usuario/registro
    @GetMapping("/registro")
    public String create() {
        
        return "usuario/registro";
    }
    
    @PostMapping("/save")
    public String save(Usuario usuario) {
        logger.info("Usuario registro: {}", usuario);
        
        usuario.setTipo("USER");
        
        usuario.setPassword(passEncode.encode(usuario.getPassword()));
        usuarioService.save(usuario);
        
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }
    
    @GetMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session) {
        // logger.info("Accesos : {}", usuario);
        
        Optional<Usuario> user = usuarioService.findById((String) session.getAttribute("idusuario"));
        logger.info("Usuario de db: {}", user.get()); /// aca veridficamos en la consola cual es el usuario obtenido 
        
        if (user.isPresent()) {
            session.setAttribute("idUsuario", user.get().getId());
            if (user.get().getTipo().equals("ADMIN")) {
                return "redirect:/administrador";
            } else {
                return "redirect:/";
            }
        }
        
        return "redirect:/";
    }
    
    @GetMapping("/compras")
    public String obtenerCompras(Model modelo, HttpSession session) {
        
        modelo.addAttribute("sesion", session.getAttribute("idUsuario"));
        
        Usuario usuario = usuarioService.findById(session.getAttribute("idUsuario").toString()).get();
        
        List<Orden> ordenes = ordenService.findByUsuario(usuario);
        
        modelo.addAttribute("ordenes", ordenes);
        
        return "usuario/compras";
        
    }
    
    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable String id, HttpSession session, Model modelo) {
        logger.info("id de la orden: {}", id);
        Optional<Orden> orden = ordenService.findById(id);
        
       //session
        modelo.addAttribute("sesion", session.getAttribute("idUsuario"));
        modelo.addAttribute("detalles", orden.get().getDetalle());
        return "usuario/detallecompra";
    }
    
    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session){
        session.removeAttribute("idUsuario");
        
        return "redirect:/";
        
    }
}
