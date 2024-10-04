package com.InvGenius.InvGenius.interfaces;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.lote;

@Repository
public interface Ilote extends CrudRepository<lote, String> {

        // Query personalizado para buscar por el codigo del lote
        @Query("SELECT l FROM lote l WHERE l.codigoLote = ?1")
        List<lote> loteExist(String codigoLote);

        // // Query para notificar los lotes de productos proximo a vencer
        // @Query("SELECT l FROM lote l JOIN l.producto p WHERE l.fechaVencimiento <= CURRENT_DATE + 30")
        @Query("SELECT l FROM lote l " +
                        " JOIN l.producto p " +
                        //" ON l.idProducto = p.idProducto " +
                        " WHERE DATEDIFF(l.fechaVencimiento, CURRENT_DATE) <= 30") // BETWEEN CURDATE() AND CURDATE() + INTERVAL 30 DAY
        List<lote> loteACaducar();


        // Query para avisar que hay productos en bajo stock
        @Query("SELECT l FROM lote l " +
                        " JOIN producto p " +
                        //" ON l.idProducto = p.idProducto " +
                        " WHERE p.stock < 60")
        List<lote> loteBajoStock();


        // // Query para lotes ya caducados
        @Query("SELECT l FROM lote l " +
                        " JOIN producto p " +
                        //" ON l.idProducto = p.idProducto " +
                        " WHERE l.fechaVencimiento < CURRENT_DATE")
        List<lote> loteVencido();

}
