package com.InvGenius.InvGenius.interfaceService;

import java.util.List;
import java.util.Optional;

import com.InvGenius.InvGenius.models.unidad;

public interface IunidadService {
    
    public String save(unidad unidad);

    public List<unidad> findAll();

    public List<unidad> unidadExist(String nombreUnidad);

    public Optional<unidad> findOne(String id);

    public int delete(String id);

}
