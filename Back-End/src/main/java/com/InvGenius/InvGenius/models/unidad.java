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
     private String idUnidad;

     @Column(name = "nombreUnidad", nullable = false, length = 36)
     private String nombreUnidad;

     @Column(name = "estadoUnidad", nullable = false, length = 20)
     private String estadoUnidad;

     @Column(name = "imagenUnidad", nullable = false)
     private String imagenUnidad;

    public unidad() {
    }


    public unidad(String idUnidad, String nombreUnidad, String estadoUnidad, String imagenUnidad) {
        this.idUnidad = idUnidad;
        this.nombreUnidad = nombreUnidad;
        this.estadoUnidad = estadoUnidad;
        this.imagenUnidad = imagenUnidad;
    }



    public String getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(String idUnidad) {
        this.idUnidad = idUnidad;
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

    public String getImagenUnidad() {
        return imagenUnidad;
    }

    public void setImagenUnidad(String imagenUnidad) {
        this.imagenUnidad = imagenUnidad;
    }
     
}
