package com.InvGenius.InvGenius.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

//Se indica que este es un archivo de configuracion
@Configuration
//configure las seguridad del proyecto
@EnableWebSecurity
//se incluye el constructor con lombok
@RequiredArgsConstructor
public class securityConfig {
    //el filter chain es el encargado de filtrar las peticiones
    //e indicar el acceso a los end point
    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http
    )throws Exception
    {
        return http
        .csrf(csrf ->csrf.disable())
        .authorizeHttpRequests(
            authRequest -> authRequest
            /*todas las peticiones que comiencen con 
             * /api/v1/public/
             * van a ser permitidas sin restriccion de usuario
             */
            .requestMatchers("/api/v1/public/")
            .permitAll()
            //para el resto de peticiones 
            //es requerido que se autentique
            .anyRequest().authenticated()
        ).build();
    }
}
