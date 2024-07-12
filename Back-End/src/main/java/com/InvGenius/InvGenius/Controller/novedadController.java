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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.interfaceService.InovedadService;
import com.InvGenius.InvGenius.models.novedad;

@RestController
@RequestMapping("/api/v1/novedad")
public class novedadController {

    @Autowired
    private InovedadService novedadService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("novedad") novedad novedad) {
        
        if (novedad.getPara().equals("")) {
            
            return new ResponseEntity<>("El destinatario, *Para:* es un campo obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (novedad.getAsunto().equals("")) {

            return new ResponseEntity<>("El asunto es un campo obligatorio",HttpStatus.BAD_REQUEST);   
        }

        if (novedad.getCuerpo().equals("")) {

            return new ResponseEntity<>("El cuerpo es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }


        novedadService.save(novedad);
        return new ResponseEntity<>(novedad,HttpStatus.OK);
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
        return new ResponseEntity<>("Registro eliminado",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id,@ModelAttribute("novedad")novedad novedadUpdate) {
        var novedad = novedadService.findOne(id).get();

        if (novedad != null) {

            novedad.setPara(novedadUpdate.getPara());
            novedad.setAsunto(novedadUpdate.getAsunto());
            novedad.setCuerpo(novedadUpdate.getCuerpo());
            novedad.setEnlaceEvidencia(novedadUpdate.getEnlaceEvidencia());
            novedad.setNumeroNovedad(novedadUpdate.getNumeroNovedad());
            novedad.setFechaNovedad(novedadUpdate.getFechaNovedad());

            novedadService.save(novedad);

            return new ResponseEntity<>(novedad,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Error novedad NO encontrada", HttpStatus.BAD_REQUEST);
        }
    }
    
}
