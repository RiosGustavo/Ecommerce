/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.service;

import com.ecommerce.model.Orden;
import com.ecommerce.model.Usuario;
import com.ecommerce.repository.IOrdenRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class OrdenServiceImpl implements IOrdenService{

    @Autowired
    private IOrdenRepository ordenRepository;
        
    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }
     /// CON ESTE METODO LISTAMOS LAS ORDENES
    @Override
    public List<Orden> findAll() {
       return ordenRepository.findAll();
    }
    
/// con este obtenemos el secuencial del número de la orden
    @Override
    public String generarNumeroOrden(){
        int numero=0;
        String numeroConcatenado=""; // esto como es un string lo vamos a concatenar
        
        List<Orden> ordenes = findAll(); 
        List<Integer> numeros = new ArrayList<Integer>(); /// aca viene lo s numero sde orden
        
/// dentro del foreach decalramos una funcion anonima (flecha) a las ordenes el pasamos los numero pero ya como un entero
        ordenes.stream().forEach( o -> numeros.add(Integer.parseInt(o.getNumero()))); /// esto para poder hacer el incremento
        
        /// con este if comprobamos si la lista de ordenes viene vacia o no
        
        if(ordenes.isEmpty()){
            numero = 1;
        }else{
            /// si no viene vacia obtenemos el mayor numero que viene de la lista
            numero = numeros.stream().max(Integer::compare).get();
            numero++; // ahor hacemos el incremento 
            
        }
        
        ///ahora el nuemro entero hay que pasarlo de nueveo a cadena String
        
        if(numero<10){
            // evaluamos primero si el numero esta entre 0 y 9 
            numeroConcatenado="000000000"+String.valueOf(numero);
            
        }else if(numero<100){
            numeroConcatenado="00000000"+String.valueOf(numero);
        }else if(numero<1000){
            numeroConcatenado="0000000"+String.valueOf(numero);
        }else if(numero<10000){
            numeroConcatenado="000000"+String.valueOf(numero);
        }
        
        
        return numeroConcatenado;
    }

    @Override
    public List<Orden> findByUsuario(Usuario usuario) {
        return ordenRepository.findByUsuario(usuario);
    }
    
}
