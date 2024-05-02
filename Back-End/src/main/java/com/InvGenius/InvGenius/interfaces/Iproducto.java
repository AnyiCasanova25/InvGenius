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

     //JOIN
    @Query("SELECT p FROM producto p "+
    "JOIN p.marca ma "+
    "JOIN p.categoria c "+
    "WHERE p.nombreProducto LIKE %?1% OR ma.nombreMarca LIKE %?2% OR c.nombreCategoria LIKE %?3%")
    List<producto> productoExist(String nombreProducto, String nombreMarca, String nombreCategoria);

}
