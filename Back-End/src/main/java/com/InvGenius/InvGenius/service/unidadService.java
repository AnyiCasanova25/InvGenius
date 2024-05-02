package com.InvGenius.InvGenius.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.InvGenius.InvGenius.interfaceService.IunidadService;
import com.InvGenius.InvGenius.interfaces.Iunidad;
import com.InvGenius.InvGenius.models.unidad;

public class unidadService implements IunidadService {

    @Autowired
    private Iunidad data;

    @Override
    public String save(unidad unidad) {
        data.save(unidad);
        return unidad.getIdUnidad();
    }

    @Override
    public List<unidad> findAll() {
        List<unidad> listaUnidad = (List<unidad>) data.findAll();
        return listaUnidad;
    }

    @Override
    public Optional<unidad> findOne(String id) {
        Optional<unidad> unidad = data.findById(id);
        return unidad;
    }

    @Override
    public List<unidad> unidadExist(String nombreUnidad) {
        List<unidad> listaUnidad = data.unidadExist(nombreUnidad);
        return listaUnidad;
    }

    @Override
    public int delete(String id) {
        data.deleteById(id);
        return 1;
    }
}
