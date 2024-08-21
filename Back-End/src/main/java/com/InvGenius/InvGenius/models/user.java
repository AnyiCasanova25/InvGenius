package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    public user() {
    }

    


    public user(String idUser, String documentoIdentidad, String nombres, String apellidos, String celular,
            String correo, String password, String confirmarPassword, String rol, String imagenUser) {
        this.idUser = idUser;
        this.documentoIdentidad = documentoIdentidad;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.correo = correo;
        this.password = password;
        this.confirmarPassword = confirmarPassword;
        this.rol = rol;
        this.imagenUser = imagenUser;
    }




    public String getIdUser() {
        return idUser;
    }




    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }




    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }




    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }




    public String getNombres() {
        return nombres;
    }




    public void setNombres(String nombres) {
        this.nombres = nombres;
    }




    public String getApellidos() {
        return apellidos;
    }




    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }




    public String getCelular() {
        return celular;
    }




    public void setCelular(String celular) {
        this.celular = celular;
    }




    public String getCorreo() {
        return correo;
    }




    public void setCorreo(String correo) {
        this.correo = correo;
    }




    public String getPassword() {
        return password;
    }




    public void setPassword(String password) {
        this.password = password;
    }




    public String getConfirmarPassword() {
        return confirmarPassword;
    }




    public void setConfirmarPassword(String confirmarPassword) {
        this.confirmarPassword = confirmarPassword;
    }




    public String getRol() {
        return rol;
    }




    public void setRol(String rol) {
        this.rol = rol;
    }




    public String getImagenUser() {
        return imagenUser;
    }




    public void setImagenUser(String imagenUser) {
        this.imagenUser = imagenUser;
    }


}
