package com.InvGenius.InvGenius.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InvGenius.InvGenius.interfaceService.IubicacionService;
import com.InvGenius.InvGenius.interfaces.Iubicacion;
import com.InvGenius.InvGenius.models.ubicacion;

@Service
public class ubicacionService implements IubicacionService {

    @Autowired
    private Iubicacion data;

    @Override
    public String save(ubicacion ubicacion) {
    data.save(ubicacion);
    return ubicacion.getIdUbicacion();
    }

    @Override
    public List<ubicacion> findAll() {
        List<ubicacion> listaUbicacion=(List<ubicacion>) data.findAll();
        return listaUbicacion;
    }

    @Override
    public Optional<ubicacion> findOne(String id) {
        Optional<ubicacion> ubicacion = data.findById(id);
        return ubicacion;
    }

    @Override
    public int delete(String id){
        data.deleteById(id);
        return 1;
    }


}
