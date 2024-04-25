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

import com.InvGenius.InvGenius.interfaceService.IsolicitudSeguridaService;
import com.InvGenius.InvGenius.models.solicitudSegurida;

@RestController
@RequestMapping("/api/v1/solicitudSegurida")
public class solicitudSegController {

    @Autowired
    private IsolicitudSeguridaService solicitudSeguridaService;

   @PostMapping("/")
   public ResponseEntity<Object> save (@ModelAttribute("solicitudSegurida") solicitudSegurida solicitudSegurida){

       //Verificar que no exista una fecha igual 
    var listaSolicitud = solicitudSeguridaService.solicitudSegExist(solicitudSegurida.getFechaHora());

   if (listaSolicitud.size() !=0) {
           return new ResponseEntity<>("La fecha ya existe", HttpStatus.BAD_REQUEST);
       }
 
       //Añadir campos obligatorios

       if (solicitudSegurida.getEstadoSoliSeguridad().equals("")) {
           
           return new ResponseEntity<>("El estado es obligatorio", HttpStatus.BAD_REQUEST);
       }

       if (solicitudSegurida.getCodigoSeguridad().equals("")) {
           
           return new ResponseEntity<>("El codigo es obligatorio", HttpStatus.BAD_REQUEST);
       }


       //todo bien
       solicitudSeguridaService.save(solicitudSegurida);
       return new ResponseEntity<>(solicitudSegurida, HttpStatus.OK);

   }

   @GetMapping("/{id}")
   public ResponseEntity <Object> findOne(@PathVariable String id) {
       var solicitudSegurida = solicitudSeguridaService.findOne(id);
       return new ResponseEntity<>(solicitudSegurida, HttpStatus.OK);
   }
   
   @GetMapping("/")
   public String home() {

       return "Este es un end point de solicitud(privado)";
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Object> delete(@PathVariable String id) {
       solicitudSeguridaService.delete(id);
       return new ResponseEntity<>("solicitud eliminada", HttpStatus.OK);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("solicitudSegurida") solicitudSegurida solicitudSeguridaUpdate) {
       var solicitudSegurida = solicitudSeguridaService.findOne(id).get();

       if (solicitudSegurida != null) {
           
        solicitudSegurida.setEstadoSoliSeguridad(solicitudSeguridaUpdate.getEstadoSoliSeguridad());
        solicitudSegurida.setCodigoSeguridad(solicitudSeguridaUpdate.getCodigoSeguridad());
           

           solicitudSeguridaService.save(solicitudSegurida);

           return new ResponseEntity<>(solicitudSegurida, HttpStatus.OK);
       }else{
           return new ResponseEntity<>("Error solicitud no encontrada", HttpStatus.BAD_REQUEST);
       }
   }
    
}
