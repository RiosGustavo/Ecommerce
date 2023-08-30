/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controller;

import com.ecommerce.model.Producto;
import com.ecommerce.model.Usuario;
import com.ecommerce.service.IUsuarioService;

import com.ecommerce.service.ProductoService;
import com.ecommerce.service.UploadFileService;
import com.ecommerce.service.UsuarioServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.slf4j.*; //// con el * le decimos que importe todo lo que tiene este paquete 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private UploadFileService uploadFileService;
    
    @Autowired
    private IUsuarioService usuarioService;

    //// con este medoto redireccionamos hacia la vista show
    @GetMapping("")
    public String show(Model modelo) {
        modelo.addAttribute("productos", productoService.findAll());
        return "productos/show";
    }

    ////ahro ahacemos otro metodo el cual nosretornara al html para crer los proudctos
    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }

    @PostMapping("/save")                   //// del campo del formulario create traemos el name= img
    public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        LOGGER.info("Este es el objeto producto {}", producto);
        
        Usuario usuario = usuarioService.findById((String) session.getAttribute("idUsuario")).get();
        
      

        producto.setUsuario(usuario);

        // imagen
        /// validamos que la imagen se cargue por primera vez esto lo validamos cuando hacemos cuadno creamos el producto 
        if (producto.getId() == null) {
            String nombreImagen = uploadFileService.saveImage(file);
            producto.setImagen(nombreImagen);
        } else {

        }

        productoService.save(producto);
        return "redirect:/productos";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model modelo) {

        Producto producto = new Producto();
        Optional<Producto> respuestaProducto = productoService.get(id);
        producto = respuestaProducto.get();

        /// con lo sigueinte vemso en el output si nostrae el objeto buscado
        LOGGER.info("Producto buscado: {}", producto);
        modelo.addAttribute("producto", producto);

        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {

        /// ahora obtenemos el producto a modificar
        Producto p = new Producto();
        p = productoService.get(producto.getId()).get();
        
        /// aca es cuando se modifica el producto pero se carga la misma imagen
        if (file.isEmpty()) {

            producto.setImagen(p.getImagen());

        } else {
            /// este es el caso de cambiar la imagen cuando se modifica el producto


            /// aca le decimos que borre la imagen siempre y cuando la imagen no sea la imagen por defecto
            if (!p.getImagen().equals("default.jpg")) {
                uploadFileService.deleteImage(p.getImagen());

            }

            String nombreImagen = uploadFileService.saveImage(file);
            producto.setImagen(nombreImagen);

        }
        
        producto.setUsuario(p.getUsuario());

        productoService.upDate(producto);
        
        return "redirect:/productos";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {

        Producto p = new Producto();
        p = productoService.get(id).get();

        /// aca le decimos que borre la imagen siempre y cuando la imagen no sea la imagen por defecto
        if (!p.getImagen().equals("default.jpg")) {
            uploadFileService.deleteImage(p.getImagen());

        }

        productoService.delete(id);
        return "redirect:/productos";
    }

}
