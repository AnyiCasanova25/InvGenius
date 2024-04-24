package com.InvGenius.InvGenius.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InvGenius.InvGenius.interfaceService.ImarcaService;
import com.InvGenius.InvGenius.interfaces.Imarca;
import com.InvGenius.InvGenius.models.marca;


@Service
public class marcaService implements ImarcaService{
    
    @Autowired
    private Imarca data;

    @SuppressWarnings("null")
    @Override
    public String save (marca marca){
        data.save(marca);
        return marca.getIdMarca();
    }

    @Override
    public List<marca> findAll(){
        List<marca> listaMarca = (List<marca>) data.findAll();
        return listaMarca;
    }

    @Override
    public Optional<marca> findOne(String id){
        @SuppressWarnings("null")
        Optional<marca> marca = data.findById(id);
        return marca;
    }

    @Override
    public List<marca> marcaExist(String  nombreMarca) {
            List<marca> listaMarca =data.marcaExist( nombreMarca);
            return listaMarca;
    }

    @Override
    public int delete(String id) {
        data.deleteById(id);
        return 1;
    }
}
