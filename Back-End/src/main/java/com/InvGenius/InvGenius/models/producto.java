package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity(name = "producto")
public class producto {
    
    /*id 
     * nombre producto
     * id marca
     * precio
     * id categoria
     * stock 
     * precio compra
     * precio venta
     * id unidad
     */

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(name = "idProducto" , nullable = false, length = 36)
     private String idProducto;

     @Column(name = "nombreProducto" , nullable = false, length = 36)
     private String nombreProducto;

     @Column(name = "estadoProducto" , nullable = false, length = 36)
     private String estadoProducto;

    @ManyToOne
    @JoinColumn(name = "idMarca")
    private marca marca;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private categoria categoria;

    @Column(name = "stock" , nullable = false, length = 36)
    private int stock;

    @Column(name = "descripcionProducto", nullable = false, length = 199)
    private String descripcionProducto;

    @Column(name = "unidadMedida", nullable = false,length = 199)
    private String unidadMedida;

    @Column( name="imagen_base", nullable = true, columnDefinition = "MEDIUMBLOB")
	private String  imagen_base;

    public producto() {
    }

    

    public producto(String idProducto, String nombreProducto, String estadoProducto,
            com.InvGenius.InvGenius.models.marca marca, com.InvGenius.InvGenius.models.categoria categoria, int stock,
            String descripcionProducto, String unidadMedida, String imagen_base) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.estadoProducto = estadoProducto;
        this.marca = marca;
        this.categoria = categoria;
        this.stock = stock;
        this.descripcionProducto = descripcionProducto;
        this.unidadMedida = unidadMedida;
        this.imagen_base = imagen_base;
    }



    public String getIdProducto() {
        return idProducto;
    }



    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }



    public String getNombreProducto() {
        return nombreProducto;
    }



    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }



    public String getEstadoProducto() {
        return estadoProducto;
    }



    public void setEstadoProducto(String estadoProducto) {
        this.estadoProducto = estadoProducto;
    }



    public marca getMarca() {
        return marca;
    }



    public void setMarca(marca marca) {
        this.marca = marca;
    }



    public categoria getCategoria() {
        return categoria;
    }



    public void setCategoria(categoria categoria) {
        this.categoria = categoria;
    }



    public int getStock() {
        return stock;
    }



    public void setStock(int stock) {
        this.stock = stock;
    }



    public String getDescripcionProducto() {
        return descripcionProducto;
    }



    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }



    public String getUnidadMedida() {
        return unidadMedida;
    }



    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getImagen_base() {
        return imagen_base;
    }



    public void setImagen_base(String imagen_base) {
        this.imagen_base = imagen_base;
    }

    
}
