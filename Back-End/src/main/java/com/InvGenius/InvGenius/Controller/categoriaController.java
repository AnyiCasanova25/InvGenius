package com.InvGenius.InvGenius.Controller;

import java.io.IOException;
// import java.util.Arrays;
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

import com.InvGenius.InvGenius.interfaceService.IcategoriaService;
import com.InvGenius.InvGenius.models.categoria;
import com.InvGenius.InvGenius.models.response;
import com.InvGenius.InvGenius.models.respuestaImagen;

@RestController
@RequestMapping("/api/v1/categoria")
public class categoriaController {

    @Autowired
    private IcategoriaService categoriaService;

    // @PostMapping("/")
    // public ResponseEntity<Object> save(@RequestParam("file") MultipartFile file)
    // throws IOException
    // {
    // // var listaCategoria =
    // categoriaService.categoriaExist(categoria.getNombreCategoria(),categoria.getUbicacion());
    // var categoria=categoriaService.findAll().get(0);
    // var imagen=Base64.getEncoder().encodeToString(file.getBytes());
    // categoria.setImagen_base(imagen);
    // categoriaService.save(categoria);

    // // Guardar la categoría
    // return new ResponseEntity<>(categoria, HttpStatus.OK);
    // }

    String extensionesPermitidas[] = { "png", "jpg" };

    @PostMapping("/")
    public ResponseEntity<Object> save(categoria categoria, @RequestParam("file") MultipartFile file)
            throws IOException {
        var listaCategoria = categoriaService.categoriaExist(categoria.getNombreCategoria(), categoria.getUbicacion());
        // String extension = "";
        // if (file.getOriginalFilename() != null && file.getOriginalFilename().contains(".")) {
        //     extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        // } else {
        //     extension = ""; // O cualquier texto que desees si no hay archivo
        // }
        // if (!Arrays.asList(extensionesPermitidas).contains(extension)) {
        //     response respuesta = response.builder()
        //             .message("Extensión no permitida")
        //             .build();
        //     // Retornar el objeto como JSON
        //     return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        // }
        // var tamano=file.getSize();

        if (listaCategoria.size() != 0) {
            // Construir una respuesta con el mensaje y el estado
            response respuesta = response.builder()
                    .message("La categoria  ya existe")
                    .build();
            // Retornar el objeto como JSON
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        // Verifica que los campos requeridos no estén vacíos
        if (categoria.getNombreCategoria().equals("")) {
            return new ResponseEntity<>("El nombre es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (categoria.getUbicacion().equals("")) {
            return new ResponseEntity<>("La ubicación es obligatoria", HttpStatus.BAD_REQUEST);
        }

        // if (categoria.getEstado().equals("")) {
        // return new ResponseEntity<>("El estado es obligatoria",
        // HttpStatus.BAD_REQUEST);
        // }
        categoria.setImagen_base(Base64.getEncoder().encodeToString(file.getBytes()));

        // Guardar la categoría
        categoriaService.save(categoria);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    // Método para consultar categorías con manejo de imágenes (ruta cambiada)
    @GetMapping("/consultar-imagenes")
    public ResponseEntity<Object> consultarcategoriaJson() {
        List<categoria> listaCategoria = categoriaService.consultarcategoria();
        // listaCategoria.forEach(c -> c.setImagen_base("")); // Aquí puedes ajustar
        // cómo se manejan las imágenes
        return new ResponseEntity<>(listaCategoria, HttpStatus.OK);
    }

    @PostMapping("/imagen") // Aquí no debe haber una barra adicional
    public ResponseEntity<Object> guardarImagenJson(
            categoria categoria,
            @RequestParam("file") MultipartFile file) throws IOException {

        try {
            // Guardar el archivo y generar la URL
            // String fileName = gestionArchivoService.storeFile(file);
            // categoria.setImagen_url("http://localhost:8080/api/downloadFile/" +
            // fileName);
            categoria.setImagen_base(Base64.getEncoder().encodeToString(file.getBytes()));

            int resultado = categoriaService.guardarimagenJson(categoria);
            if (resultado == 0) {
                return new ResponseEntity<>(new respuestaImagen("ok", "Se guardó correctamente"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new respuestaImagen("error", "Error al guardar"),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (IOException e) {
            return new ResponseEntity<>("Error al guardar la imagen: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Filtros de categoría
    @GetMapping("/busquedaFiltros/{filtro}")
    public ResponseEntity<Object> findFiltro(@PathVariable String filtro) {
        var listaCategoria = categoriaService.categoriaExist(filtro, filtro);
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
public ResponseEntity<Object> update(@PathVariable String id, categoria categoriaUpdate,
        @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
    
    var categoria = categoriaService.findOne(id).orElse(null);

    if (categoria != null) {
        // Actualiza los campos de la categoría
        categoria.setNombreCategoria(categoriaUpdate.getNombreCategoria());
        categoria.setUbicacion(categoriaUpdate.getUbicacion());

        // Verifica si se ha enviado un nuevo archivo
        if (file != null && !file.isEmpty()) {
            categoria.setImagen_base(Base64.getEncoder().encodeToString(file.getBytes()));
        }

        // Guarda la categoría actualizada
        categoriaService.save(categoria);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Error: categoría no encontrada", HttpStatus.BAD_REQUEST);
    }
}

}