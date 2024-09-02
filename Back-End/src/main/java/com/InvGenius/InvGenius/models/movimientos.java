package com.InvGenius.InvGenius.models;


import java.util.Date;

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
@Entity(name = "movimientos")
public class movimientos {
    /*
    oo
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

     @Column(name = "DescripcionMovimiento", nullable = false, length = 60)
     private String DescripcionMovimiento;

     @ManyToOne
     @JoinColumn(name = "idUser")
     private user user;

     @ManyToOne
     @JoinColumn(name = "idUnidad")
     private unidad unidad;

     @Column(name = "FechaMovimiento", nullable = false, length = 36)
     private Date FechaMovimiento;

     @Column(name = "imagenMovimiento", nullable = false, length = 60)
     private String imagenMovimiento;

}
