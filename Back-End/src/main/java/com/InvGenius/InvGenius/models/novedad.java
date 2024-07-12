package com.InvGenius.InvGenius.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "novedad")
public class novedad {
    
    // Para
    // Asunto
    // Cuerpo
    // Evidencia
    // Numero novedad
    // fecha novedad

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idNovedad", nullable = false,length = 36)
    private String idNovedad;

    @Column(name = "para",nullable = false,length = 50)
    private String para;

    @Column(name = "asunto",nullable = false,length = 50)
    private String asunto;

    @Column(name = "cuerpo", nullable = false, length = 299)
    private String cuerpo;

    @Column(name = "evidencia", nullable = false)
    private String enlaceEvidencia;

    @Column(name = "numeroNovedad" , nullable = false, length = 6)
    private int numeroNovedad;

    @Column(name = "fechaNovedad", nullable = false,length = 10)
    private Date fechaNovedad;

    public novedad() {
    }

   
    public novedad(String idNovedad, String para, String asunto, String cuerpo, String enlaceEvidencia,
            int numeroNovedad, Date fechaNovedad) {
        this.idNovedad = idNovedad;
        this.para = para;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.enlaceEvidencia = enlaceEvidencia;
        this.numeroNovedad = numeroNovedad;
        this.fechaNovedad = fechaNovedad;
    }


    public String getIdNovedad() {
        return idNovedad;
    }


    public void setIdNovedad(String idNovedad) {
        this.idNovedad = idNovedad;
    }


    public String getPara() {
        return para;
    }


    public void setPara(String para) {
        this.para = para;
    }


    public String getAsunto() {
        return asunto;
    }


    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }


    public String getCuerpo() {
        return cuerpo;
    }


    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }


    public String getEnlaceEvidencia() {
        return enlaceEvidencia;
    }


    public void setEnlaceEvidencia(String enlaceEvidencia) {
        this.enlaceEvidencia = enlaceEvidencia;
    }


    public int getNumeroNovedad() {
        return numeroNovedad;
    }


    public void setNumeroNovedad(int numeroNovedad) {
        this.numeroNovedad = numeroNovedad;
    }


    public Date getFechaNovedad() {
        return fechaNovedad;
    }


    public void setFechaNovedad(Date fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }
    
}

