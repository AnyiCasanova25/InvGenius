package com.InvGenius.InvGenius.models;


import java.time.LocalDate;
import java.time.LocalTime;

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
@Entity(name = "informe")
public class informe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idInforme", nullable = false, length = 36)
    private String idInforme;

    @Column(name = "horaInforme", nullable = false, length = 20)
    private LocalTime horaInforme;

    @Column(name = "fechaInforme", nullable = false, length = 20)
    private LocalDate fechaInforme;

    @ManyToOne
    @JoinColumn(name = "idMovimiento")
    private movimientos movimientos;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private producto producto;

    @ManyToOne
    @JoinColumn(name = "idMarca")
    private marca marca;
    
    @ManyToOne
    @JoinColumn(name = "idProveedor")
    private proveedor proveedor;
    
    @ManyToOne
    @JoinColumn(name = "idLote")
    private lote lote;

}
