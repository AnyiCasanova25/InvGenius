package com.InvGenius.InvGenius.Controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.InvGenius.InvGenius.interfaceService.IcategoriaService;
import com.InvGenius.InvGenius.models.categoria;
import com.InvGenius.InvGenius.models.respuestaImagen;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/categoria")
public class categoriaController {

    @Autowired
    private IcategoriaService categoriaService;

    @PostMapping("/")
public ResponseEntity<Object> save(@RequestParam("categoria") categoria categoria){

    var listaCategoria = categoriaService.categoriaExist(categoria.getNombreCategoria(), null, null);

    if (listaCategoria.size() != 0) {
        return new ResponseEntity<>("La categoría ya existe", HttpStatus.BAD_REQUEST);
    }

    // Verifica que los campos requeridos no estén vacíos
    if (categoria.getNombreCategoria().equals("")) {
        return new ResponseEntity<>("El nombre es obligatorio", HttpStatus.BAD_REQUEST);
    }

    if (categoria.getEstado().equals("")) {
        return new ResponseEntity<>("El estado es obligatorio", HttpStatus.BAD_REQUEST);
    }

    if (categoria.getUbicacion().equals("")) {
        return new ResponseEntity<>("La ubicación es obligatoria", HttpStatus.BAD_REQUEST);
    }

    // Guardar la categoría
    categoriaService.save(categoria);
    return new ResponseEntity<>(categoria, HttpStatus.OK);
}

// Método para consultar categorías con manejo de imágenes (ruta cambiada)
@GetMapping("/consultar-imagenes")
public ResponseEntity<Object> consultarcategoriaJson() {
    List<categoria> listaCategoria = categoriaService.consultarcategoria();
    listaCategoria.forEach(c -> c.setImagen_base("")); // Aquí puedes ajustar cómo se manejan las imágenes
    return new ResponseEntity<>(listaCategoria, HttpStatus.OK);
}

    // Método para guardar imagen asociada a una categoría
    @PostMapping("/imagen")  // Cambio de endpoint para evitar conflicto
    public ResponseEntity<Object> guardarImagenJson(
            categoria categoria, 
            @RequestParam("file") MultipartFile file) throws IOException {

        try {
            // Guardar el archivo y generar la URL
            // String fileName = gestionArchivoService.storeFile(file);
            // categoria.setImagen_url("http://localhost:8080/api/downloadFile/" + fileName);
            categoria.setImagen_base(Base64.getEncoder().encodeToString(file.getBytes()));

            int resultado = categoriaService.guardarimagenJson(categoria);
            if (resultado == 0) {
                return new ResponseEntity<>(new respuestaImagen("ok", "Se guardó correctamente"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new respuestaImagen("error", "Error al guardar"), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (IOException e) {
            return new ResponseEntity<>("Error al guardar la imagen: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
