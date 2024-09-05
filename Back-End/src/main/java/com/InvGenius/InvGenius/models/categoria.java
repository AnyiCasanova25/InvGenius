package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "categoria")
public class categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idCategoria", nullable = false, length = 36)
    private String idCategoria;

    @Column(name = "nombreCategoria", nullable = false, length = 40)
    private String nombreCategoria;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "imagenUrl")
    private String imagenUrl; // Aquí se almacena la ruta o nombre del archivo de imagen

    @OneToOne
    @JoinColumn(name = "idUbicacion")
    private ubicacion ubicacion;

}
