package com.InvGenius.InvGenius.interfaces;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.InvGenius.InvGenius.models.solicitudSegurida;

public interface IsolicitudSegurida extends CrudRepository<solicitudSegurida, String> {

    @Query("SELECT s FROM solicitudSegurida s WHERE s.fechaHora = ?1")
    List<solicitudSegurida> solicitudSegExist(Date fechaHora);
    
}
