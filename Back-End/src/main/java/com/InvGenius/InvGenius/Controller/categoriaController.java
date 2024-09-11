package com.InvGenius.InvGenius.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.interfaceService.IcategoriaService;
import com.InvGenius.InvGenius.models.categoria;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/categoria")
public class categoriaController {

     @Autowired
    private IcategoriaService categoriaService;
    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody categoria categoria) {

        var listaCategoria = categoriaService.categoriaExist(categoria.getNombreCategoria(), null, null);

        if (listaCategoria.size() != 0) {
            return new ResponseEntity<>("La categoría ya existe", HttpStatus.BAD_REQUEST);
        }

        // Verifica que los campos requeridos no estén vacíos
        if (categoria.getNombreCategoria().equals("")) {

            return new ResponseEntity<>("El nombre es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (categoria.getEstado().equals("")) {

            return new ResponseEntity<>("El estado es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (categoria.getUbicacion().equals("")) {

            return new ResponseEntity<>("La ubicacion es obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        // todo bien
        categoriaService.save(categoria);
        return new ResponseEntity<>(categoria, HttpStatus.OK);

    }
    //Filtros de categoria
    @GetMapping("/busquedaFiltros/{filtro}")
    public ResponseEntity<Object>findFiltro(@PathVariable String filtro){
        var listaCategoria = categoriaService.categoriaExist(filtro, filtro, filtro);
        return new ResponseEntity<>(listaCategoria, HttpStatus.OK);
    }

    
    //Se creo el metodo get para poder listar los datos de categoria
    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        var listaCategoria = categoriaService.findAll();
        return new ResponseEntity<>(listaCategoria,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var categoria = categoriaService.findOne(id);
        return new ResponseEntity<>(categoria, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        categoriaService.delete(id);
        return new ResponseEntity<>("Resgistro Eliminado", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id,
            @RequestBody categoria categoriaUpdate) {
        var categoria = categoriaService.findOne(id).get();

        if (categoria != null) {

            categoria.setNombreCategoria(categoriaUpdate.getNombreCategoria());
            categoria.setEstado(categoriaUpdate.getEstado());
            categoria.setUbicacion(categoriaUpdate.getUbicacion());

            categoriaService.save(categoria);

            return new ResponseEntity<>(categoria, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Error categoria no encontrada", HttpStatus.BAD_REQUEST);
        }

    }
}
