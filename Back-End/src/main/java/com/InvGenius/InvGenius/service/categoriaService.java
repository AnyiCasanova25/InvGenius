package com.InvGenius.InvGenius.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InvGenius.InvGenius.interfaceService.IcategoriaService;
import com.InvGenius.InvGenius.interfaces.Icategoria;
import com.InvGenius.InvGenius.models.categoria;

@Service
public class categoriaService implements IcategoriaService {

    @Autowired
    private Icategoria data;

    @Override
    public String save(categoria categoria) {
        data.save(categoria);
        return categoria.getIdCategoria();
    }

    @Override
    public List<categoria> findAll() {
        List<categoria> listaCategoria=(List<categoria>) data.findAll();
        return listaCategoria;
    }

    @Override
    public Optional<categoria> findOne(String id) {
        Optional<categoria> categoria = data.findById(id);
        return categoria;
    }

    //Filtros de categoria
    @Override
    public List<categoria> categoriaExist(String nombreCategoria, String estado) {
            List<categoria> listaCategoria = data.categoriaExist( nombreCategoria, estado);
            return listaCategoria;
    }
    
    @Override
    public int delete(String id) {
        data.deleteById(id);
        return 1;
    }
    
}
