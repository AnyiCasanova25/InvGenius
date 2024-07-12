package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity (name = "categoria")
public class categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idCategoria", nullable = false, length = 36)
    private String idCategoria;

    @Column(name = "nombreCategoria", nullable = false, length = 40)
    private String nombreCategoria;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @OneToOne
    @JoinColumn(name = "idUbicacion")
    private ubicacion ubicacion;

    public categoria() {
    }

    public categoria(String idCategoria, String nombreCategoria, String estado,
            com.InvGenius.InvGenius.models.ubicacion ubicacion) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.estado = estado;
        this.ubicacion = ubicacion;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

}
