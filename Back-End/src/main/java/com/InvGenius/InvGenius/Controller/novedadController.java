package com.InvGenius.InvGenius.Controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.InvGenius.InvGenius.interfaceService.InovedadService;
import com.InvGenius.InvGenius.models.novedad;
import com.InvGenius.InvGenius.models.respuestaImagen;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/novedad")
public class novedadController {
    
    @Autowired
    private InovedadService novedadService;


    String extensionesPermitidas[] = { "png", "jpg" };

    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody novedad novedad, @RequestParam("file") MultipartFile file)
            throws IOException {

        // if (novedad.getPara().equals("")) {
            
        //     return new ResponseEntity<>("El contenedor *para*, es un campo obligatorio",HttpStatus.BAD_REQUEST);
        // }

        if (novedad.getAsunto().equals("")) {
            
            return new ResponseEntity<>("El contenedor *asunto*, es un campo obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (novedad.getCuerpo().equals("")) {
            
            return new ResponseEntity<>("El contenedor *cuerpo*, es un campo obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (novedad.getEstadoNovedad().equals("")) {
            
            return new ResponseEntity<>("El contenedor *estado*, es un campo obligatorio",HttpStatus.BAD_REQUEST);
        }

         novedad.setImagen_base(Base64.getEncoder().encodeToString(file.getBytes()));

        //novedad.setFechaNovedad(new Date());
        novedadService.save(novedad);
        return new ResponseEntity<>(novedad, HttpStatus.OK);
    }

    @PostMapping("/imagen") // Aquí no debe haber una barra adicional
    public ResponseEntity<Object> guardarImagenJson(
            novedad novedad,
            @RequestParam("file") MultipartFile file) throws IOException {

        try {
            // Guardar el archivo y generar la URL
            // String fileName = gestionArchivoService.storeFile(file);
            // categoria.setImagen_url("http://localhost:8080/api/downloadFile/" +
            // fileName);
            novedad.setImagen_base(Base64.getEncoder().encodeToString(file.getBytes()));

            int resultado = novedadService.guardarimagenJson(novedad);
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

    @GetMapping("/busquedaFiltros/{filtro}")
    public ResponseEntity<Object> findFiltro(@PathVariable String filtro) {
        var listaNovedad = novedadService.novedadExist(filtro);
        return new ResponseEntity<>(listaNovedad, HttpStatus.OK);
    }

    @GetMapping("/busquedaFiltrosFecha/{filtro}")
    public ResponseEntity<Object> findFiltro(@PathVariable Date filtro) {
        var listaNovedad = novedadService.fechaDeNovedad(filtro);
        return new ResponseEntity<>(listaNovedad, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var listaNovedad = novedadService.findAll();
        return new ResponseEntity<>(listaNovedad,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var novedad = novedadService.findOne(id);
        return new ResponseEntity<>(novedad,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        novedadService.delete(id);
        return new ResponseEntity<>("Registro eliminado", HttpStatus.OK);
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Object> update(@PathVariable String id, @RequestBody novedad novedadUpdate) {
    //     var novedad = novedadService.findOne(id).get();

    //     if (novedad != null) {
            
    //         // novedad.setPara(novedadUpdate.getPara());
    //         // novedad.setAsunto(novedadUpdate.getAsunto());
    //         // novedad.setCuerpo(novedadUpdate.getCuerpo());
    //         novedad.setEstadoNovedad(novedadUpdate.getEstadoNovedad());

    //         novedadService.save(novedad);

    //         return new ResponseEntity<>(novedad, HttpStatus.OK);
    //     }else {
    //         return new ResponseEntity<>("Error novedad NO encontrada", HttpStatus.BAD_REQUEST);
    //     }
    // }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, novedad novedadUpdate,
    @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        var novedadOptional = novedadService.findOne(id);

        if (novedadOptional.isPresent()) {
            var novedad = novedadOptional.get();

            // Verifica si los campos no son nulos o vacíos antes de actualizarlos
            // if (novedadUpdate.getDe() != null && !novedadUpdate.getDe().isEmpty()) {
            //     novedad.setDe(novedadUpdate.getDe());
            // }
            // if (novedadUpdate.getPara() != null && !novedadUpdate.getPara().isEmpty()) {
            //     novedad.setPara(novedadUpdate.getPara());
            // }
            if (novedadUpdate.getAsunto() != null && !novedadUpdate.getAsunto().isEmpty()) {
                novedad.setAsunto(novedadUpdate.getAsunto());
            }
            if (novedadUpdate.getCuerpo() != null && !novedadUpdate.getCuerpo().isEmpty()) {
                novedad.setCuerpo(novedadUpdate.getCuerpo());
            }

            // Siempre se actualiza el estado, ya que es obligatorio para esta acción
            if (novedadUpdate.getEstadoNovedad() != null) {
                novedad.setEstadoNovedad(novedadUpdate.getEstadoNovedad());
            }

                    // Verifica si se ha enviado un nuevo archivo
            if (file != null && !file.isEmpty()) {
            novedad.setImagen_base(Base64.getEncoder().encodeToString(file.getBytes()));
            }

            novedadService.save(novedad);

            return new ResponseEntity<>(novedad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error: novedad NO encontrada", HttpStatus.BAD_REQUEST);
        }
    }

}
