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
@Entity(name = "lote")
public class lote {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idLote", nullable = false, length = 36)
    private String idLote;

    @Column(name = "fechaIngreso", nullable = false, length = 10)
    private Date fechaIngreso;

    @Column(name = "fechaVencimiento", nullable = false, length = 10)
    private Date fechaVencimiento;

    @Column(name = "cantidad", nullable = false, length = 36)
    private String cantidad;

    @Column(name = "numeroLote", nullable = false, length = 30)
    private String numeroLote;

    @Column(name = "codigoLote", nullable = false, length = 20)
    private String codigoLote;

    @Column(name = "detalleLote", nullable = false, length = 199)
    private String detalleLote;
    
    @Column(name = "precioEntrada", nullable = false, length = 199)
    private String precioEntrada;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private producto producto;

    @ManyToOne
    @JoinColumn(name = "idMovimiento")
    private movimientos movimientos;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idProveedor")
    private proveedor proveedor;
}
