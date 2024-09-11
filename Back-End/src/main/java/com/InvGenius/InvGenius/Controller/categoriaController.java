package com.InvGenius.InvGenius.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.nio.file.Path;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.InvGenius.InvGenius.interfaceService.IcategoriaService;
import com.InvGenius.InvGenius.models.categoria;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/categoria")
public class categoriaController {

     @Autowired
    private IcategoriaService categoriaService;

    // Define la ruta donde se guardarán las imágenes
    private static final String DIRECTORIO_IMAGENES = "ruta/a/carpeta_imagenes/";

    @PostMapping("/")
    public ResponseEntity<Object> save(
            @RequestBody categoria categoria,
            @RequestParam("imagen") MultipartFile imagen) {

        // Verifica que no se repita el nombre de la categoría
        var listaCategoria = categoriaService.categoriaExist(categoria.getNombreCategoria(), null, null);

        if (listaCategoria.size() != 0) {
            return new ResponseEntity<>("La categoría ya existe", HttpStatus.BAD_REQUEST);
        }

        // Verifica que los campos requeridos no estén vacíos
        if (categoria.getNombreCategoria().isEmpty()) {
            return new ResponseEntity<>("El campo Nombre Categoria es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (categoria.getEstado().isEmpty()) {
            return new ResponseEntity<>("El campo Estado es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (categoria.getUbicacion().isEmpty()) {
            return new ResponseEntity<>("El campo Ubicacion es obligatorio", HttpStatus.BAD_REQUEST);
        }

        // Si se envía una imagen, guárdala
        if (imagen != null && !imagen.isEmpty()) {
            try {
                String rutaImagen = guardarImagen(imagen);
                categoria.setImagenUrl(rutaImagen);
            } catch (IOException e) {
                return new ResponseEntity<>("Error al subir la imagen", HttpStatus.BAD_REQUEST);
            }
        } else {
            // Asigna una imagen predeterminada si no se proporciona ninguna
            categoria.setImagenUrl("ruta/a/imagen_predeterminada.jpg");
        }

        categoriaService.save(categoria);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    // Método para guardar la imagen en el sistema de archivos
    private String guardarImagen(MultipartFile imagen) throws IOException {
        // Generar un nombre único para la imagen
        String nombreImagen = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();

        // Crear la ruta donde se guardará la imagen
        Path rutaImagen = Paths.get(DIRECTORIO_IMAGENES + nombreImagen);

        // Asegúrate de que el directorio de imágenes exista
        if (!Files.exists(Paths.get(DIRECTORIO_IMAGENES))) {
            Files.createDirectories(Paths.get(DIRECTORIO_IMAGENES));
        }

        // Guardar el archivo de imagen en el servidor
        Files.copy(imagen.getInputStream(), rutaImagen);

        // Retornar el nombre o la ruta de la imagen guardada
        return nombreImagen;
    }
    //Filtros de categoria
    @GetMapping("/busquedaFiltros/{filtro}")
    public ResponseEntity<Object>findFiltro(@PathVariable String filtro){
        var listaCategoria = categoriaService.categoriaExist(filtro, filtro, filtro);
        return new ResponseEntity<>(listaCategoria, HttpStatus.OK);
    }

    
    //Se creo el metodo get para poder listar los datos de categoria
    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        var listaCategoria = categoriaService.findAll();
        return new ResponseEntity<>(listaCategoria,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var categoria = categoriaService.findOne(id);
        return new ResponseEntity<>(categoria, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        categoriaService.delete(id);
        return new ResponseEntity<>("Resgistro Eliminado", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id,
            @RequestBody categoria categoriaUpdate) {
        var categoria = categoriaService.findOne(id).get();

        if (categoria != null) {

            categoria.setNombreCategoria(categoriaUpdate.getNombreCategoria());
            categoria.setEstado(categoriaUpdate.getEstado());
            categoria.setUbicacion(categoriaUpdate.getUbicacion());

            categoriaService.save(categoria);

            return new ResponseEntity<>(categoria, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Error categoria no encontrada", HttpStatus.BAD_REQUEST);
        }

    }
}
