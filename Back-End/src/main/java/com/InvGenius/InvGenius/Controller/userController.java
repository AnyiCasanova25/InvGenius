package com.InvGenius.InvGenius.Controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.interfaceService.IuserService;
import com.InvGenius.InvGenius.models.user;

import lombok.var;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/user")

public class userController {

    @Autowired
    private IuserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    private static int numeroAleatorioEnRango(int minimo, int maximo) {
        // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos
        // 1
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }

    private String codigoAleatorio() {
        int longitud = 6;
        // El banco de caracteres
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        // La cadena en donde iremos agregando un carácter aleatorio
        String cadena = "";
        for (int x = 0; x < longitud; x++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        return cadena;
    }

    @PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("user") user user) {

        // Verificar que no exista numero de telefono
        var listaUser = userService.userExist(user.getCelular(), user.getCorreo());

        if (listaUser.size() != 0) {
            return new ResponseEntity<>("El correo y/o el numero de celular ya existe", HttpStatus.BAD_REQUEST);
        }

        // Verificar que el campo de de documento de identidad sea diferente a vacio
        // Añadir campos obligatorios

        if (user.getDocumentoIdentidad().equals("")) {

            return new ResponseEntity<>("El documento de identidad es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (user.getNombres().equals("")) {

            return new ResponseEntity<>("El primer nombre es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (user.getApellidos().equals("")) {

            return new ResponseEntity<>("El primer apellido es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (user.getCelular().equals("")) {

            return new ResponseEntity<>("El numero de telefono es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (user.getCorreo().equals("")) {

            return new ResponseEntity<>("El correo es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(codigoAleatorio());

        // if (user.getPassword().equals("")) {

        // return new ResponseEntity<>("La contraseña es un campo obligatorio",
        // HttpStatus.BAD_REQUEST);
        // }

        // if (user.getConfirmarPassword().equals("")) {

        //     return new ResponseEntity<>("Confirme su contraseña correctamente", HttpStatus.BAD_REQUEST);
        // }

        // todo bien
        user.setRol("Usuario");
        userService.save(user);
        emailController email = new emailController(javaMailSender);
        email.enviarCorreoRegistro(user);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        var listaUser = userService.findAll();
        return new ResponseEntity<>(listaUser,HttpStatus.OK);
    }

   @GetMapping("/busquedaFiltros/{filtro}")
   public ResponseEntity<Object> findFiltro(@PathVariable String filtro) {
       var listaUser = userService.userExist(filtro,filtro);
       return new ResponseEntity<>(listaUser, HttpStatus.OK);
   }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var user = userService.findOne(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        var user = userService.findOne(id).get();
        if (user != null) {
            if (user.getRol().equals("Usuario")) {
 
                user.setRol("Administrador");
                userService.save(user);
                return new ResponseEntity<>("El administrador es usuario ahora", HttpStatus.OK);
            } else
                user.setRol("Usuario");
            userService.save(user);
            return new ResponseEntity<>("El usuario es administrador ahora", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se ha encontrado el registro", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("user") user userUpdate) {
        var user = userService.findOne(id).get();

        if (user != null) {

            user.setDocumentoIdentidad(userUpdate.getDocumentoIdentidad());
            user.setNombres(userUpdate.getNombres());
            user.setApellidos(userUpdate.getApellidos());
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
