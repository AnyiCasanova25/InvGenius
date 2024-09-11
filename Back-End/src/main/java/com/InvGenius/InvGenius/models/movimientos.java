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
     private String tipoMovimiento;

     @Column(name = "cantidadProducto", nullable = false, length = 15)
     private String cantidadProducto;

     @Column(name = "descripcionMovimiento", nullable = false, length = 60)
     private String descripcionMovimiento;

     @ManyToOne
     @JoinColumn(name = "idUser")
     private user user;

     @Column(name = "fechaMovimiento", nullable = false, length = 36)
     private Date fechaMovimiento;

     @Column(name = "imagenMovimiento")
     private String imagenMovimiento;

}
