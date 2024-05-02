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

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private producto producto;

    public lote() {

    }

    public lote(String idLote, Date fechaIngreso, Date fechaVencimiento, String cantidad,
            com.InvGenius.InvGenius.models.producto producto) {
        this.idLote = idLote;
        this.fechaIngreso = fechaIngreso;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.producto = producto;
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

    public producto getProducto() {
        return producto;
    }

    public void setProducto(producto producto) {
        this.producto = producto;
    }

}
