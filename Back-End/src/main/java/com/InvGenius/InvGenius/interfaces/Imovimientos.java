package com.InvGenius.InvGenius.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.InvGenius.InvGenius.models.movimientos;

public interface Imovimientos extends CrudRepository<movimientos , String>{

    @Query("SELECT u FROM movimientos u WHERE u.Fechamovimiento = ?1 OR u.CantidadProducto = ?2")
    List<movimientos> movimientosExist(String Fechamovimiento);
}
