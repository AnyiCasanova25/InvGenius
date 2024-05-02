package com.InvGenius.InvGenius.interfaceService;

import java.util.List;
import java.util.Optional;

import com.InvGenius.InvGenius.models.categoria;

public interface IcategoriaService {

    public String save(categoria categoria);
    public List<categoria> findAll();

    //para los filtros de categoria
    public List<categoria> categoriaExist(String nombreCategoria, String estado, String bloques);

    public Optional<categoria> findOne (String id);
    public int delete(String id);
    
}
