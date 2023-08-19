/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controller;

import com.ecommerce.model.DetalleOrden;
import com.ecommerce.model.Orden;
import com.ecommerce.model.Producto;
import org.slf4j.Logger;
import com.ecommerce.service.ProductoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    /// para almacenar los detalles de la orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    /// Datos de la orden 
    Orden orden = new Orden();

    @GetMapping("")
    public String home(Model modelo) {

        modelo.addAttribute("productos", productoService.findAll());

        return "usuario/home";

    }
    //// con este metodo mostramos el producto en la pagina

    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable String id, Model modelo) {
        log.info("id producto enviado como parametro {}", id);

        Producto producto = new Producto();

        Optional<Producto> productoRespuesta = productoService.get(id);

        producto = productoRespuesta.get();

        modelo.addAttribute("producto", producto);

        return "usuario/productohome";
    }

    /// con este metodo añadimos el carrito de compras 
    @PostMapping("/cart")
    public String addCart(@RequestParam String id, @RequestParam Double cantidad, Model modelo) {

        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();

        double sumaTotal = 0;

        Optional<Producto> respuestaProducto = productoService.get(id);
        /// ponemso un log para que nos pase en pantalla del output si manda los datos que necesitamos
        log.info("Producto anadido: {} ", respuestaProducto.get());
        log.info("Cantidad: {}", cantidad);

        producto = respuestaProducto.get();
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);

        // VALIDAR QUE EL PRODCUTO NO SE PUEDA AÑADIR MAS DE DOS VECES
        /// Ahora obtenemos el  id del producto agregado
        String idProducto = producto.getId();

        /// ATAVES DE UNA FNCION LAMDA (API DE JAVA) VAMOS A VERIFICAR SI EL ID  YA SE ENCUENTRA EN LA LISTA DEATELLES
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

        //si devulve false lo añadimos a la lista sino no lo añadimos
        if (!ingresado) {
            detalles.add(detalleOrden);
        }

        /// ahora utilizamos una funcion landa
        /// con esto sumamos los totales de la lista
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        modelo.addAttribute("cart", detalles);
        modelo.addAttribute("orden", orden);

        return "usuario/carrito.html";

    }

    /// quitar un producto del carrito 
    @GetMapping("/delete/cart/{id}")
    public String deleteProdcutoCart(@PathVariable String id, Model modelo) {

        /// Lista nueva de productos
        List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();

        //// Con esto vamos a crear una nueva lista, excluyendo el elemento a eliminar
        for (DetalleOrden detalleOrden : detalles) {
            if (!detalleOrden.getProducto().getId().equals(id)) {
                ordenesNueva.add(detalleOrden);
            }
        }

        /// Actualizamos la lista de productos en el carrito
        detalles = ordenesNueva;

        /// Recalculamos el valor total de los productos que quedan en el carrito de compras
        double sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        modelo.addAttribute("cart", detalles);
        modelo.addAttribute("orden", orden);

        return "usuario/carrito.html";
    }
    
    
    @GetMapping("/getCart")
    public String getCart(Model modelo){
        
        modelo.addAttribute("cart", detalles);
        modelo.addAttribute("orden", orden);
        return "/usuario/carrito";
        
    }

}
