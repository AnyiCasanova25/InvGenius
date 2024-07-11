package com.InvGenius.InvGenius.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.solicitudSeguridad;


@Repository
public interface IsolicitudSeguridad extends CrudRepository<solicitudSeguridad, String>{
      @Query("SELECT s FROM solicitudSeguridad s WHERE s.fechaHora = ?1")
      List<solicitudSeguridad> solicitudSeguridadExist(Date fechaHora);
}
