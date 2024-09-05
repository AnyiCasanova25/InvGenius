package com.InvGenius.InvGenius.interfaces;

import java.util.Date;
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

        // Query para notificar el producto proximo a vencer
        @Query("SELECT l FROM lote l " +
                        "JOIN l.producto p " +
                        "WHERE p.nombreProducto LIKE %?1% OR DATEDIFF(NOW(), l.fechaVencimiento) <= 30") //BETWEEN CURDATE() AND CURDATE() + INTERVAL 30 DAY
        List<lote> loteACaducar(String nombreProdcuto);

        // Query para avisar que hay productos en bajo stock
        @Query("SELECT l FROM lote l " +
                        "JOIN l.producto p " +
                        "WHERE p.nombreProducto LIKE %?1% OR p.stock < 60")
        List<lote> loteBajoStock(String nombreProducto, String stock);

        // Query para lotes ya caducados
        @Query("SELECT l FROM lote l " +
                        "JOIN l.producto p " +
                        "WHERE p.nombreProducto LIKE %?1% OR l.fechaVencimiento < CURRENT_DATE")
        List<lote> loteVencido(String nombreProducto);

}
