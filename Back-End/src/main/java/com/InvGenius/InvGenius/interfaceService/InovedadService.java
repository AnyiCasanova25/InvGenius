package com.InvGenius.InvGenius.interfaceService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.InvGenius.InvGenius.models.novedad;

public interface InovedadService {
    
    public String save(novedad novedad);

    public List<novedad> findAll();

    public List<novedad> asuntoNovedad(String asunto);

    public List<novedad> numNovedad(int numeroNovedad);

    public List<novedad> fechaNovedad(Date fechaNovedad);

    public Optional<novedad> findOne(String id);

    public int delete(String id);
    
}
