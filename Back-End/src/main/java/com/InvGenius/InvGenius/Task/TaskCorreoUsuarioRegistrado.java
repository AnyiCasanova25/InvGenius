package com.InvGenius.InvGenius.Task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.InvGenius.InvGenius.models.user;
import com.InvGenius.InvGenius.service.authService;
import com.InvGenius.InvGenius.service.emailService;

public class TaskCorreoUsuarioRegistrado {
    
    @Autowired
    private authService data;

    @Autowired
    private emailService email;


    //task para el correo cuando el usuario se registre
    @Scheduled(fixedRate = 1000)
    public void enviarCorreoRegistro(){
        List<user> listaUser = data.findAll();
        for (user user : listaUser){
            System.out.println("Usuario registrado: " + user.getNombres() + " " + user.getApellidos());
            email.enviarCorreoRegistro(user, user.getPassword());
        }
    }

}
