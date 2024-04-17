package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "registroProveedorMarca")
public class registroProveedorMarca {
    /*
     * id marca
     * id proveedor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idSoliSeguridad", nullable = false, length = 36)
    private String idSoliSeguridad;

    @ManyToMany
    @JoinColumn(name = "idMarca")
    private  marca marca;

    @OneToOne
    @JoinColumn(name = "idProveedor")
    private  proveedor proveedor;

    public registroProveedorMarca() {
    }

    public registroProveedorMarca(String idSoliSeguridad, com.InvGenius.InvGenius.models.marca marca,
            com.InvGenius.InvGenius.models.proveedor proveedor) {
        this.idSoliSeguridad = idSoliSeguridad;
        this.marca = marca;
        this.proveedor = proveedor;
    }

    public String getIdSoliSeguridad() {
        return idSoliSeguridad;
    }

    public void setIdSoliSeguridad(String idSoliSeguridad) {
        this.idSoliSeguridad = idSoliSeguridad;
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
