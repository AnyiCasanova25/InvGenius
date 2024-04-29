package com.InvGenius.InvGenius.Controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.interfaceService.IuserService;
import com.InvGenius.InvGenius.models.user;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/user/security")

public class userControllerSecurity {
    
    @Autowired
    private IuserService userService;
    @PostMapping("path")
    public ResponseEntity <Object> save(@ModelAttribute("user") user user) {
        if (user.getPrimerNombre().equals("")) {

            return new ResponseEntity<>("El primer nombre es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (user.getPrimerApellido().equals("")) {

            return new ResponseEntity<>("El primer apellido es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (user.getCelular().equals("")) {

            return new ResponseEntity<>("El numero de telefono es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (user.getCorreo().equals("")) {

            return new ResponseEntity<>("El correo es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (user.getPassword().equals("")) {

            return new ResponseEntity<>("La contraseña es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (user.getConfirmarPassword().equals("")) {

            return new ResponseEntity<>("Confirme su contraseña correctamente", HttpStatus.BAD_REQUEST);
        }

        // todo bien
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
     @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var user = userService.findOne(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        userService.delete(id);
        return new ResponseEntity<>("Registro eliminado", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("user") user userUpdate) {
        var user = userService.findOne(id).get();

        if (user != null) {

            user.setDocumentoIdentidad(userUpdate.getDocumentoIdentidad());
            user.setPrimerNombre(userUpdate.getPrimerNombre());
            user.setSegundoNombre(userUpdate.getSegundoNombre());
            user.setPrimerApellido(userUpdate.getPrimerApellido());
            user.setSegundoApellido(userUpdate.getSegundoApellido());
            user.setCelular(userUpdate.getCelular());
            user.setCorreo(userUpdate.getCorreo());
            user.setPassword(userUpdate.getPassword());
            user.setConfirmarPassword(userUpdate.getConfirmarPassword());
            user.setRol(userUpdate.getRol());

            userService.save(user);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error usuario NO encontrado", HttpStatus.BAD_REQUEST);
        }
    }
    
}
