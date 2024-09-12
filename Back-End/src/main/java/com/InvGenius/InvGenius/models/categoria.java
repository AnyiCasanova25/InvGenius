package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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

    @Column(name = "ubicacion", nullable = false, length = 20)
    private String ubicacion;

    @Lob // Indica que se trata de un campo grande (BLOB)
    @Column(name = "imagenUrl", columnDefinition = "BLOB")
    private byte[] imagenUrl; // Aquí se almacena la imagen en formato binario

}
