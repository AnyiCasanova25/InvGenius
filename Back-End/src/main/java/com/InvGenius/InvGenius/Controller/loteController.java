package com.InvGenius.InvGenius.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.interfaceService.IloteService;
import com.InvGenius.InvGenius.models.lote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/v1/lote")
public class loteController {

    @Autowired
    private IloteService loteService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("lote") lote lote) {

        var listaLote = loteService.loteExist(lote.getCodigoLote());

        if (listaLote.size() != 0) {
            return new ResponseEntity<>("Este lote ya existe", HttpStatus.BAD_REQUEST);
            
        }
        
        if (lote.getFechaIngreso().equals("")){
            return new ResponseEntity<>("Este campo es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (lote.getFechaVencimiento().equals("")){
            return new ResponseEntity<>("Este campo es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (lote.getCantidad().equals("")){
            return new ResponseEntity<>("Este campo es obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (lote.getNumeroLote().equals("")){
            return new ResponseEntity<>("El campo es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (lote.getCodigoLote().equals("")){
            return new ResponseEntity<>("El campo es obligatorio", HttpStatus.BAD_REQUEST);
        }

        loteService.save(lote);
        return new ResponseEntity<>(lote,HttpStatus.OK);
        
    }

     //Se creo el metodo get para poder listar los datos de lote
     @GetMapping("/")
     public ResponseEntity<Object> findAll(){
        var listaLote = loteService.findAll();
        return new ResponseEntity<>(listaLote,HttpStatus.OK);
     }
     
    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var lote = loteService.findOne(id);
        return new ResponseEntity<>(lote, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        loteService.delete(id);
        return new ResponseEntity<>("Registro Eliminado", HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("lote") lote loteUpdate) {
        var lote = loteService.findOne(id).get();

        if (lote != null){

            lote.setFechaIngreso(loteUpdate.getFechaIngreso());
            lote.setFechaVencimiento(loteUpdate.getFechaVencimiento());
            lote.setCantidad(loteUpdate.getCantidad());
            lote.setNumeroLote(loteUpdate.getNumeroLote());
            lote.setCodigoLote(loteUpdate.getCodigoLote());

            loteService.save(lote);

            return new ResponseEntity<>(lote, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Error lote no encontrado", HttpStatus.BAD_REQUEST);
        }
    }
    
}
