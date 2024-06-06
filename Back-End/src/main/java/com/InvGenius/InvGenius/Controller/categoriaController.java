package com.InvGenius.InvGenius.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.interfaceService.IcategoriaService;
import com.InvGenius.InvGenius.models.categoria;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.var;

@RestController
@RequestMapping("api/v1/categoria")
public class categoriaController {

    @Autowired
    private IcategoriaService categoriaService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("categoria") categoria categoria) {

        // Verifica que no se repita el nombre de la categoria
        var listaCategoria = categoriaService.categoriaExist(categoria.getNombreCategoria(), null, null);

        if (listaCategoria.size() != 0) {
            return new ResponseEntity<>("La categoria ya existe", HttpStatus.BAD_REQUEST);
        }

        // Verificar que el campo de de nombreCategoria y estado sean obligatorios
        // Añadir campos obligatorios

        if (categoria.getNombreCategoria().equals("")) {
            return new ResponseEntity<>("El campo Nombre Categoria es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (categoria.getEstado().equals("")) {
            return new ResponseEntity<>("El campo estado es obligatorio", HttpStatus.BAD_REQUEST);
        }

        categoriaService.save(categoria);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
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
            @ModelAttribute("categoria") categoria categoriaUpdate) {
        var categoria = categoriaService.findOne(id).get();

        if (categoria != null) {

            categoria.setNombreCategoria(categoriaUpdate.getNombreCategoria());
            categoria.setEstado(categoriaUpdate.getEstado());

            categoriaService.save(categoria);

            return new ResponseEntity<>(categoria, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Error categoria no encontrada", HttpStatus.BAD_REQUEST);
        }

    }
}
