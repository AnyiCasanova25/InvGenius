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

    @Column(name = "nombreProveedor", nullable = false, length = 20)
    private String nombreProveedor;

    @Column(name = "apellidoProveedor", nullable = false, length = 20)
    private String apellidoProveedor;

    @Column(name = "documentoProveedor", nullable = false, length = 20)
    private String documentoProveedor;

    @Column(name = "empresaProveedor", nullable = false, length = 20)
    private String empresaProveedor;

    @Column(name = "estadoProveedor", nullable = false, length = 20)
    private String estadoProveedor;

    public proveedor() {
    }

    public proveedor(String idProveedor, String nombreProveedor, String apellidoProveedor, String documentoProveedor,
            String empresaProveedor, String estadoProveedor) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.apellidoProveedor = apellidoProveedor;
        this.documentoProveedor = documentoProveedor;
        this.empresaProveedor = empresaProveedor;
        this.estadoProveedor = estadoProveedor;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getApellidoProveedor() {
        return apellidoProveedor;
    }

    public void setApellidoProveedor(String apellidoProveedor) {
        this.apellidoProveedor = apellidoProveedor;
    }

    public String getDocumentoProveedor() {
        return documentoProveedor;
    }

    public void setDocumentoProveedor(String documentoProveedor) {
        this.documentoProveedor = documentoProveedor;
    }

    public String getEmpresaProveedor() {
        return empresaProveedor;
    }

    public void setEmpresaProveedor(String empresaProveedor) {
        this.empresaProveedor = empresaProveedor;
    }

    public String getEstadoProveedor() {
        return estadoProveedor;
    }

    public void setEstadoProveedor(String estadoProveedor) {
        this.estadoProveedor = estadoProveedor;
    }

   
}