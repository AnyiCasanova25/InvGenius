package com.InvGenius.InvGenius.interfaceService;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import com.InvGenius.InvGenius.models.solicitudSeguridad;

public interface IsolicitudSeguridadService {

     
    public String save(solicitudSeguridad solicitudSeguridad);

    public List<solicitudSeguridad> findAll();

    public List<solicitudSeguridad> solicitudSeguridadExist(Date fechaHora);

    public Optional<solicitudSeguridad> findOne(String id);

    public int delete(String id);

}
