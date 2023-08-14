/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author User
 */
@Service
public class UploadFileService {
    /// esta variable va  a tener la ubiciacion donde se van a guardar las imagenes
    private String folder="images//";
    
    public String saveImage(MultipartFile file) throws IOException{
        /// validamos que se haya cargado una imagen
        if(!file.isEmpty()){
            /// convertimos nuesra imagen a bytes 
            byte [] bytes = file.getBytes();
            
            /// ahora le decimos donde queremos que se almacene la imagen 
            Path path = Paths.get(folder+file.getOriginalFilename());
            Files.write(path,bytes);
            //// este metodo retorna el nombre que tiene la imagen 
            return file.getOriginalFilename();
        }
        /// si el usuario no carga una imagen le ponemos que retorne una imagen por dafault
        return "default.jpg";
    }
    
    public void deleteImage(String nombre){
         // creamos una variable  ruta con la ruta de dodne esta almacenada la imagen 
        String ruta="images//";
        
        File file = new File(ruta+nombre);
        file.delete();
        
        
    }
    
}
