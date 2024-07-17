package com.InvGenius.InvGenius.interfaces;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.informe;

@Repository
public interface IInforme extends CrudRepository<informe, String> {

    //Query para los filtros de informe
    @Query("SELECT info FROM informe info "
            +"JOIN info.movimientos mo "
            +"JOIN info.categoria c "
            +"WHERE mo.tipomovimiento LIKE %?1% "
            +"OR c.nombreCategoria LIKE %?2% "
            +"OR info.fechaInforme BETWEEN ?3 AND ?4")
    List<informe> informeExist(String tipomovimiento, String nombreCategoria, LocalDate fechaInforme);
    
}
