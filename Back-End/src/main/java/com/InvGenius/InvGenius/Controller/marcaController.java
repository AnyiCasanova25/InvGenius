package com.InvGenius.InvGenius.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.interfaceService.ImarcaService;
import com.InvGenius.InvGenius.models.marca;

@RestController
@RequestMapping("/api/v1/marca")

public class marcaController {

    @Autowired
    private ImarcaService marcaService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody marca marca) {

        // Verificar si la marca existe
        var listaMarca = marcaService.marcaExist(marca.getNombreMarca());

        if (listaMarca.size() != 0) {
            return new ResponseEntity<>("Esta marca ya existe", HttpStatus.BAD_REQUEST);
        }

        // Añadir campos obligatorios
        if (marca.getEstado().equals("")) {

            return new ResponseEntity<>("El estado es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        // todo bien
        marcaService.save(marca);
        return new ResponseEntity<>(marca, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var listaMarca = marcaService.findAll();
        return new ResponseEntity<>(listaMarca, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var marca = marcaService.findOne(id);
        return new ResponseEntity<>(marca, HttpStatus.OK);
    }

    @GetMapping("/busquedaFiltros/{filtro}")
    public ResponseEntity<Object> findFiltro(@PathVariable String filtro) {
        var listaMarca = marcaService.marcaExist(filtro);
        return new ResponseEntity<>(listaMarca, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        var optionalMarca = marcaService.findOne(id);

        if (optionalMarca.isPresent()) {
            var marca = optionalMarca.get();

            if ("Activo".equals(marca.getEstado())) {
                marca.setEstado("Inactivo");
                marcaService.save(marca);
                return new ResponseEntity<>("Se ha desactivado correctamente", HttpStatus.OK);
            } else {
                marca.setEstado("Activo");
                marcaService.save(marca);
                return new ResponseEntity<>("Se ha activado correctamente", HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("No se ha encontrado el registro", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id,
            @RequestBody marca marcaUpdate) {
        var marca = marcaService.findOne(id).get();

        if (marca != null) {

            marca.setNombreMarca(marcaUpdate.getNombreMarca());
            marca.setEstado(marcaUpdate.getEstado());

            marcaService.save(marca);

            return new ResponseEntity<>(marca, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error marca NO encontrada", HttpStatus.BAD_REQUEST);
        }
    }
}
