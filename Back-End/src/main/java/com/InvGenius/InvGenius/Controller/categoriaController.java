package com.InvGenius.InvGenius.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.interfaceService.IcategoriaService;
import com.InvGenius.InvGenius.models.categoria;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/categoria")
public class categoriaController {

    @Autowired
    private IcategoriaService categoriaService;

    @PostMapping("/")
public ResponseEntity<Object> save(@RequestParam("nombreCategoria") String nombreCategoria,
                                   @RequestParam("estado") String estado,
                                   @RequestParam("ubicacion") String ubicacion,
                                   @RequestParam(value = "imagenUrl", required = false) MultipartFile imagenUrl) {

    var listaCategoria = categoriaService.categoriaExist(nombreCategoria, null, null);

    if (listaCategoria.size() != 0) {
        return new ResponseEntity<>("La categoría ya existe", HttpStatus.BAD_REQUEST);
    }

    // Verifica que los campos requeridos no estén vacíos
    if (nombreCategoria.equals("")) {
        return new ResponseEntity<>("El nombre es obligatorio", HttpStatus.BAD_REQUEST);
    }

    if (estado.equals("")) {
        return new ResponseEntity<>("El estado es obligatorio", HttpStatus.BAD_REQUEST);
    }

    if (ubicacion.equals("")) {
        return new ResponseEntity<>("La ubicación es obligatoria", HttpStatus.BAD_REQUEST);
    }

    categoria nuevaCategoria = new categoria();
    nuevaCategoria.setNombreCategoria(nombreCategoria);
    nuevaCategoria.setEstado(estado);
    nuevaCategoria.setUbicacion(ubicacion);

    if (imagenUrl != null && !imagenUrl.isEmpty()) {
        try {
            // Convertir la imagen a un array de bytes
            byte[] imagenBytes = imagenUrl.getBytes();
            
            // Asegúrate de que el tamaño no exceda el límite de 255 caracteres
            if (imagenBytes.length > 255) {
                // Puedes elegir truncar la imagen a un tamaño específico si es necesario.
                imagenBytes = java.util.Arrays.copyOf(imagenBytes, 255);
            }

            nuevaCategoria.setImagenUrl(imagenBytes);
        } catch (IOException e) {
            return new ResponseEntity<>("Error al procesar la imagen", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Guardar la categoría
    categoriaService.save(nuevaCategoria);
    return new ResponseEntity<>(nuevaCategoria, HttpStatus.OK);
}


    // Filtros de categoría
    @GetMapping("/busquedaFiltros/{filtro}")
    public ResponseEntity<Object> findFiltro(@PathVariable String filtro) {
        var listaCategoria = categoriaService.categoriaExist(filtro, filtro, filtro);
        return new ResponseEntity<>(listaCategoria, HttpStatus.OK);
    }

    // Listar todas las categorías
    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var listaCategoria = categoriaService.findAll();
        return new ResponseEntity<>(listaCategoria, HttpStatus.OK);
    }

    // Buscar una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var categoria = categoriaService.findOne(id);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    // Eliminar una categoría por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        categoriaService.delete(id);
        return new ResponseEntity<>("Registro eliminado", HttpStatus.OK);
    }

    // Actualizar una categoría
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestParam("categoria") categoria categoriaUpdate) {
        var categoria = categoriaService.findOne(id).get();

        if (categoria != null) {
            categoria.setNombreCategoria(categoriaUpdate.getNombreCategoria());
            categoria.setEstado(categoriaUpdate.getEstado());
            categoria.setUbicacion(categoriaUpdate.getUbicacion());

            categoriaService.save(categoria);
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error: categoría no encontrada", HttpStatus.BAD_REQUEST);
        }
    }
}
