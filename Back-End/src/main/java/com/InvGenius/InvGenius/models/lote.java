package com.InvGenius.InvGenius.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity(name = "lote")
public class lote {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idLote", nullable = false, length = 36)
    private String idLote;

    @Column(name = "fechaIngreso", nullable = false, length = 10)
    private Date fechaIngreso;

    @Column(name = "fechaVencimiento", nullable = false, length = 10)
    private Date fechaVencimiento;

    @Column(name = "cantidad", nullable = false, length = 36)
    private String cantidad;

    @Column(name = "numeroLote", nullable = false, length = 30)
    private String numeroLote;

    @Column(name = "codigoLote", nullable = false, length = 20)
    private String codigoLote;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private producto producto;

    @ManyToOne
    @JoinColumn(name = "idUbicacion")
    private ubicacion ubicacion;

    public lote() {
    }

    public lote(String idLote, Date fechaIngreso, Date fechaVencimiento, String cantidad, String numeroLote,
            String codigoLote, com.InvGenius.InvGenius.models.producto producto,
            com.InvGenius.InvGenius.models.ubicacion ubicacion) {
        this.idLote = idLote;
        this.fechaIngreso = fechaIngreso;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.numeroLote = numeroLote;
        this.codigoLote = codigoLote;
        this.producto = producto;
        this.ubicacion = ubicacion;
    }

    public String getIdLote() {
        return idLote;
    }

    public void setIdLote(String idLote) {
        this.idLote = idLote;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public String getCodigoLote() {
        return codigoLote;
    }

    public void setCodigoLote(String codigoLote) {
        this.codigoLote = codigoLote;
    }

    public producto getProducto() {
        return producto;
    }

    public void setProducto(producto producto) {
        this.producto = producto;
    }

    public ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

}
