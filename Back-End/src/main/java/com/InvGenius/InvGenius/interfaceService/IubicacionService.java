package com.InvGenius.InvGenius.interfaceService;

import java.util.List;
import java.util.Optional;

import com.InvGenius.InvGenius.models.ubicacion;

public interface IubicacionService {

    public String save(ubicacion ubicacion);
    public List<ubicacion> findAll();
    public Optional<ubicacion> findOne (String id);
    public int delete(String id);

}
