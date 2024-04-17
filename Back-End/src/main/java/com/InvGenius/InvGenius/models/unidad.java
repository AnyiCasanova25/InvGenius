package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "unidad")
public class unidad {
    
    /*id
     * nombre unidad
     * estado unidad
     */

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(name = "idUnidad", nullable = false, length = 36)
     private String idUser;

     @Column(name = "nombreUnidad", nullable = false, length = 36)
     private String nombreUnidad;

     @Column(name = "estadoUnidad", nullable = false, length = 20)
     private String estadoUnidad;

    public unidad() {
    }

    public unidad(String idUser, String nombreUnidad, String estadoUnidad) {
        this.idUser = idUser;
        this.nombreUnidad = nombreUnidad;
        this.estadoUnidad = estadoUnidad;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public String getEstadoUnidad() {
        return estadoUnidad;
    }

    public void setEstadoUnidad(String estadoUnidad) {
        this.estadoUnidad = estadoUnidad;
    }

     
}
