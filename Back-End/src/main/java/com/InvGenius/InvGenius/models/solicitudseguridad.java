package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "solicitudSeguridad")
public class solicitudSeguridad {
    /*
     * id Solicitud Seguridad
     * id usuario
     * fecha y hora
     * estado solicitud seguridad
     * codigo seguridad 
     * laura
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idSoliSeguridad", nullable = false, length = 36)
    private String idSoliSeguridad;

    @Column(name = "idUsuario", nullable = false, length = 36)
    private String idUsuario;

    @Column(name = "fechaHora", nullable = false, length = 36)
    private String fechaHora;

    @Column(name = "estadoSoliSeguridad", nullable = false, length = 36)
    private String estadoSoliSeguridad;

    @Column(name = "codigoSeguridad", nullable = false, length = 36)
    private String codigoSeguridad;

    public solicitudSeguridad() {
    }

    public solicitudSeguridad(String idSoliSeguridad, String idUsuario, String fechaHora, String estadoSoliSeguridad,
            String codigoSeguridad) {
        this.idSoliSeguridad = idSoliSeguridad;
        this.idUsuario = idUsuario;
        this.fechaHora = fechaHora;
        this.estadoSoliSeguridad = estadoSoliSeguridad;
        this.codigoSeguridad = codigoSeguridad;
    }

    public String getIdSoliSeguridad() {
        return idSoliSeguridad;
    }

    public void setIdSoliSeguridad(String idSoliSeguridad) {
        this.idSoliSeguridad = idSoliSeguridad;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstadoSoliSeguridad() {
        return estadoSoliSeguridad;
    }

    public void setEstadoSoliSeguridad(String estadoSoliSeguridad) {
        this.estadoSoliSeguridad = estadoSoliSeguridad;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

     
    
}
