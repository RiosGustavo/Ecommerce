/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author User
 */

/// esta va hacer una clse de configuracion por eso le ponemos
@Configuration
public class ResourceWebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/images/**").addResourceLocations("file:images/"); /// los ** le decimos que tome todo lo que esta dentro de la carpeta images
                                                                                        /// tambien se configura hacia cual directorio va a apuntar addResourceLocations("file:images/");
        
    }
    
    
}
