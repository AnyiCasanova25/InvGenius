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
}
