package com.InvGenius.InvGenius.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.interfaceService.IuserService;
import com.InvGenius.InvGenius.models.user;

@RestController
@RequestMapping("/api/v1/user")

public class userController {
    
    //  @Autowired
    //  private IuserService userService;

    // @PostMapping("/")
    // public ResponseEntity<Object> save (@ModelAttribute("user") user user){

    //     //Verificar que no exista numero de telefono 
    //  var listaUser = userService.findAll()
    // .stream().filter(user -> user.getCelular()
    //     .equals(user.getCelular()));

    // if (listaUser.count() !=0) {
    //         return new ResponseEntity<>("El numero de celular ya existe", HttpStatus.BAD_REQUEST);
    //     }

    //     listaUser = userService.findAll()
    //     .stream().filter(user ->user.getCorreo()
    //     .equals(user.getCorreo()));

    //     if (listaUser.count()!=0) {
    //         return new ResponseEntity<>("El correo ya existe", HttpStatus.BAD_REQUEST); 
    //     }


    // }
    @GetMapping("/")
    public String home() {

        return "Este es un end point de user(privado)";
    }
}
