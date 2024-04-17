package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "ubicacion")
public class ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idUbicacion", nullable = false, length = 36)
    private String idUbicacion;

    @Column(name = "asignarUbicacion", nullable = false, length = 20)
    private String asignarUbicacion;

    @Column(name = "listarProductoEnUbicacion", nullable = false, length = 30)
    private String listarProductoEnUbicacion;

    public ubicacion() {
    }

    public ubicacion(String idUbicacion, String asignarUbicacion, String listarProductoEnUbicacion) {
        this.idUbicacion = idUbicacion;
        this.asignarUbicacion = asignarUbicacion;
        this.listarProductoEnUbicacion = listarProductoEnUbicacion;
    }

    public String getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(String idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getAsignarUbicacion() {
        return asignarUbicacion;
    }

    public void setAsignarUbicacion(String asignarUbicacion) {
        this.asignarUbicacion = asignarUbicacion;
    }

    public String getListarProductoEnUbicacion() {
        return listarProductoEnUbicacion;
    }

    public void setListarProductoEnUbicacion(String listarProductoEnUbicacion) {
        this.listarProductoEnUbicacion = listarProductoEnUbicacion;
    }


}
