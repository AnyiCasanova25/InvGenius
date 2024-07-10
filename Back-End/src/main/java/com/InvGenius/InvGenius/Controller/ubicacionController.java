package com.InvGenius.InvGenius.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.interfaceService.IubicacionService;
import com.InvGenius.InvGenius.models.ubicacion;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.var;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/v1/ubicacion")
public class ubicacionController {
    
    @Autowired
    private IubicacionService ubicacionService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("ubicacion")ubicacion ubicacion) {
        
        //Verificar que no se repita el Bloque
        var listaUbicacion = ubicacionService.ubicacionExist(ubicacion.getBloques());

        if (listaUbicacion.size() != 0){
            return new ResponseEntity<>("El bloque ya existe", HttpStatus.BAD_REQUEST);
        }
        
        // Verificar que el campo de de AsignarUbicacion y bloques sea diferente a vacio
        // Añadir campos obligatorios

        if (ubicacion.getAsignarUbicacion().equals("")){
            return new ResponseEntity<>("El campo de Asignar Ubicación es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (ubicacion.getBloques().equals("")){
            return new ResponseEntity<>("El campo Bloque es obligatorio", HttpStatus.BAD_REQUEST);
        }
        ubicacionService.save(ubicacion);
        return new ResponseEntity<>(ubicacion,HttpStatus.OK); 
    }

    //Se creo el metodo get para poder listar los datos de ubicación
    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        var listaUbicacion = ubicacionService.findAll();
        return new ResponseEntity<>(listaUbicacion,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var ubicacion = ubicacionService.findOne(id);
        return new ResponseEntity<>(ubicacion, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        ubicacionService.delete(id);
        return new ResponseEntity<>("Resgistro Eliminado", HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute ("ubicacion") ubicacion ubicacionUpdate) {
        var ubicacion = ubicacionService.findOne(id).get();

        if (ubicacion != null){

            ubicacion.setAsignarUbicacion(ubicacionUpdate.getAsignarUbicacion());
            ubicacion.setBloques(ubicacionUpdate.getBloques());

            ubicacionService.save(ubicacion);

            return new ResponseEntity<>(ubicacion, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Error ubicación no encontrada", HttpStatus.BAD_REQUEST);
        }
        
       
    }
    
    
}
