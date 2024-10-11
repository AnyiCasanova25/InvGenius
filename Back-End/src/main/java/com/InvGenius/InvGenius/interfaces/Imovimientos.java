package com.InvGenius.InvGenius.interfaces;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.movimientos;
 
@Repository
public interface Imovimientos extends CrudRepository<movimientos , String>{
      //JOIN
    //   @Query("SELECT mo FROM movimientos mo "+
    //   "JOIN p.marca ma "+
    //   "JOIN p.categoria c "+
    //   "WHERE p.nombreProducto LIKE %?1% OR ma.nombreMarca LIKE %?2% OR c.nombreCategoria LIKE %?3%")

    @Query("SELECT mo FROM movimientos mo WHERE mo.fechaMovimiento = ?1")
    List<movimientos> movimientosExist(Date FechaMovimiento);

    @Query("SELECT mo FROM movimientos mo " +
                      " JOIN mo.categoria c " +
                      " JOIN mo.producto p " + 
                      " JOIN mo.lote l" +
                      " JOIN mo.proveedor prov" +
                      " WHERE mo.tipoMovimiento LIKE %?1% OR p.nombreProducto LIKE %?2% OR c.nombreCategoria LIKE %?3% OR l.cantidad LIKE %?4% OR mo.fechaMovimiento = ?5 OR prov.nombreProveedor LIKE %?6%")
    List<movimientos> llamarInforme();
}
