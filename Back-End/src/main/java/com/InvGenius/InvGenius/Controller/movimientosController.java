package com.InvGenius.InvGenius.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.interfaceService.ImovimientoService;
import com.InvGenius.InvGenius.models.movimientos;
import com.InvGenius.InvGenius.models.response;

@RestController
@RequestMapping("/api/v1/movimientos")
public class movimientosController {

    @Autowired
    private ImovimientoService movimientosService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody movimientos movimientos) {

        // Verificar que no exista una fecha igual
        movimientos.setFechaMovimiento(new Date());

        var listaMovimientos = movimientosService.movimientosExist(movimientos.getFechaMovimiento());

       if (listaMovimientos.size() != 0) {
            // Construir una respuesta con el mensaje y el estado
            response respuesta = response.builder()
                    .message("la fecha ya existe")
                    .build();
            // Retornar el objeto como JSON
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }


        // Añadir campos obligatorios

        if (movimientos.getTipoMovimiento().equals("")) {

            return new ResponseEntity<>("El tipo de movimiento es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (movimientos.getFechaMovimiento().equals("")) {

            return new ResponseEntity<>("La fecha del movimiento es obligatorio ", HttpStatus.BAD_REQUEST);
        }

        if (movimientos.getCantidadProducto().equals("")) {

            return new ResponseEntity<>("La cantidad de producto es obligatorio ", HttpStatus.BAD_REQUEST);
        }
        
        if (movimientos.getDescripcionMovimiento().equals("")) {

            return new ResponseEntity<>("La descripcion de movimiento es obligatorio ", HttpStatus.BAD_REQUEST);
        }
        // todo bien
        movimientosService.save(movimientos);
        return new ResponseEntity<>(movimientos, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var listaMovimientos = movimientosService.findAll();
        return new ResponseEntity<>(listaMovimientos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var movimientos = movimientosService.findOne(id);
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Object> delete(@PathVariable String id) {
    //     movimientosService.delete(id);
    //     return new ResponseEntity<>("Movimiento eliminado", HttpStatus.OK);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Object> update(@PathVariable String id,
    //         @RequestBody movimientos movimientosUpdate) {
    //     var movimientos = movimientosService.findOne(id).get();

    //     if (movimientos != null) {

    //         movimientos.setTipoMovimiento(movimientosUpdate.getTipoMovimiento());
    //         movimientos.setCantidadProducto(movimientosUpdate.getCantidadProducto());
    //         movimientos.setFechaMovimiento(movimientosUpdate.getFechaMovimiento());
    //         movimientos.setDescripcionMovimiento(movimientosUpdate.getDescripcionMovimiento());

    //         movimientosService.save(movimientos);

    //         return new ResponseEntity<>(movimientos, HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>("Error movimiento no encontrado", HttpStatus.BAD_REQUEST);
    //     }
    // }

    // @GetMapping("/")
    // public String home() {

    // return ".......";
    // }
}
