package com.InvGenius.InvGenius.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "solicitudSegurida")
public class solicitudSegurida {
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
    private Date fechaHora;

    @Column(name = "estadoSoliSeguridad", nullable = false, length = 36)
    private String estadoSoliSeguridad;

    @Column(name = "codigoSeguridad", nullable = false, length = 36)
    private String codigoSeguridad;

    public solicitudSegurida() {
    }

    public solicitudSegurida(String idSoliSeguridad, String idUsuario, Date fechaHora, String estadoSoliSeguridad,
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

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
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
