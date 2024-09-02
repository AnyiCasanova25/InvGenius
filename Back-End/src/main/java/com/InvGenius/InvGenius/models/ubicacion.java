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
@Entity(name = "ubicacion")
public class ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idUbicacion", nullable = false, length = 36)
    private String idUbicacion;

    @Column(name = "asignarUbicacion", nullable = false, length = 150)
    private String asignarUbicacion;

    @Column(name =  "bloques", nullable = false, length = 10)
    private String bloques;

    
}
