package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "unidad")
public class unidad {
    
    /*id
     * nombre unidad
     * estado unidad
     */

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(name = "idUnidad", nullable = false, length = 36)
     private String idUnidad;

     @Column(name = "nombreUnidad", nullable = false, length = 36)
     private String nombreUnidad;

     @Column(name = "estadoUnidad", nullable = false, length = 20)
     private String estadoUnidad;

     @Column(name = "imagenUnidad", nullable = false)
     private String imagenUnidad;

}
