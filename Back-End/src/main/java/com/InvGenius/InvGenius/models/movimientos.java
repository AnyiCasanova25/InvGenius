package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity(name = "movimientos")
public class movimientos {
    /*
     * id
     * tipo de movimiento
     * id producto "llave foranea"
     * cantidad de producto 
     * id usuario "llave"
     * fecha de movimiento
     */

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(name = "idMovimiento", nullable = false, length = 36)
     private String idMovimiento;

     @OneToMany
     @JoinColumn(name = "idProducto")
     private  producto producto;

     @Column(name = "tipoMovimiento", nullable = false, length = 36)
     private String tipomovimiento;

     @Column(name = "CantidadProducto", nullable = false, length = 36)
     private String CantidadProducto;

     @Column(name = "id_User", nullable = false, length = 36)
     private String id_User;

     @Column(name = "FechaMovimiento", nullable = false, length = 36)
     private String FechaMovimiento;

    public movimientos() {
    }

    public movimientos(String idMovimiento, com.InvGenius.InvGenius.models.producto producto, String tipomovimiento,
            String cantidadProducto, String id_User, String fechaMovimiento) {
        this.idMovimiento = idMovimiento;
        this.producto = producto;
        this.tipomovimiento = tipomovimiento;
        CantidadProducto = cantidadProducto;
        this.id_User = id_User;
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

    public String getId_User() {
        return id_User;
    }

    public void setId_User(String id_User) {
        this.id_User = id_User;
    }

    public String getFechaMovimiento() {
        return FechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        FechaMovimiento = fechaMovimiento;
    }

     

    

}
