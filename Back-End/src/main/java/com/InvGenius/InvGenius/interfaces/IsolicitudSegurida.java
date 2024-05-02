package com.InvGenius.InvGenius.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.solicitudSegurida;

@Repository
public interface IsolicitudSegurida extends CrudRepository<solicitudSegurida, String> {

    @Query("SELECT s FROM solicitudSegurida s WHERE s.fechaHora = ?1")
    List<solicitudSegurida> solicitudSegExist(Date fechaHora);
    
}
