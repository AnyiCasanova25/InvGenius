package com.InvGenius.InvGenius.models;


import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "informe")
public class informe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idInforme", nullable = false, length = 36)
    private String idInforme;

    @Column(name = "horaInforme", nullable = false, length = 20)
    private LocalTime horaInforme;

    @Column(name = "fechaInforme", nullable = false, length = 20)
    private LocalDate fechaInforme;

    @ManyToOne
    @JoinColumn(name = "idMovimiento")
    private movimientos movimientos;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private producto producto;

    @ManyToOne
    @JoinColumn(name = "idMarca")
    private marca marca;
    
    @ManyToOne
    @JoinColumn(name = "idProveedor")
    private proveedor proveedor;
    
    @ManyToOne
    @JoinColumn(name = "idLote")
    private lote lote;

    public informe() {
    }

    public informe(String idInforme, LocalTime horaInforme, LocalDate fechaInforme,
            com.InvGenius.InvGenius.models.movimientos movimientos, com.InvGenius.InvGenius.models.categoria categoria,
            com.InvGenius.InvGenius.models.producto producto, com.InvGenius.InvGenius.models.marca marca,
            com.InvGenius.InvGenius.models.proveedor proveedor, com.InvGenius.InvGenius.models.lote lote) {
        this.idInforme = idInforme;
        this.horaInforme = horaInforme;
        this.fechaInforme = fechaInforme;
        this.movimientos = movimientos;
        this.categoria = categoria;
        this.producto = producto;
        this.marca = marca;
        this.proveedor = proveedor;
        this.lote = lote;
    }

    public String getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(String idInforme) {
        this.idInforme = idInforme;
    }

    public LocalTime getHoraInforme() {
        return horaInforme;
    }

    public void setHoraInforme(LocalTime horaInforme) {
        this.horaInforme = horaInforme;
    }

    public LocalDate getFechaInforme() {
        return fechaInforme;
    }

    public void setFechaInforme(LocalDate fechaInforme) {
        this.fechaInforme = fechaInforme;
    }

    public movimientos getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(movimientos movimientos) {
        this.movimientos = movimientos;
    }

    public categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(categoria categoria) {
        this.categoria = categoria;
    }

    public producto getProducto() {
        return producto;
    }

    public void setProducto(producto producto) {
        this.producto = producto;
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

    public lote getLote() {
        return lote;
    }

    public void setLote(lote lote) {
        this.lote = lote;
    }

    
}
