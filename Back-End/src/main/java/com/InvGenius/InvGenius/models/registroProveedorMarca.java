package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "registroProveedorMarca")
public class registroProveedorMarca {
    /*
     * id marca
     * id proveedor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idRegistro", nullable = false, length = 36)
    private String idRegistro;

    @ManyToOne
    @JoinColumn(name = "idMarca")
    private  marca marca;

    @ManyToOne
    @JoinColumn(name = "idProveedor")
    private  proveedor proveedor;

}
