package com.InvGenius.InvGenius.interfaceService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.InvGenius.InvGenius.models.solicitudSegurida;

public interface IsolicitudSeguridaService {

    public String save(solicitudSegurida solicitudSegurida);

    public List<solicitudSegurida> findAll();

    public List<solicitudSegurida> solicitudSegExist(Date fechaHora);

    public Optional<solicitudSegurida> findOne(String id);

    public int delete(String id);

    
}
