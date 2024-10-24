package com.InvGenius.InvGenius.models;



import java.sql.Date;

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
@Entity(name = "novedad")
public class novedad {
    
    // Para
    // Asunto
    // Cuerpo
    // Evidencia
    // numNovedad
    // fechaNovedad

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idNovedad", nullable = false, length = 36)
    private String idNovedad;

    // @Column(name = "de", nullable = false, length = 100)
    // private String de;

    // @Column(name = "para", nullable = false, length = 100)
    // private String para;

    @Column(name = "asunto", nullable = false, length = 80)
    private String asunto;

    @Column(name = "cuerpo", nullable = false, length = 500)
    private String cuerpo;

    @Column(name = "fechaNovedad", nullable = false)
    private Date fechaNovedad;

    @Column(name = "imagenNovedad", nullable = false)
    private String imagenNovedad;

    @Column(name = "estadoNovedad", nullable = false, length = 30)
    private String estadoNovedad;

    @Column( name="imagen_base", nullable = true, columnDefinition = "MEDIUMBLOB")
	private String  imagen_base;

}
