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

import com.InvGenius.InvGenius.interfaceService.IunidadService;
import com.InvGenius.InvGenius.models.unidad;

import lombok.var;

@RestController
@RequestMapping("/api/v1/unidad")

public class unidadController {

    @Autowired
    private IunidadService unidadService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody unidad unidad) {

        var listaUnidad = unidadService.unidadExist(unidad.getNombreUnidad());

        if (listaUnidad.size() != 0) {
            return new ResponseEntity<>("El nombre de la unidad ya existe", HttpStatus.BAD_REQUEST);
        }

        if (unidad.getNombreUnidad().equals("")) {
            return new ResponseEntity<>("El nombre de la unidad es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (unidad.getEstadoUnidad().equals("")) {

            return new ResponseEntity<>("El estado de la unidad es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        unidadService.save(unidad);
        return new ResponseEntity<>(unidad, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        var listaUnidad = unidadService.findAll();
        return new ResponseEntity<>(listaUnidad,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var unidad = unidadService.findOne(id);
        return new ResponseEntity<>(unidad, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        unidadService.delete(id);
        return new ResponseEntity<>("Registro eliminado", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("unidad") unidad unidadUpdate) {
        var unidad = unidadService.findOne(id).get();

        if (unidad != null) {

            unidad.setNombreUnidad(unidadUpdate.getNombreUnidad());
            unidad.setEstadoUnidad(unidadUpdate.getEstadoUnidad());

            unidadService.save(unidad);

            return new ResponseEntity<>(unidad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error unidad No encontrada", HttpStatus.BAD_REQUEST);
        }
    }
}
