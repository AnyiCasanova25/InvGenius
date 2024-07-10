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

    @Column(name = "asignarUbicacion", nullable = false, length = 36)
    private String asignarUbicacion;

    @Column(name =  "bloques", nullable = false, length = 10)
    private String bloques;

    public ubicacion() {
    }

    public ubicacion(String idUbicacion, String asignarUbicacion, String bloques) {
        this.idUbicacion = idUbicacion;
        this.asignarUbicacion = asignarUbicacion;
        this.bloques = bloques;
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

    public String getBloques() {
        return bloques;
    }

    public void setBloques(String bloques) {
        this.bloques = bloques;
    }

}
