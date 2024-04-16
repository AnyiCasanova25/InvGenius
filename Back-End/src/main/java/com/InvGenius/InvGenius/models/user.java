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
     */

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(name = "idUser", nullable = false, length = 36)
     private String idUser;

     @Column(name = "documentoIdentidad", nullable = false, length = 36)
     private String documentoIdentidad;

     @Column(name = "primerNombre", nullable = false, length = 36)
     private String primerNombre;

     @Column(name = "segundoNombre", nullable = false, length = 36)
     private String segundoNombre;

     @Column(name = "primerApellido", nullable = false, length = 36)
     private String primerApellido;

     @Column(name = "segundoApellido", nullable = false, length = 36)
     private String segundoApellido;

     @Column(name = "Celular", nullable = false, length = 36)
     private String Celular;

     @Column(name = "Correo", nullable = false, length = 36)
     private String Correo;

     @Column(name = "Contraseña", nullable = false, length = 36)
     private String Contraseña;

     @Column(name = "confirmarContraseña", nullable = false, length = 36)
     private String confirmarContraseña;

    public user() {
    }

    public user(String idUser, String documentoIdentidad, String primerNombre, String segundoNombre,
            String primerApellido, String segundoApellido, String celular, String correo, String contraseña,
            String confirmarContraseña) {
        this.idUser = idUser;
        this.documentoIdentidad = documentoIdentidad;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        Celular = celular;
        Correo = correo;
        Contraseña = contraseña;
        this.confirmarContraseña = confirmarContraseña;
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

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getConfirmarContraseña() {
        return confirmarContraseña;
    }

    public void setConfirmarContraseña(String confirmarContraseña) {
        this.confirmarContraseña = confirmarContraseña;
    }  

}
