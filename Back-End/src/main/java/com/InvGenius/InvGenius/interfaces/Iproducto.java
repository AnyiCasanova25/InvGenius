package com.InvGenius.InvGenius.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.InvGenius.InvGenius.models.producto;

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

    @Query("SELECT p FROM producto p WHERE p.nombreProducto LIKE %?1% OR ma.nombreMarca LIKE %?1% OR c.nombreCategoria")
    List<producto> productoExist(String nombreProducto, String nombreMarca, String nombreCategoria);

}
