package com.InvGenius.InvGenius.interfaceService;

import java.util.List;
import java.util.Optional;


import com.InvGenius.InvGenius.models.categoria;

public interface IcategoriaService {

    public String save(categoria categoria);
    public List<categoria> findAll();
    public Optional<categoria> findOne (String id);
    public int delete(String id);
    
}
