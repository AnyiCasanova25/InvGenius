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

    @Column(name = "precioProducto" , nullable = false, length = 36)
    private String precioProducto;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private categoria categoria;

    @Column(name = "stock" , nullable = false, length = 36)
    private String stock;

    @Column(name = "precioCompra" , nullable = false, length = 36)
    private String precioCompra;

    @Column(name = "descripcionProducto", nullable = false, length = 199)
    private String descripcionProducto;

    @ManyToOne
    @JoinColumn(name = "idUnidad")
    private unidad unidad;

    @Column(name = "imagenProducto", nullable = false)
    private String imagenProducto;

    public producto() {
    }


    
    public producto(String idProducto, String nombreProducto, String estadoProducto,
            com.InvGenius.InvGenius.models.marca marca, String precioProducto,
            com.InvGenius.InvGenius.models.categoria categoria, String stock, String precioCompra,
            String descripcionProducto, com.InvGenius.InvGenius.models.unidad unidad, String imagenProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.estadoProducto = estadoProducto;
        this.marca = marca;
        this.precioProducto = precioProducto;
        this.categoria = categoria;
        this.stock = stock;
        this.precioCompra = precioCompra;
        this.descripcionProducto = descripcionProducto;
        this.unidad = unidad;
        this.imagenProducto = imagenProducto;
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

    public String getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(String precioProducto) {
        this.precioProducto = precioProducto;
    }

    public categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(categoria categoria) {
        this.categoria = categoria;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(String precioCompra) {
        this.precioCompra = precioCompra;
    }

    public unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(unidad unidad) {
        this.unidad = unidad;
    }
    
    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    //Se creo el get y set de descripcion producto, hacia falta eso y por eso no dejaba enviar este atributo, ya que estaba null
    public String getDescripcionProducto() {
        return this.descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }
}
