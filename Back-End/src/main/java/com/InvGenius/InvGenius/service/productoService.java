package com.InvGenius.InvGenius.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.InvGenius.InvGenius.interfaceService.IproductoService;
import com.InvGenius.InvGenius.interfaces.Iproducto;
import com.InvGenius.InvGenius.models.producto;

public class productoService implements IproductoService {

    @Autowired
    private Iproducto data;

    @SuppressWarnings("null")
    @Override
    public String save(producto producto) {
        data.save(producto);
        return producto.getIdProducto();
    }

    @Override
    public List<producto> findAll() {
        List<producto> listaProducto = (List<producto>) data.findAll();
        return listaProducto;
    }

    @Override
    public Optional<producto> findOne(String id) {
        @SuppressWarnings("null")
        Optional<producto> producto = data.findById(id);
        return producto;
    }

    @Override
    public List<producto> productoExist(String nombreProducto, String nombreMarca, String nombreCategoria){
        List<producto> listaProducto = data.productoExist(nombreProducto, nombreMarca, nombreCategoria);
        return listaProducto;
    }

    // Corregir tipo de dato int
    @Override
    public int delete(String id) {
        data.deleteById(id);
        return 1;
    }
}
