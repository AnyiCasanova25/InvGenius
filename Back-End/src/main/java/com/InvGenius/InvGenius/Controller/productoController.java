package com.InvGenius.InvGenius.Controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.InvGenius.InvGenius.interfaceService.IproductoService;
import com.InvGenius.InvGenius.models.producto;
import com.InvGenius.InvGenius.models.respuestaImagen;


@RestController
@RequestMapping("/api/v1/producto")
public class productoController {

    @Autowired
    private IproductoService productoService;

    @PostMapping("/")
public ResponseEntity<Object> save( producto producto,  @RequestParam("file") MultipartFile file) throws IOException
{
        if (producto.getNombreProducto().equals("")) {

            return new ResponseEntity<>("El nombre del producto es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (producto.getEstadoProducto().equals("")) {

            return new ResponseEntity<>("El estado del producto es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        producto.setImagen_base(Base64.getEncoder().encodeToString(file.getBytes()));

        //guardar el producto
        productoService.save(producto);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    // Método para consultar categorías con manejo de imágenes (ruta cambiada)
@GetMapping("/consultar-imagenes")
public ResponseEntity<Object> consultarcategoriaJson() {
    List<producto> listapProductos = productoService.consultarProducto();
    listapProductos.forEach(c -> c.setImagen_base("")); // Aquí puedes ajustar cómo se manejan las imágenes
    return new ResponseEntity<>(listapProductos, HttpStatus.OK);
}

    // Método para guardar imagen asociada a una categoría
    @PostMapping("/imagen")  // Cambio de endpoint para evitar conflicto
    public ResponseEntity<Object> guardarImagenJson(
            producto producto, 
            @RequestParam("file") MultipartFile file) throws IOException {

        try {
            // Guardar el archivo y generar la URL
            // String fileName = gestionArchivoService.storeFile(file);
            // categoria.setImagen_url("http://localhost:8080/api/downloadFile/" + fileName);
            producto.setImagen_base(Base64.getEncoder().encodeToString(file.getBytes()));

            int resultado = productoService.guardarimagenJson(producto);
            if (resultado == 0) {
                return new ResponseEntity<>(new respuestaImagen("ok", "Se guardó correctamente"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new respuestaImagen("error", "Error al guardar"), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (IOException e) {
            return new ResponseEntity<>("Error al guardar la imagen: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Editar imagen de un producto
    @PutMapping("/imagen/{id}")
    public ResponseEntity<Object> updateProductoImage(
            @PathVariable String id, 
            @RequestParam("file") MultipartFile file) {
        try {
            // Buscar la categoría por su ID
            var productoOptional = productoService.findOne(id);

            // Verificar si la categoría existe
            if (productoOptional.isPresent()) {
                var producto = productoOptional.get();

                // Convertir la imagen a base64
                String imagenBase64 = Base64.getEncoder().encodeToString(file.getBytes());

                // Actualizar la imagen de la categoría
                producto.setImagen_base(imagenBase64);

                // Guardar los cambios de la categoría
                productoService.save(producto);

                return new ResponseEntity<>(producto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error: Categoría no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Error al actualizar la imagen: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public ResponseEntity<Object> update(@PathVariable String id, @RequestParam("producto") producto productoUpdate){
        var producto= productoService.findOne(id).get();

        if (producto != null) {
            

            producto.setNombreProducto(productoUpdate.getNombreProducto());
            producto.setEstadoProducto(productoUpdate.getEstadoProducto());
            producto.setStock(productoUpdate.getStock());

            productoService.save(producto);

            return new ResponseEntity<>(producto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error producto No encontrado", HttpStatus.BAD_REQUEST);
        }
    }
}
