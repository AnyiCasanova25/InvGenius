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

    @Column(name = "asunto", nullable = false, length = 80)
    private String asunto;

    @Column(name = "cuerpo", nullable = false, length = 500)
    private String cuerpo;

    @Column(name = "fechaNovedad", nullable = false)
    private Date fechaNovedad;

    @Column(name = "imagenNovedad", nullable = false)
    private String imagenNovedad;

    public novedad() {
    }

    

    public novedad(String idNovedad, String para, String asunto, String cuerpo, Date fechaNovedad,
            String imagenNovedad) {
        this.idNovedad = idNovedad;
        this.para = para;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.fechaNovedad = fechaNovedad;
        this.imagenNovedad = imagenNovedad;
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

    public Date getFechaNovedad() {
        return fechaNovedad;
    }

    public void setFechaNovedad(Date fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }



    public String getImagenNovedad() {
        return imagenNovedad;
    }



    public void setImagenNovedad(String imagenNovedad) {
        this.imagenNovedad = imagenNovedad;
    }

    
}
