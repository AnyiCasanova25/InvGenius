package com.InvGenius.InvGenius.models;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity(name = "movimientos")
public class movimientos {
    /*
     * id
     * tipo de movimiento
     * id producto "llave foranea"
     * cantidad de producto 
     * id usuario 
     * fecha de movimiento
     */

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(name = "idMovimiento", nullable = false, length = 36)
     private String idMovimiento;

     @ManyToOne
     @JoinColumn(name = "idProducto")
     private  producto producto;

     @Column(name = "tipoMovimiento", nullable = false, length = 36)
     private String tipomovimiento;

     @Column(name = "CantidadProducto", nullable = false, length = 36)
     private String CantidadProducto;

     @ManyToOne
     @JoinColumn(name = "idUser")
     private user user;


     @Column(name = "FechaMovimiento", nullable = false, length = 36)
     private Date FechaMovimiento;

    public movimientos() {
    }

    public movimientos(String idMovimiento, com.InvGenius.InvGenius.models.producto producto, String tipomovimiento,

            String cantidadProducto, user user, Date fechaMovimiento) {

        this.idMovimiento = idMovimiento;
        this.producto = producto;
        this.tipomovimiento = tipomovimiento;
        CantidadProducto = cantidadProducto;
        this.user = user;
        FechaMovimiento = fechaMovimiento;
    }

    public String getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(String idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public producto getProducto() {
        return producto;
    }

    public void setProducto(producto producto) {
        this.producto = producto;
    }

    public String getTipomovimiento() {
        return tipomovimiento;
    }

    public void setTipomovimiento(String tipomovimiento) {
        this.tipomovimiento = tipomovimiento;
    }

    public String getCantidadProducto() {
        return CantidadProducto;
    }

    public void setCantidadProducto(String cantidadProducto) {
        CantidadProducto = cantidadProducto;
    }

    public user getidUser() {
        return user;
    }

    public void setidUser(user user) {
        this.user = user;

    }

    public Date getFechaMovimiento() {
        return FechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        FechaMovimiento = fechaMovimiento;
    }

     

    

}
