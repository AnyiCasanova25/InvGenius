package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class user {
    
    /*id
     * nombres
     * apellidos
     * documentos
     * celular
     * correo
     * contraseña
     * confirmar contraseña
     * rol
     */

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(name = "idUser", nullable = false, length = 36)
     private String idUser;

     @Column(name = "documentoIdentidad", nullable = false, length = 36)
     private String documentoIdentidad;

     @Column(name = "nombres", nullable = false, length = 36)
     private String nombres;

     @Column(name = "apellidos", nullable = false, length = 36)
     private String apellidos;

     @Column(name = "celular", nullable = false, length = 13)
     private String celular;

     @Column(name = "correo", nullable = false, length = 100)
     private String correo;

     @Column(name = "password", nullable = false, length = 8)
     private String password;

     private String confirmarPassword;

     @Column(name = "rol", nullable = false, length = 36)
     private String rol;

     @Column(name = "imagenUser", nullable = false)
     private String imagenUser;

}
