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
    // numNovedad
    // fechaNovedad

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idNovedad", nullable = false, length = 36)
    private String idNovedad;

    @Column(name = "para", nullable = false, length = 100)
    private String para;

    @Column(name = "asunto", nullable = false, length = 100)
    private String asunto;

    @Column(name = "cuerpo", nullable = false, length = 100)
    private String cuerpo;

    @Column(name = "evidencia", nullable = false, length = 100)
    private String evidencia;

    @Column(name = "numNovedad", nullable = false, length = 100)
    private String numNovedad;

    @Column(name = "fechaNovedad", nullable = false)
    private Date fechaNovedad;

    public novedad() {
    }

    public novedad(String idNovedad, String para, String asunto, String cuerpo, String evidencia, String numNovedad,
            Date fechaNovedad) {
        this.idNovedad = idNovedad;
        this.para = para;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.evidencia = evidencia;
        this.numNovedad = numNovedad;
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

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public String getNumNovedad() {
        return numNovedad;
    }

    public void setNumNovedad(String numNovedad) {
        this.numNovedad = numNovedad;
    }

    public Date getFechaNovedad() {
        return fechaNovedad;
    }

    public void setFechaNovedad(Date fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

    
}
