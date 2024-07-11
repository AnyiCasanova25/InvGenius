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


import com.InvGenius.InvGenius.interfaceService.IsolicitudSeguridadService;
import com.InvGenius.InvGenius.models.solicitudSeguridad;
import lombok.var;

@RestController
@RequestMapping("/api/v1/solicitudSeguridad")
public class solicitudSeguridadController {
     
    
    @Autowired
    private IsolicitudSeguridadService solicitudSeguridadService;

   @PostMapping("/")
   public ResponseEntity<Object> save (@RequestBody solicitudSeguridad solicitudSeguridad){

       //Verificar que no exista una fecha igual 
    var listasolicitudSeguridad = solicitudSeguridadService.solicitudSeguridadExist(solicitudSeguridad.getFechaHora());

   if (listasolicitudSeguridad.size() !=0) {
           return new ResponseEntity<>("La fecha y hora ya existe", HttpStatus.BAD_REQUEST);
       }


       
       //Añadir campos obligatorios

       if (solicitudSeguridad.getEstadoSolicitud().equals("")) {
           
           return new ResponseEntity<>("El estado es obligatorio", HttpStatus.BAD_REQUEST);
       }

       if (solicitudSeguridad.getCodigoSeguridad().equals("")) {
           
           return new ResponseEntity<>("El codigo es obligatorio", HttpStatus.BAD_REQUEST);
       }


       //todo bien
       solicitudSeguridadService.save(solicitudSeguridad);
       return new ResponseEntity<>(solicitudSeguridad, HttpStatus.OK);

   }

   @GetMapping("/")
   public ResponseEntity<Object> findAll(){
       var listasolicitudSeguridad =solicitudSeguridadService.findAll();
       return new ResponseEntity<>(listasolicitudSeguridad, HttpStatus.OK);
   }
   @GetMapping("/{id}")
   public ResponseEntity <Object> findOne(@PathVariable String id) {
       var solicitudSeguridad = solicitudSeguridadService.findOne(id);
       return new ResponseEntity<>(solicitudSeguridad, HttpStatus.OK);
   }
   @DeleteMapping("/{id}")
   public ResponseEntity<Object> delete(@PathVariable String id) {
       solicitudSeguridadService.delete(id);
       return new ResponseEntity<>("solicitud de seguridad eliminada", HttpStatus.OK);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Object> update(@PathVariable String id, @RequestBody solicitudSeguridad solicitudSeguridadUpdate) {
       var solicitudSeguridad = solicitudSeguridadService.findOne(id).get();

       if (solicitudSeguridad != null) {
           
        solicitudSeguridad.setEstadoSolicitud(solicitudSeguridadUpdate.getEstadoSolicitud());
        solicitudSeguridad.setCodigoSeguridad(solicitudSeguridadUpdate.getCodigoSeguridad());
        solicitudSeguridad.setFechaHora(solicitudSeguridadUpdate.getFechaHora());
           

          solicitudSeguridadService.save(solicitudSeguridad);

           return new ResponseEntity<>(solicitudSeguridad, HttpStatus.OK);
       }else{
           return new ResponseEntity<>("Error solicitud de seguridad no encontrado", HttpStatus.BAD_REQUEST);
       }
   }

}
