package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "proveedor")
public class proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idProveedor", nullable = false, length = 36)
    private String idProveedor;

    @Column(name = "marcasProveedor", nullable = false, length = 20)
    private String marcasProveedor;

    public proveedor() {
    }

    public proveedor(String idProveedor, String marcasProveedor) {
        this.idProveedor = idProveedor;
        this.marcasProveedor = marcasProveedor;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getMarcasProveedor() {
        return marcasProveedor;
    }

    public void setMarcasProveedor(String marcasProveedor) {
        this.marcasProveedor = marcasProveedor;
    }

}