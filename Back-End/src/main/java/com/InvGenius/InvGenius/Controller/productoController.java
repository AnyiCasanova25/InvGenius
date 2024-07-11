package com.InvGenius.InvGenius.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.interfaceService.IproductoService;
import com.InvGenius.InvGenius.models.producto;

import lombok.var;

@RestController
@RequestMapping("/api/v1/producto")
public class productoController {

    @Autowired
    private IproductoService productoService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("producto") producto producto) {

        if (producto.getNombreProducto().equals("")) {

            return new ResponseEntity<>("El nombre del producto es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (producto.getEstadoProducto().equals("")) {

            return new ResponseEntity<>("El estado del producto es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (producto.getPrecioProducto().equals("")) {

            return new ResponseEntity<>("El precio del producto es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (producto.getPrecioCompra().equals("")) {

            return new ResponseEntity<>("El precio de la compra del producto es un campo obligatorio",
                    HttpStatus.BAD_REQUEST);
        }

        if (producto.getPrecioVenta().equals("")) {

            return new ResponseEntity<>("El precio de la venta del producto es un campo obligatorio",
                    HttpStatus.BAD_REQUEST);
        }

        productoService.save(producto);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        var listaProductos=productoService.findAll();
        return new ResponseEntity<>(listaProductos,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var producto = productoService.findOne(id);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        productoService.delete(id);
        return new ResponseEntity<>("Registro eliminado", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("producto") producto productoUpdate){
        var producto= productoService.findOne(id).get();

        if (producto != null) {
            

            producto.setNombreProducto(productoUpdate.getNombreProducto());
            producto.setEstadoProducto(productoUpdate.getEstadoProducto());
            producto.setPrecioProducto(productoUpdate.getPrecioProducto());
            producto.setStock(productoUpdate.getStock());
            producto.setPrecioCompra(productoUpdate.getPrecioCompra());
            producto.setPrecioVenta(productoUpdate.getPrecioVenta());

            productoService.save(producto);

            return new ResponseEntity<>(producto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error producto No encontrado", HttpStatus.BAD_REQUEST);
        }
    }
}
