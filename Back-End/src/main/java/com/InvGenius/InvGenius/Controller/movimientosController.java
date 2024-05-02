package com.InvGenius.InvGenius.Controller;


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

import com.InvGenius.InvGenius.interfaceService.ImovimientoService;
import com.InvGenius.InvGenius.models.movimientos;

@RestController
@RequestMapping("/api/v1/movimientos")
public class movimientosController {
    
    //@Autowired
    private ImovimientoService movimientosService;

   @PostMapping("/")
   public ResponseEntity<Object> save (@ModelAttribute("movimientos") movimientos movimientos){

       //Verificar que no exista una fecha igual 
    var listaMovimientos = movimientosService.movimientosExist(movimientos.getFechaMovimiento());

   if (listaMovimientos.size() !=0) {
           return new ResponseEntity<>("La fecha ya existe", HttpStatus.BAD_REQUEST);
       }


       
       //Añadir campos obligatorios

       if (movimientos.getTipomovimiento().equals("")) {
           
           return new ResponseEntity<>("El tipo de movimiento es obligatorio", HttpStatus.BAD_REQUEST);
       }

       if (movimientos.getCantidadProducto().equals("")) {
           
           return new ResponseEntity<>("La cantidad de producto es obligatorio", HttpStatus.BAD_REQUEST);
       }

       if (movimientos.getFechaMovimiento().equals("")) {
           
           return new ResponseEntity<>("La fecha del movimiento es obligatorio ", HttpStatus.BAD_REQUEST);
       }


       //todo bien
       movimientosService.save(movimientos);
       return new ResponseEntity<>(movimientos, HttpStatus.OK);

   }

   @GetMapping("/{id}")
   public ResponseEntity <Object> findOne(@PathVariable String id) {
       var movimientos = movimientosService.findOne(id);
       return new ResponseEntity<>(movimientos, HttpStatus.OK);
   }
   
   @GetMapping("/")
   public String home() {

       return "Este es un end point de movimiento(privado)";
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Object> delete(@PathVariable String id) {
       movimientosService.delete(id);
       return new ResponseEntity<>("Movimiento eliminado", HttpStatus.OK);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("movimientos") movimientos movimientosUpdate) {
       var movimientos = movimientosService.findOne(id).get();

       if (movimientos != null) {
           
        movimientos.setTipomovimiento(movimientosUpdate.getTipomovimiento());
        movimientos.setCantidadProducto(movimientosUpdate.getCantidadProducto());
        movimientos.setFechaMovimiento(movimientosUpdate.getFechaMovimiento());
           

           movimientosService.save(movimientos);

           return new ResponseEntity<>(movimientos, HttpStatus.OK);
       }else{
           return new ResponseEntity<>("Error movimiento no encontrado", HttpStatus.BAD_REQUEST);
       }
   }

     
    // @GetMapping("/")
    // public String home() {

    //     return ".......";
    // }
    
}
