package com.InvGenius.InvGenius.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "solicitudSeguridad")
public class solicitudSeguridad {

    @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(name = "idSolicitud", nullable = false, length = 36)
     private String idSolicitud;

     @Column(name = "estadoSolicitud", nullable = false, length = 36)
     private String estadoSolicitud;

     @Column(name = "codigoSeguridad", nullable = false, length = 36)
     private String codigoSeguridad;

     @Column(name = "fechaHora", nullable = false, length = 36)
     private Date fechaHora;

     @ManyToOne
     @JoinColumn(name = "idUser")
     private user user;

    public solicitudSeguridad() {
    }

    public solicitudSeguridad(String idSolicitud, String estadoSolicitud, String codigoSeguridad, Date fechaHora,
            com.InvGenius.InvGenius.models.user user) {
        this.idSolicitud = idSolicitud;
        this.estadoSolicitud = estadoSolicitud;
        this.codigoSeguridad = codigoSeguridad;
        this.fechaHora = fechaHora;
        this.user = user;
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

}

