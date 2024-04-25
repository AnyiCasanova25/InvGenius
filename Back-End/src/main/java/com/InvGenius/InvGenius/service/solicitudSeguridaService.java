package com.InvGenius.InvGenius.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InvGenius.InvGenius.interfaceService.IsolicitudSeguridaService;
import com.InvGenius.InvGenius.interfaces.IsolicitudSegurida;
import com.InvGenius.InvGenius.models.solicitudSegurida;

@Service
public class solicitudSeguridaService implements IsolicitudSeguridaService {

    
    @Autowired
    private IsolicitudSegurida data;

    // @SuppressWarnings("null")
    @Override
    public String save(solicitudSegurida solicitudSegurida) {
        data.save(solicitudSegurida);
        return solicitudSegurida.getIdSoliSeguridad();
    }

    @Override
    public List<solicitudSegurida> findAll(){
        List<solicitudSegurida> listaSolicitud = (List<solicitudSegurida>) data.findAll();
        return listaSolicitud;
    }

    @Override
    public Optional<solicitudSegurida> findOne(String id){
        // @SuppressWarnings("null")
        Optional<solicitudSegurida> solicitudSegurida = data.findById(id);
        return solicitudSegurida;
    }
    

    @Override
    public List<solicitudSegurida> solicitudSegExist(Date fechaHora) {
            List<solicitudSegurida> listaSolicitud = data.solicitudSegExist( fechaHora);
            return listaSolicitud;
    }

    @Override
    public int delete(String id) {
        data.deleteById(id);
        return 1;
    
}
    
}
