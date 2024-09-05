package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "imagenProducto", nullable = false)
    private String imagenProducto;

}
