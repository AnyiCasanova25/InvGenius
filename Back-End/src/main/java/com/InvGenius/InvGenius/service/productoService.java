package com.InvGenius.InvGenius.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InvGenius.InvGenius.interfaceService.IproductoService;
import com.InvGenius.InvGenius.interfaces.Iproducto;
import com.InvGenius.InvGenius.models.producto;

@Service
public class productoService implements IproductoService {

    @Autowired
    private Iproducto data;

    @Override
	public List<producto> consultarProducto() {

		return (List<producto>) data.findAll();
	}


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
        Optional<producto> producto = data.findById(id);
        return producto;
    }

    @Override
    public List<producto> productoExist(String nombreProducto, String nombreMarca, String nombreCategoria){
        List<producto> listaProducto = data.productoExist(nombreProducto, nombreMarca, nombreCategoria);
        return listaProducto;
    }

    // @Override
    // public List<producto> productoACaducar(String nombreProducto,Date fechaVencimiento){
    //     List<producto> listaProducto = data.productoACaducar(nombreProducto, fechaVencimiento);
    //     return listaProducto;
    // }

    // @Override
    // public List<producto> productoBajoStock(String nombreProducto,  String stock){
    //     List<producto> listaProducto = data.productoBajoStock(nombreProducto, stock);
    //     return listaProducto;
    // }

    // @Override
    // public List<producto> productoVencido(String nombreProducto,Date fechaVencimiento){
    //     List<producto> listaProducto = data.productoVencido(nombreProducto, fechaVencimiento);
    //     return listaProducto;
    // }

    // Corregir tipo de dato int
    @Override
    public int delete(String id) {
        data.deleteById(id);
        return 1;
    }
//funcion de guardar imagen 
    @Override
	public int guardarimagenJson(producto producto) {
		int res=0;
		producto =data.save(producto);
		if(producto.equals(null)) {
			res=1;
		}
		return res;
	}
}
