package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity(name = "registroProveedorMarca")
public class registroProveedorMarca {
    /*
     * id marca
     * id proveedor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idRegistro", nullable = false, length = 36)
    private String idRegistro;

    @ManyToOne
    @JoinColumn(name = "idMarca")
    private  marca marca;

    @ManyToOne
    @JoinColumn(name = "idProveedor")
    private  proveedor proveedor;

    public registroProveedorMarca() {
    }

    public registroProveedorMarca(String idRegistro, com.InvGenius.InvGenius.models.marca marca,
            com.InvGenius.InvGenius.models.proveedor proveedor) {
        this.idRegistro = idRegistro;
        this.marca = marca;
        this.proveedor = proveedor;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public marca getMarca() {
        return marca;
    }

    public void setMarca(marca marca) {
        this.marca = marca;
    }

    public proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(proveedor proveedor) {
        this.proveedor = proveedor;
    }

    

}
