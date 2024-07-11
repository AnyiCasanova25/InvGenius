package com.InvGenius.InvGenius.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InvGenius.InvGenius.interfaceService.IsolicitudSeguridadService;
import com.InvGenius.InvGenius.interfaces.IsolicitudSeguridad;
import com.InvGenius.InvGenius.models.solicitudSeguridad;


@Service
public class solicitudSeguridadService implements IsolicitudSeguridadService{
    @Autowired
    private IsolicitudSeguridad data;

    
    @Override
    public String save(solicitudSeguridad solicitudSeguridad) {
        data.save(solicitudSeguridad);
        return solicitudSeguridad.getIdSolicitud();
    }

    @Override
    public List<solicitudSeguridad> findAll(){
        List<solicitudSeguridad> listaSolicitudSeguridad = (List<solicitudSeguridad>) data.findAll();
        return listaSolicitudSeguridad;
    }

    @Override
    public Optional<solicitudSeguridad> findOne(String id){
        
        Optional<solicitudSeguridad> solicitudSeguridad = data.findById(id);
        return solicitudSeguridad;
    }
    

    @Override
    public List<solicitudSeguridad> solicitudSeguridadExist(Date fechaHora) {
            List<solicitudSeguridad> listaSolicitudSeguridad = data.solicitudSeguridadExist( fechaHora);
            return listaSolicitudSeguridad;
    }

    @Override
    public int delete(String id) {
        data.deleteById(id);
        return 1;
    
}

}
