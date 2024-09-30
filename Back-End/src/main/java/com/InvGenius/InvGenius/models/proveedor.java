package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "proveedor")
public class proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idProveedor", nullable = false, length = 36)
    private String idProveedor;

    @Column(name = "nombreProveedor", nullable = false, length = 40)
    private String nombreProveedor;

    @Column(name = "apellidoProveedor", nullable = false, length = 40)
    private String apellidoProveedor;

    @Column(name = "documentoProveedor", nullable = false, length = 11)
    private String documentoProveedor;

    @Column(name = "empresaProveedor", nullable = false, length = 40)
    private String empresaProveedor;

    @Column(name = "estadoProveedor", nullable = false, length = 40)
    private String estadoProveedor;

    // @Enumerated(EnumType.STRING)
    // //@Column(name = "estadoProveedor", nullable = false, length = 20)
    // private estadoProveedor estadoProveedor;

    @Column(name = "numeroProveedor", nullable = false, length = 15)
    private String numeroProveedor;

}