package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "marca")
public class marca {
    
    /*id
     * nombre marca
     * estado
     */

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(name = "idMarca" , nullable = false, length = 36)
     private String idMarca;

     @Column(name = "nombreMarca" , nullable = false, length = 36)
     private String nombreMarca;

     @Column(name = "estado" , nullable = false, length = 36)
     private String estado;

    public marca() {
    }

    public marca(String idMarca, String nombreMarca, String estado) {
        this.idMarca = idMarca;
        this.nombreMarca = nombreMarca;
        this.estado = estado;
    }

    public String getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(String idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

     
}
