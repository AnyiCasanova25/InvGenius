package com.InvGenius.InvGenius.interfaceService;

import java.util.List;
import java.util.Optional;

import com.InvGenius.InvGenius.models.marca;

public interface ImarcaService {

    public String save(marca marca);

    public List<marca> findAll();

    public List<marca> marcaExist(String nombreMarca);

    public Optional<marca> findOne(String id);

    public int delete(String id);

}
