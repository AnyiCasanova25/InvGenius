package com.InvGenius.InvGenius.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.producto;

@Repository
public interface Iproducto extends CrudRepository<producto, String> {

    /*
     * id
     * nombre producto
     * id marca
     * precio
     * id categoria
     * stock
     * precio compra
     * precio venta
     * id unidad
     */

    // JOIN
    @Query("SELECT p FROM producto p " +
            "JOIN p.marca ma " +
            "JOIN p.categoria c " +
            "WHERE p.nombreProducto LIKE %?1% OR ma.nombreMarca LIKE %?2% OR c.nombreCategoria LIKE %?3%")
    List<producto> productoExist(String nombreProducto, String nombreMarca, String nombreCategoria);

//     // QUERY DE NOTIFICACION DE PRODUCTO A VENCER
//     @Query("SELECT p FROM producto p " +
//             "JOIN p.lote l " +
//             "WHERE p.nombreProducto LIKE %?1% OR l.fechaVencimiento LIKE ?2 BETWEEN CURDATE() AND CURDATE() + INTERVAL 30 DAY")
//     List<producto> productoACaducar(String nombreProducto, Date fechaVencimiento);

//     // QUERY DE NOTIFICACION PARA AVISAR QUE HAY PRODUCTOS EN BAJO STOCK

//     @Query("SELECT p FROM producto p " +
//             "JOIN p.lote l " +
//             "WHERE p.nombreProducto LIKE %?1% OR p.stock < 60")
//     List<producto> productoBajoStock(String nombreProducto, String stock);

//     // QUERY DE PRODUCTOS YA CADUCADOS
//     @Query("SELECT p FROM producto p " +
//             "JOIN p.lote l " +
//             "WHERE p.nombreProducto LIKE %?1% OR l.fechaVencimiento LIKE ?2 < CURDATE()")
//     List<producto> productoVencido(String nombreProducto, Date fechaVencimiento);

}
