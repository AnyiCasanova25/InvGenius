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
@Entity(name = "marca")
public class marca {
    
    /*id
     * nombre marca
     * estado
     */

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(name = "idMarca" , nullable = false, length = 36)
     private String idMarca;

     @Column(name = "nombreMarca" , nullable = false, length = 36)
     private String nombreMarca;

     @Column(name = "estado" , nullable = false, length = 36)
     private String estado;

}
