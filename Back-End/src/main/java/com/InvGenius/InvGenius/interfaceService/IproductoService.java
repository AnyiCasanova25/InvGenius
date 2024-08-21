package com.InvGenius.InvGenius.interfaceService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.InvGenius.InvGenius.models.producto;

public interface IproductoService {

    public String save(producto producto);

    public List<producto> findAll();

    public List<producto> productoExist(String nombreProducto, String nombreMarca, String nombreCategoria);

    public List<producto> productoACaducar(String nombreProducto, Date fechaVencimiento);

    public List<producto> productoBajoStock(String nombreProducto, String stock);

    public List<producto> productoVencido(String nombreProducto, Date fechaVencimiento);

    public Optional<producto> findOne(String id);

    // cambiar el int
    public int delete(String id);
}
