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

import com.InvGenius.InvGenius.interfaceService.IInformeService;
import com.InvGenius.InvGenius.models.informe;

@RestController
@RequestMapping("/api/v1/informe")
public class informeController {

    @Autowired
    private IInformeService informeService;
    
    @PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute ("informe") informe informe){

        if (informe.getHoraInforme().equals("")) {
            return new ResponseEntity<>("El campo de hora es obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (informe.getFechaInforme().equals("")) {
            return new ResponseEntity<>("El campo de fecha es obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (informe.getMovimientos().equals("")) {
            return new ResponseEntity<>("El campo de movimientos es obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (informe.getCategoria().equals("")){
            return new ResponseEntity<>("El campo de categoria es obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (informe.getProducto().equals("")) {
            return new ResponseEntity<>("El campo de producto es obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (informe.getMarca().equals("")) {
            return new ResponseEntity<>("El campo de marca es obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (informe.getProveedor().equals("")) {
            return new ResponseEntity<>("El campo del proveedor es obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (informe.getLote().equals("")) {
            return new ResponseEntity<>("El campo del lote es obligatorio",HttpStatus.BAD_REQUEST);
        }

        informeService.save(informe);
        return new ResponseEntity<>(informe,HttpStatus.OK);
    }

    //Filtro de informe
    @GetMapping("/busquedaFiltros/{filtro}")
    public ResponseEntity<Object>findFiltro(@PathVariable String filtro){
        var listaInforme = informeService.informeExist(filtro, filtro, null);
        return new ResponseEntity<>(listaInforme, HttpStatus.OK);
    }


     @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        var listaInforme = informeService.findAll();
        return new ResponseEntity<>(listaInforme,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var informe = informeService.findOne(id);
        return new ResponseEntity<>(informe, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        informeService.delete(id);
        return new ResponseEntity<>("Resgistro Eliminado", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("informe") informe informeUpdate){
        var informe = informeService.findOne(id).get();

        if (informe != null){

            informe.setHoraInforme(informeUpdate.getHoraInforme());
            informe.setFechaInforme(informeUpdate.getFechaInforme());
            informe.setMovimientos(informeUpdate.getMovimientos());
            informe.setCategoria(informeUpdate.getCategoria());
            informe.setProducto(informeUpdate.getProducto());
            informe.setMarca(informeUpdate.getMarca());
            informe.setProveedor(informeUpdate.getProveedor());
            informe.setLote(informeUpdate.getLote());

            informeService.save(informe);

            return new ResponseEntity<>(informe,HttpStatus.OK);

        }else {
            return new ResponseEntity<>("Error informer no encontrado", HttpStatus.BAD_REQUEST);
        }
    }
}
