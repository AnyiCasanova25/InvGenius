package com.InvGenius.InvGenius.interfaces;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.InvGenius.InvGenius.models.movimientos;

public interface Imovimientos extends CrudRepository<movimientos , String>{

    @Query("SELECT Mo FROM movimientos Mo WHERE Mo.Fechamovimiento LIKE %?1%")
    List<movimientos> movimientosExist(Date Fechamovimiento);
}
