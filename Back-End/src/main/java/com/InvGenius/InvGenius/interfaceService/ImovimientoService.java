package com.InvGenius.InvGenius.interfaceService;

import java.util.List;
import java.util.Optional;

import com.InvGenius.InvGenius.models.movimientos;

public interface ImovimientoService {
    
    public String save(movimientos movimientos);

    public List<movimientos> findAll();

    public Optional<movimientos> findOne(String id);
}

